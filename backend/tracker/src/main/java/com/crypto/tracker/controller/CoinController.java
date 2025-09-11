package com.crypto.tracker.controller;

//Annotation Imports 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
//Autowired
import org.springframework.beans.factory.annotation.Autowired;
// Get, Post und Delete Mappings
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

//ResponseEntity
import org.springframework.http.ResponseEntity;

//Java Utils
import java.util.List;

//COIN
//Service
import com.crypto.tracker.services.CoinService;

import io.swagger.v3.oas.annotations.Parameter;

//Model
import com.crypto.tracker.model.Coin;

@RestController
@RequestMapping("/api/coins")
public class CoinController {

    @Autowired
    private CoinService coinService;

    // Returned Liste aller verfügbaren coins
    @GetMapping
    public ResponseEntity<List<Coin>> getCoins() {
        List<Coin> coins = coinService.getAllCoins();
        return ResponseEntity.ok(coins);
    }

    // Coin zur Watchlist hinzufügen
    @PostMapping
    public ResponseEntity<Coin> addCoin(@RequestBody Coin coin) {
        Coin newCoin = coinService.addCoin(coin);
        return ResponseEntity.ok(newCoin);
    }

    // Coin von der Watchlist entfernen
    @DeleteMapping
    public String removeCoin(@PathVariable Integer id) {
        coinService.removeCoin(id);
        return "Coin removed";
    }

    // Coins in der eigenen Watchlist anzeigen
    @GetMapping("/watchlist")
    public String getWatchlist() {
        return "";
    }

    // Kursverlauf eines Coins abrufen
    @GetMapping("/{id}/history")
    public String getCoinHistory(@Parameter(description = "Coin ID") @PathVariable String id) {
        return "";
    }

    // Aktuellen Kurs eines Coins abrufen
    @GetMapping("/{id}/current")
    public String getCoinCurrent(@Parameter(description = "Coin ID") @PathVariable String id) {
        return "";
    }

}
