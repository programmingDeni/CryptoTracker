package com.crypto.tracker.services;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.crypto.tracker.enums.TimeInterval;
import com.crypto.tracker.model.Coin;
import com.crypto.tracker.model.CoinPriceHistory;
import com.crypto.tracker.repos.CoinPriceHistoryRepository;
import com.crypto.tracker.repos.CoinRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Scheduler-Service der periodisch Preisdaten von CoinGecko holt
 * und in der Datenbank speichert.
 */
@Service
public class PriceHistorySchedulerService {

    private static final Logger log = LoggerFactory.getLogger(PriceHistorySchedulerService.class);

    @Autowired
    private CoinGeckoService coinGeckoService;

    @Autowired
    private CoinRepository coinRepository;

    @Autowired
    private CoinPriceHistoryRepository priceHistoryRepository;

    @Value("${scheduler.price-history.enabled:true}")
    private boolean schedulerEnabled;

    @Value("${coingecko.initial-import.interval:YEAR_1}")
    private String initialImportInterval;

    /**
     * Holt alle 5 Minuten neue Preisdaten für alle Coins in der DB.
     * Duplikate werden durch den Composite Key automatisch verhindert.
     */
    @Scheduled(fixedRateString = "${scheduler.price-history.interval:300000}") // Default: 5 Minuten
    public void fetchPriceHistory() {
        if (!schedulerEnabled) {
            log.debug("Price history scheduler is disabled");
            return;
        }

        log.info("Starting scheduled price history fetch...");

        List<Coin> coins = coinRepository.findAll();
        if (coins.isEmpty()) {
            log.info("No coins in database, skipping fetch");
            return;
        }

        for (Coin coin : coins) {
            try {
                fetchAndStorePriceHistoryForCoin(coin);
            } catch (Exception e) {
                log.error("Error fetching price history for {}: {}", coin.getSymbol(), e.getMessage());
            }
        }

        log.info("Finished scheduled price history fetch for {} coins", coins.size());
    }

    /**
     * Holt Preisdaten für einen einzelnen Coin und speichert sie.
     * Wird vom Scheduler aufgerufen, kann aber auch manuell getriggert werden.
     */
    public void fetchAndStorePriceHistoryForCoin(Coin coin) {
        log.debug("Fetching price history for {}", coin.getSymbol());

        CoinPriceHistory latest = priceHistoryRepository.findLatestByCoin(coin);

        TimeInterval interval;

        // berechne wie lange seit dem letzten fetch vergangen ist
        if (latest == null) {
            // keine daten vorhanden => api limit holen
            interval = TimeInterval.valueOf(initialImportInterval);
        } else {
            long latestTimestamp = latest.getId().getTimestamp();
            long now = System.currentTimeMillis();
            long hoursSinceLastFetch = (now - latestTimestamp) / (60 * 60 * 1000);

            if (hoursSinceLastFetch < 1) {
                log.debug("Data for {} is up to date, skipping", coin.getSymbol());
                return; // Daten sind aktuell, nichts zu tun
            }

            // Wähle passendes Interval basierend auf der Lücke
            if (hoursSinceLastFetch <= 24) {
                interval = TimeInterval.HOURS_24;
            } else if (hoursSinceLastFetch <= 168) { // 7 Tage
                interval = TimeInterval.DAYS_7;
            } else if (hoursSinceLastFetch <= 720) { // 30 Tage
                interval = TimeInterval.DAYS_30;
            } else if (hoursSinceLastFetch <= 8760) { // 365 Tage
                interval = TimeInterval.YEAR_1;
            } else {
                interval = TimeInterval.MAX;
            }
            log.debug("Data for {} is {} hours old, fetching {}", coin.getSymbol(), hoursSinceLastFetch, interval);
        }

        // Hole 1 Tag Daten (5-Minuten-Intervalle)
        // TODO: ist der api call mit dem namen nicht mit der coingeckoid?
        String json = coinGeckoService.getCoinHistory(
                coin.getCoinGeckoIdString().toLowerCase(),
                "usd",
                interval);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);

            JsonNode prices = root.get("prices");
            JsonNode marketCaps = root.get("market_caps");
            JsonNode volumes = root.get("total_volumes");

            List<CoinPriceHistory> entities = new ArrayList<>();

            for (int i = 0; i < prices.size(); i++) {
                long timestamp = prices.get(i).get(0).asLong();
                double price = prices.get(i).get(1).asDouble();
                double marketCap = marketCaps.get(i).get(1).asDouble();
                double volume = volumes.get(i).get(1).asDouble();

                CoinPriceHistory entity = new CoinPriceHistory(
                        coin,
                        coin.getSymbol(),
                        timestamp,
                        BigDecimal.valueOf(price),
                        BigDecimal.valueOf(marketCap),
                        BigDecimal.valueOf(volume));
                entities.add(entity);
            }

            // saveAll ignoriert Duplikate dank Composite Key
            priceHistoryRepository.saveAll(entities);
            log.debug("Saved {} price entries for {}", entities.size(), coin.getSymbol());

        } catch (Exception e) {
            throw new RuntimeException("Error parsing price history for " + coin.getSymbol(), e);
        }
    }

    /**
     * Initialer Import: Holt historische Daten basierend auf der
     * konfigurierten TimeInterval (coingecko.initial-import.interval).
     * Default: YEAR_1 (365 Tage): CoinGecko Free-API Limit.
     */
    public void initialImport(Coin coin) {
        log.info("Starting initial import for {}", coin.getSymbol());

        TimeInterval interval = TimeInterval.valueOf(initialImportInterval);

        String json = coinGeckoService.getCoinHistory(
                coin.getCoinGeckoIdString().toLowerCase(),
                "usd",
                interval);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);

            JsonNode prices = root.get("prices");
            JsonNode marketCaps = root.get("market_caps");
            JsonNode volumes = root.get("total_volumes");

            List<CoinPriceHistory> entities = new ArrayList<>();

            for (int i = 0; i < prices.size(); i++) {
                long timestamp = prices.get(i).get(0).asLong();
                double price = prices.get(i).get(1).asDouble();
                double marketCap = marketCaps.get(i).get(1).asDouble();
                double volume = volumes.get(i).get(1).asDouble();

                CoinPriceHistory entity = new CoinPriceHistory(
                        coin,
                        coin.getSymbol(),
                        timestamp,
                        BigDecimal.valueOf(price),
                        BigDecimal.valueOf(marketCap),
                        BigDecimal.valueOf(volume));
                entities.add(entity);
            }

            priceHistoryRepository.saveAll(entities);
            log.info("Initial import complete: {} entries for {}", entities.size(), coin.getSymbol());

        } catch (Exception e) {
            throw new RuntimeException("Error during initial import for " + coin.getSymbol(), e);
        }
    }
}