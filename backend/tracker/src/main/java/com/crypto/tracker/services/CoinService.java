package com.crypto.tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.crypto.tracker.dto.CoinGeckoCoinDto;
import com.crypto.tracker.enums.TimeInterval;
import com.crypto.tracker.mapper.CoinGeckoToCoinMapper;
import com.crypto.tracker.model.Coin;
import com.crypto.tracker.model.CoinPriceHistory;
import com.crypto.tracker.repos.CoinRepository;
import com.crypto.tracker.security.SecurityUtils;
import com.crypto.tracker.repos.CoinPriceHistoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoinService {

    @Autowired
    private CoinGeckoService coinGeckoService;

    @Autowired
    private CoinRepository coinRepository;

    @Autowired
    private CoinPriceHistoryRepository coinPriceHistoryRepository;

    @Autowired
    private PriceHistorySchedulerService priceHistorySchedulerService;

    public Coin getCoin(Integer id) {
        return coinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coin not found with id: " + id));
    }

    public List<Coin> getAllCoins() {
        return coinRepository.findAll();
    }

    public Coin addCoin(Coin coin) {
        return coinRepository.save(coin);
    }

    public void removeCoin(Integer id) {
        coinRepository.deleteById(id);
    }

    public void fetchAndPersistTopCoins(int perPage, int page, String vsCurrency) throws Exception {
        String json = coinGeckoService.getTopCoins(perPage, page, vsCurrency);
        ObjectMapper mapper = new ObjectMapper();
        CoinGeckoCoinDto[] dtos = mapper.readValue(json, CoinGeckoCoinDto[].class);

        Integer userId = SecurityUtils.getCurrentUserId();

        for (CoinGeckoCoinDto dto : dtos) {
            Coin coin = CoinGeckoToCoinMapper.fromDto(dto);
            coin.setUserId(userId);
            coinRepository.save(coin);
        }
    }

    /**
     * Holt Preishistorie aus der DB für ein bestimmtes Zeitintervall.
     * Kein API-Call - Daten werden vom Scheduler befüllt.
     */
    public List<CoinPriceHistory> getCoinHistory(Integer coinId, TimeInterval interval) {
        Coin coin = coinRepository.findById(coinId)
                .orElseThrow(() -> new RuntimeException("Coin not found with id: " + coinId));

        // Berechne den Timestamp ab dem wir Daten wollen
        long fromTimestamp = calculateFromTimestamp(interval);

        // Hole gefilterte Daten aus der DB
        List<CoinPriceHistory> history = coinPriceHistoryRepository.findByCoinAndTimestampAfter(coin, fromTimestamp);
        if (history.isEmpty()) {
            // Falls keine Daten vorhanden sind, hole von CoinGecko
            priceHistorySchedulerService.initialImport(coin);
            history = coinPriceHistoryRepository.findByCoinAndTimestampAfter(coin, fromTimestamp);
        }
        return history;
    }

    /**
     * Berechnet den Start-Timestamp basierend auf dem Interval.
     */
    private long calculateFromTimestamp(TimeInterval interval) {
        long now = System.currentTimeMillis();

        return switch (interval) {
            case HOUR_1 -> now - (60 * 60 * 1000L); // 1 Stunde
            case HOURS_24 -> now - (24 * 60 * 60 * 1000L); // 24 Stunden
            case DAYS_7 -> now - (7 * 24 * 60 * 60 * 1000L); // 7 Tage
            case DAYS_30 -> now - (30L * 24 * 60 * 60 * 1000L); // 30 Tage
            case YEAR_1 -> now - (365L * 24 * 60 * 60 * 1000L); // 1 Jahr
            case MAX -> 0L; // Alle Daten
        };
    }
}