//Query imports
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";

//Type imports
import type { CoinPriceHistory } from "../api";

//Service imports
import { getCoinPriceHistoryService } from "../services/coinPriceHistoryService";
import type { TimeInterval } from "../types/timeIntervalLables";

export function useCoinPriceHistory(
  coinId: number,
  timeInterval: TimeInterval = "DAYS_30"
) {
  return useQuery<CoinPriceHistory[]>({
    queryKey: ["coinPriceHistories", coinId, timeInterval],
    queryFn: () => getCoinPriceHistoryService(coinId, timeInterval),
  });
}
