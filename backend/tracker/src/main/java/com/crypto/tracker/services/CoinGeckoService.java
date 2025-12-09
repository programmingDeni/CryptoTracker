package com.crypto.tracker.services;

//Annotations
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import com.crypto.tracker.enums.TimeInterval;

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
        // https://api.coingecko.com/api/v3/coins/markets
        // returns
        /*
         * [
         * {
         * "id": "bitcoin",
         * "symbol": "btc",
         * "name": "Bitcoin",
         * "image":
         * "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1696501400",
         * "current_price": 70187,
         * "market_cap": 1381651251183,
         * "market_cap_rank": 1,
         * "fully_diluted_valuation": 1474623675796,
         * "total_volume": 20154184933,
         * "high_24h": 70215,
         * "low_24h": 68060,
         * "price_change_24h": 2126.88,
         * "price_change_percentage_24h": 3.12502,
         * "market_cap_change_24h": 44287678051,
         * "market_cap_change_percentage_24h": 3.31157,
         * "circulating_supply": 19675987,
         * "total_supply": 21000000,
         * "max_supply": 21000000,
         * "ath": 73738,
         * "ath_change_percentage": -4.77063,
         * "ath_date": "2024-03-14T07:10:36.635Z",
         * "atl": 67.81,
         * "atl_change_percentage": 103455.83335,
         * "atl_date": "2013-07-06T00:00:00.000Z",
         * "roi": null,
         * "last_updated": "2024-04-07T16:49:31.736Z"
         * }
         * ]
         */
        public String getTopCoins(int perPage, int page, String vsCurrency) {
                // https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=10&page=1&sparkline=true
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
        // ist gut aber pulled nur die letzten
        public String getCoinHistory(String coinId, String vsCurrency, TimeInterval days) {
                String url = BASE_URL + "/coins/" + coinId + "/market_chart?vs_currency="
                                + vsCurrency + "&days=" + days.toApiParam();
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
}
