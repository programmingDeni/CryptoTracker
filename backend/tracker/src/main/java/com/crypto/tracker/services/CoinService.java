package com.crypto.tracker.services;

//Annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Java Utils
import java.util.List;

//Model
import com.crypto.tracker.model.Coin;

//Repository
import com.crypto.tracker.repos.CoinRepository;

@Service
public class CoinService {

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
}
