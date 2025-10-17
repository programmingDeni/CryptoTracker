package com.crypto.tracker.services;

//Annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
//Java Utils
import java.util.List;
//Dtos 
import com.crypto.tracker.dto.CoinGeckoCoinDto;
//Mapper
import com.crypto.tracker.mapper.CoinPriceHistoryMapper;
import com.crypto.tracker.mapper.CoinGeckoToCoinMapper;
//Model
import com.crypto.tracker.model.Coin;
import com.crypto.tracker.model.CoinPriceHistory;
//Repository
import com.crypto.tracker.repos.CoinRepository;
import com.crypto.tracker.repos.CoinPriceHistoryRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoinService {

    @Autowired
    private CoinGeckoService coinGeckoService;

    @Autowired
    private CoinRepository coinRepository;

    @Autowired
    private CoinPriceHistoryRepository coinPriceHistoryRepository;

    public Coin getCoin(Long id) {
        return coinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coin not found with id: " + id));
    }

    public List<Coin> getAllCoins() {
        return coinRepository.findAll();
    }

    public Coin addCoin(Coin coin) {
        return coinRepository.save(coin);
    }

    public void removeCoin(Long id) {
        coinRepository.deleteById(id);
    }

    public void fetchAndPersistTopCoins(int perPage, int page, String vsCurrency) throws Exception {
        String json = coinGeckoService.getTopCoins(perPage, page, vsCurrency);
        ObjectMapper mapper = new ObjectMapper();
        CoinGeckoCoinDto[] dtos = mapper.readValue(json, CoinGeckoCoinDto[].class);

        for (CoinGeckoCoinDto dto : dtos) {
            Coin coin = CoinGeckoToCoinMapper.fromDto(dto);
            coinRepository.save(coin);
        }
    }

    public List<CoinPriceHistory> getCoinHistory(Long coinId) {
        // coin id in symbol umwandeln
        Coin coin = coinRepository.findById(coinId)
                .orElseThrow(() -> new RuntimeException("Coin not found with id: " + coinId));
        // Prüfe, ob Historie schon in der DB ist
        List<CoinPriceHistory> history = coinPriceHistoryRepository.findBySymbol(coin.getSymbol());
        // wenn history nicht leer ist, gib es als json zurück
        if (!history.isEmpty()) {
            // Falls ja, gib sie als JSON zurück
            try {
                return (history);
            } catch (Exception e) {
                throw new RuntimeException("Fehler beim Serialisieren der Preis-Historie", e);
            }
        }

        // Falls nicht, hole von CoinGecko
        String json = coinGeckoService.getCoinHistory(coin.getName().toLowerCase(), "usd", 30);

        // Parse und speichere in DB
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

                CoinPriceHistory entity = new CoinPriceHistory(coin, coin.getSymbol(), timestamp, price,
                        marketCap, volume);
                entities.add(entity);
            }
            coinPriceHistoryRepository.saveAll(entities);

        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Verarbeiten der CoinGecko-Historie", e);
        }

        // Gib die frisch gespeicherte Historie zurück
        List<CoinPriceHistory> savedHistory = coinPriceHistoryRepository.findBySymbol(coin.getSymbol());
        try {
            ObjectMapper mapper = new ObjectMapper();
            return (savedHistory);
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Serialisieren der Preis-Historie", e);
        }

    }
}
