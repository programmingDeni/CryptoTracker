package com.crypto.tracker.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.crypto.tracker.services.CoinService;

@Profile("!test")
@Component
public class CoinDataInitializer implements CommandLineRunner {

    @Autowired
    private CoinService coinService;

    @Override
    public void run(String... args) throws Exception {
        if (coinService.getAllCoins().isEmpty()) {
            coinService.fetchAndPersistTopCoins(10, 1, "eur"); // z.B. Top 10 Coins in EUR
            System.out.println("Coin-Daten wurden initial geladen!");
        } else {
            System.out.println("Coin-Daten bereits vorhanden, Initialisierung übersprungen.");
        }
    }
}
