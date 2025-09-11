package com.crypto.tracker.services;

//Annotations
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Service
public class CoinGeckoService {

    @Value("${coingecko.api.key}")
    private String apiKey;

    private final String BASE_URL = "https://api.coingecko.com/api/v3";
    private final RestTemplate restTemplate = new RestTemplate();

    // Get Coins von CoinGecko
    /**
     * Response 200 - application/json
     * List all coins with market data
     *
     * id: string - coin ID
     * symbol: string - coin symbol
     * name: string - coin name
     * image: string - coin image url
     * current_price: number - coin current price in currency
     * market_cap: number - coin market cap in currency
     * market_cap_rank: number - coin rank by market cap
     * fully_diluted_valuation: number - coin fully diluted valuation (fdv) in
     * currency
     * total_volume: number - coin total trading volume in currency
     * high_24h: number - coin 24hr price high in currency
     * low_24h: number - coin 24hr price low in currency
     * price_change_24h: number - coin 24hr price change in currency
     * price_change_percentage_24h: number - coin 24hr price change in percentage
     * market_cap_change_24h: number - coin 24hr market cap change in currency
     * market_cap_change_percentage_24h: number - coin 24hr market cap change in
     * percentage
     * circulating_supply: number - coin circulating supply
     * total_supply: number - coin total supply
     * max_supply: number - coin max supply
     * ath: number - coin all time high (ATH) in currency
     * ath_change_percentage: number - coin all time high (ATH) change in percentage
     * ath_date: string<date-time> - coin all time high (ATH) date
     * atl: number - coin all time low (atl) in currency
     * atl_change_percentage: number - coin all time low (atl) change in percentage
     * atl_date: string<date-time> - coin all time low (atl) date
     * roi: string
     * last_updated: string<date-time> - coin last updated timestamp
     */
    public String getTopCoins(int perPage, int page, String vsCurrency) {
        String url = BASE_URL + "/coins/markets?vs_currency=" + vsCurrency +
                "&order=market_cap_desc&per_page=" + perPage + "&page=" + page + "&sparkline=true";

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-cg-demo-api-key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class);
        return response.getBody();
    }

    // TODO: GET Price Historien von CoinGecko

}
