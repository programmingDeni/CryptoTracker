//import axios from "./axios";

//import type { Coin } from "../types/Coin";

import { CoinControllerApi } from "../api";
import type { Coin } from "../api";
import { apiConfig } from "../api/apiConfig";

export async function getCoinsService(): Promise<Coin[]> {
  try {
    const api = new CoinControllerApi(apiConfig);
    const response = await api.getCoins();
    return response.data;
  } catch (error) {
    console.log("coinService.ts error: ", error);
    throw error;
  }
}
