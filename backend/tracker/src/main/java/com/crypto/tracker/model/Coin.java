package com.crypto.tracker.model;

//java 
import java.math.BigDecimal;

import com.crypto.tracker.security.UserFeature.UserOwned;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//getter
import lombok.Getter;

@Getter
@Entity
public class Coin implements UserOwned {

    /*
     * Bekomme von CoinGeckoDto die folgenden Felder:
     * public String id;
     * public String symbol;
     * public String name;
     * public String image;
     * public BigDecimal current_price;
     * public BigDecimal market_cap;
     * public BigDecimal price_change_24h_per_cent;
     * public BigDecimal ath;
     */

    // ID ist nur meine Locale Id um Coins eindeutig zu persistieren
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // Symbol ist die "ID" von CoinGecko, e.g. "btc","eth"
    private String coinGeckoIdString;
    private String symbol;
    private String name;
    private String image;
    private BigDecimal currentPrice;
    private BigDecimal marketCap;
    private BigDecimal priceChange24hPerCent;
    private BigDecimal ath;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    // Construktoren (braucht glaub JPA)
    public Coin() {
    }

    public Coin(String coinGeckoIdString, Integer userId,
            String symbol, String name, String image,
            BigDecimal currentPrice, BigDecimal marketCap,
            BigDecimal priceChange24hPerCent, BigDecimal ath) {
        this.coinGeckoIdString = coinGeckoIdString;
        this.userId = userId;
        this.symbol = symbol;
        this.name = name;
        this.image = image;
        this.currentPrice = currentPrice;
        this.marketCap = marketCap;
        this.priceChange24hPerCent = priceChange24hPerCent;
        this.ath = ath;
    }

    // Setter für alles außer id
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public void setPriceChange24hPerCent(BigDecimal priceChange24hPerCent) {
        this.priceChange24hPerCent = priceChange24hPerCent;
    }

    public void setAth(BigDecimal ath) {
        this.ath = ath;
    }

    public void setCoinGeckoIdString(String coinGeckoIdString) {
        this.coinGeckoIdString = coinGeckoIdString;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
