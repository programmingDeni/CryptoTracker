package com.crypto.tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//getter
import lombok.Getter;

@Getter
@Entity
public class CoinPriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "coin_id", nullable = false)
    @JsonIgnore
    private Coin coin;
    private String symbol;
    private long timestamp;
    private double price;
    private double market_cap;
    private double total_volume;

    // Constructor
    public CoinPriceHistory() {
    }

    public CoinPriceHistory(Coin coin, String symbol, long timestamp, double price, double market_cap,
            double total_volume) {
        this.coin = coin;
        this.symbol = symbol;
        this.timestamp = timestamp;
        this.price = price;
        this.market_cap = market_cap;
        this.total_volume = total_volume;
    }

    // Setter für alles außer id und Symbol

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMarketCap(double market_cap) {
        this.market_cap = market_cap;
    }

    public void setTotalVolume(double total_volume) {
        this.total_volume = total_volume;
    }
}
