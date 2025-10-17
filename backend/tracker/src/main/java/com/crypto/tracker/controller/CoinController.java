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

import io.swagger.v3.oas.annotations.Operation;
//OpenAPI / Swagger
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//Model
import com.crypto.tracker.model.Coin;
import com.crypto.tracker.model.CoinPriceHistory;

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
    public String removeCoin(@PathVariable Long id) {
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
    @Operation(summary = "Kursverlauf eines Coins abrufen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coin history as JSON", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CoinPriceHistory.class))))
    })
    public List<CoinPriceHistory> getCoinHistory(@Parameter(description = "Coin ID") @PathVariable Long id) {
        return coinService.getCoinHistory(id);
    }

    // Aktuellen Kurs eines Coins abrufen
    @GetMapping("/{id}/current")
    public String getCoinCurrent(@Parameter(description = "Coin ID") @PathVariable String id) {
        return "";
    }

}
