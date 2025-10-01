package com.crypto.tracker.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crypto.tracker.model.Coin;

public interface CoinRepository extends JpaRepository<Coin, Long> {
    // Zusätzliche Query-Methoden können hier ergänzt werden
    Optional<Coin> findById(Long id);
}
