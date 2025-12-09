package com.crypto.tracker.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.crypto.tracker.dto.CoinGeckePriceHistoryDto;
import com.crypto.tracker.model.CoinPriceHistory;

public class CoinPriceHistoryMapper {
    public static String toDto(CoinPriceHistory coinPriceHistory) {
        return "";
    }

    public static String toDto(List<CoinPriceHistory> coinPriceHistory) {
        return "";
    }

    public static CoinPriceHistory fromDto(CoinGeckePriceHistoryDto priceHistoryDto) {
        CoinPriceHistory coinPriceHistory = new CoinPriceHistory();
        if (priceHistoryDto.prices != null && !priceHistoryDto.prices.isEmpty()) {
            List<Double> entry = priceHistoryDto.prices.get(0);
            coinPriceHistory.getId().setTimestamp(entry.get(0).longValue());
            coinPriceHistory.setPrice(BigDecimal.valueOf(entry.get(1)));
        }

        if (priceHistoryDto.market_caps != null && !priceHistoryDto.market_caps.isEmpty()) {
            coinPriceHistory.setMarketCap(BigDecimal.valueOf(priceHistoryDto.market_caps.get(0).get(1)));
        }

        if (priceHistoryDto.total_volumes != null && !priceHistoryDto.total_volumes.isEmpty()) {
            coinPriceHistory.setTotalVolume(BigDecimal.valueOf(priceHistoryDto.total_volumes.get(0).get(1)));
        }

        return coinPriceHistory;
    }
}
