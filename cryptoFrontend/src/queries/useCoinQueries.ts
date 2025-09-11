//Query imports
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";

//Type imports
import type { Coin } from "../api";

//Service imports
import { getCoinsService } from "../services/coinService";

export function useCoins() {
  return useQuery<Coin[]>({
    queryKey: ["coins"],
    queryFn: getCoinsService,
  });
}
