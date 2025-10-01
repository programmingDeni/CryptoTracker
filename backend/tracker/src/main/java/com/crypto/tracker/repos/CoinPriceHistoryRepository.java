package com.crypto.tracker.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crypto.tracker.model.CoinPriceHistory;

public interface CoinPriceHistoryRepository extends JpaRepository<CoinPriceHistory, Long> {
    List<CoinPriceHistory> findBySymbol(String symbol);
}
