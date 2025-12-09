import { useState } from "react";
import CoinPriceHistoryUI from "./CoinPriceHistoryUI";
import { useCoinPriceHistory } from "../../queries/useCoinPriceHistoryQueries";
// Time Interval Component and Type
import { TimeRangeSelector } from "../TimeRangeSelector";
import {
  DEFAULT_INTERVAL,
  type TimeInterval,
} from "../../types/timeIntervalLables";

//hat ein mandatory coinId prop
interface CoinPriceHistoryProps {
  coinId: number;
}
/**
 * View-Komponente für Preisverlauf mit Zeitraum-Auswahl
 */
export default function CoinPriceHistoryView(props: CoinPriceHistoryProps) {
  const [interval, setInterval] = useState<TimeInterval>(DEFAULT_INTERVAL);
  const { coinId } = props;
  const {
    data: coinPriceHistory = [],
    isLoading,
    error,
  } = useCoinPriceHistory(coinId, interval);

  if (isLoading) return <div>Lade CoinPriceHistory...</div>;
  if (error) return <div>Fehler beim Laden!</div>;
  console.log(
    "CoinPriceHistoryView interval",
    interval,
    "pricehistory",
    coinPriceHistory
  );
  return (
    <div className="stack stack--sm">
      <div className="row row--end">
        <TimeRangeSelector
          selectedInterval={interval}
          onIntervalChange={setInterval}
        />
      </div>
      <CoinPriceHistoryUI coinPriceHistory={coinPriceHistory} />
    </div>
  );
}
