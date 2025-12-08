//import axios from "./axios";

import { CoinControllerApi } from "../api";

import type { CoinPriceHistory } from "../api";

import { apiConfig } from "../api/apiConfig";

export async function getCoinPriceHistoryService(
  coinId: number
): Promise<CoinPriceHistory[]> {
  try {
    const api = new CoinControllerApi(apiConfig);
    const response = await api.getCoinHistory(coinId);
    return response.data;
  } catch (error) {
    console.log("coinPriceHistoryService.ts error: ", error);
    throw error;
  }
}
