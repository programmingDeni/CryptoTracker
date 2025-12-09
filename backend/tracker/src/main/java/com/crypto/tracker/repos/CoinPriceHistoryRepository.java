package com.crypto.tracker.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crypto.tracker.model.Coin;
import com.crypto.tracker.model.CoinPriceHistory;
import com.crypto.tracker.model.CoinPriceHistoryId;

public interface CoinPriceHistoryRepository extends JpaRepository<CoinPriceHistory, CoinPriceHistoryId> {
    List<CoinPriceHistory> findBySymbol(String symbol);

    List<CoinPriceHistory> findByCoinId(Coin coin);

    /**
     * Findet Preishistorien für einen Coin in einem Zeitraum.
     * Sortiert nach Timestamp aufsteigend.
     */
    @Query("SELECT p FROM CoinPriceHistory p WHERE p.coin = :coin AND p.id.timestamp >= :fromTimestamp ORDER BY p.id.timestamp ASC")
    List<CoinPriceHistory> findByCoinAndTimestampAfter(
            @Param("coin") Coin coin,
            @Param("fromTimestamp") long fromTimestamp);

    /**
     * Findet den neuesten Eintrag für einen Coin (für Scheduler-Logik).
     */
    @Query("SELECT p FROM CoinPriceHistory p WHERE p.coin = :coin ORDER BY p.id.timestamp DESC LIMIT 1")
    CoinPriceHistory findLatestByCoin(@Param("coin") Coin coin);
}
