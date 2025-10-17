//Query imports
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";

//Type imports
import type { CoinPriceHistory } from "../api";

//Service imports
import { getCoinPriceHistoryService } from "../services/coinPriceHistoryService";

export function useCoinPriceHistory(coinId: number) {
  return useQuery<CoinPriceHistory[]>({
    queryKey: ["coinPriceHistories", coinId],
    queryFn: ({ queryKey }) => {
      const [, coinId] = queryKey; // <--- coinId extrahieren
      return getCoinPriceHistoryService(coinId as number);
    },
  });
}
