package com.crypto.tracker.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinGeckoCoinDto {
    public String id;
    public String symbol;
    public String name;
    public String image;
    public BigDecimal current_price;
    public BigDecimal market_cap;
    @JsonProperty("price_change_percentage_24h")
    public BigDecimal priceChange24hPerCent;
    public BigDecimal ath;

}
