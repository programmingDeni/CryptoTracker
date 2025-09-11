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
         */
        Coin coin = new Coin();
        coin.setSymbol(dto.symbol);
        coin.setName(dto.name);
        coin.setImage(dto.image);
        coin.setCurrentPrice(dto.current_price);
        coin.setMarketCap(dto.market_cap);
        coin.setPriceChange24hPerCent(dto.price_change_24h_per_cent);
        coin.setAth(dto.ath);

        return coin;
    }

}
