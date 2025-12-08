package com.crypto.tracker.mapper;

//Coin importieren 
import com.crypto.tracker.model.Coin;
//Dto importieren
import com.crypto.tracker.dto.CoinGeckoCoinDto;

public class CoinGeckoToCoinMapper {
    public static Coin fromDto(CoinGeckoCoinDto dto) {
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
        Coin coin = new Coin();
        coin.setCoinGeckoIdString(dto.id);
        coin.setSymbol(dto.symbol);
        coin.setName(dto.name);
        coin.setImage(dto.image);
        coin.setCurrentPrice(dto.current_price);
        coin.setMarketCap(dto.market_cap);
        coin.setPriceChange24hPerCent(dto.priceChange24hPerCent);
        coin.setAth(dto.ath);

        return coin;
    }

}
