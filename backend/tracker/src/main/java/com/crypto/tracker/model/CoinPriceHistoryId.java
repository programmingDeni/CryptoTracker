package com.crypto.tracker.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * Composite Primary Key für CoinPriceHistory.
 * Kombination aus Coin-ID und Timestamp stellt Eindeutigkeit sicher
 * und verhindert automatisch Duplikate beim Speichern.
 */
@Embeddable
@Getter
@Setter
public class CoinPriceHistoryId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "coin_id", nullable = false)
    private Integer coinId;

    @Column(nullable = false)
    private long timestamp;

    public CoinPriceHistoryId() {
    }

    public CoinPriceHistoryId(Integer coinId, long timestamp) {
        this.coinId = coinId;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CoinPriceHistoryId that = (CoinPriceHistoryId) o;
        return timestamp == that.timestamp && Objects.equals(coinId, that.coinId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coinId, timestamp);
    }
}