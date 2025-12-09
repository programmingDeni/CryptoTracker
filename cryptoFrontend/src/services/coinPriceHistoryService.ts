import axios from "./axios";

import { CoinControllerApi } from "../api";
import type { CoinPriceHistory } from "../api";
import { apiConfig } from "../api/apiConfig";
import type { TimeInterval } from "../types/timeIntervalLables";

const coinPriceHistoryApi = new CoinControllerApi(apiConfig, undefined, axios);

export async function getCoinPriceHistoryService(
  coinId: number,
  interval: TimeInterval = "DAYS_30"
): Promise<CoinPriceHistory[]> {
  try {
    const response = await coinPriceHistoryApi.getCoinHistory(coinId, interval);
    return response.data;
  } catch (error) {
    console.log("coinPriceHistoryService.ts error: ", error);
    throw error;
  }
}
