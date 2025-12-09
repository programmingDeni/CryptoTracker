package com.crypto.tracker.model;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
//getter
import lombok.Getter;

@Getter
@Entity
public class CoinPriceHistory {
    @EmbeddedId
    private CoinPriceHistoryId id;

    @ManyToOne
    @MapsId("coinId")
    @JoinColumn(name = "coin_id", nullable = false)
    @JsonIgnore
    private Coin coin;
    private String symbol;
    private BigDecimal price;
    private BigDecimal market_cap;
    private BigDecimal total_volume;

    // Constructor
    public CoinPriceHistory() {
    }

    public CoinPriceHistory(Coin coin, String symbol, long timestamp,
            BigDecimal price,
            BigDecimal marketCap,
            BigDecimal totalVolume) {
        this.id = new CoinPriceHistoryId(coin.getId(), timestamp);
        this.coin = coin;
        this.symbol = symbol;
        this.price = price;
        this.market_cap = marketCap;
        this.total_volume = totalVolume;
    }

    public Long getTimestamp() {
        return id != null ? id.getTimestamp() : 0;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setMarketCap(BigDecimal market_cap) {
        this.market_cap = market_cap;
    }

    public void setTotalVolume(BigDecimal total_volume) {
        this.total_volume = total_volume;
    }
}
