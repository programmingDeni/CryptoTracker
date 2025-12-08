package com.crypto.tracker.dto;

import java.util.List;

public class CoinGeckePriceHistoryDto {
    public List<List<Double>> prices;
    public List<List<Double>> market_caps;
    public List<List<Double>> total_volumes;
}
