package com.crypto.tracker.services;

//Annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Java Utils
import java.util.List;

import com.crypto.tracker.dto.CoinGeckoCoinDto;
import com.crypto.tracker.mapper.CoinGeckoToCoinMapper;
//Model
import com.crypto.tracker.model.Coin;

//Repository
import com.crypto.tracker.repos.CoinRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoinService {

    @Autowired
    private CoinGeckoService coinGeckoService;

    @Autowired
    private CoinRepository coinRepository;

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

        for (CoinGeckoCoinDto dto : dtos) {
            Coin coin = CoinGeckoToCoinMapper.fromDto(dto);
            coinRepository.save(coin);
        }
    }
}
