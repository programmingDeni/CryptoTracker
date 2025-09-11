package com.crypto.tracker.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinGeckoCoinDto {
    public String id;
    public String symbol;
    public String name;
    public String image;
    public BigDecimal current_price;
    public BigDecimal market_cap;
    public BigDecimal price_change_24h_per_cent;
    public BigDecimal ath;

}
