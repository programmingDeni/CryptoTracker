package com.crypto.tracker.model;

//java 
import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//getter
import lombok.Getter;

@Getter
@Entity
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal price;
    private BigDecimal marketCap;

    // Construktoren (braucht glaub JPA)
    public Coin() {
    }

    public Coin(String name, BigDecimal price, BigDecimal marketCap) {
        this.name = name;
        this.price = price;
        this.marketCap = marketCap;
    }

    // Setter für alles außer id
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

}
