import { CoinControllerApi } from "../api";
import type { Coin } from "../api";
import { apiConfig } from "../api/apiConfig";
import axiosInstance from "./axios";

// API einmal mit deiner axios-Instanz erstellen
const coinApi = new CoinControllerApi(apiConfig, undefined, axiosInstance);

export async function getCoinsService(): Promise<Coin[]> {
  try {
    const response = await coinApi.getCoins();
    return response.data;
  } catch (error) {
    console.log("coinService.ts error: ", error);
    throw error;
  }
}
