import { GetCoinHistoryIntervalEnum } from "../api";

// Type Alias für bessere Lesbarkeit
export type TimeInterval = GetCoinHistoryIntervalEnum;

export const TIME_INTERVAL_LABELS: Record<TimeInterval, string> = {
  [GetCoinHistoryIntervalEnum.Hour1]: "1h",
  [GetCoinHistoryIntervalEnum.Hours24]: "24h",
  [GetCoinHistoryIntervalEnum.Days7]: "7d",
  [GetCoinHistoryIntervalEnum.Days30]: "30d",
  [GetCoinHistoryIntervalEnum.Year1]: "1y",
  [GetCoinHistoryIntervalEnum.Max]: "Max",
};

export const DEFAULT_INTERVAL: TimeInterval = GetCoinHistoryIntervalEnum.Days30;

// Array für UI-Iteration
export const INTERVALS: TimeInterval[] = [
  GetCoinHistoryIntervalEnum.Hours24,
  GetCoinHistoryIntervalEnum.Days7,
  GetCoinHistoryIntervalEnum.Days30,
  GetCoinHistoryIntervalEnum.Year1,
  GetCoinHistoryIntervalEnum.Max,
];
