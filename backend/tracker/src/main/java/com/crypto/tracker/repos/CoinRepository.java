package com.crypto.tracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crypto.tracker.model.Coin;

public interface CoinRepository extends JpaRepository<Coin, Integer> {
    // Zusätzliche Query-Methoden können hier ergänzt werden
}
