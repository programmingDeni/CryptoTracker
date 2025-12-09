import type { CoinPriceHistory } from "../../api";
import Linechart from "../Linechart/Linechart";

interface CoinPriceHistoryUIProps {
  coinPriceHistory: CoinPriceHistory[];
}

export default function CoinPriceHistoryUI(props: CoinPriceHistoryUIProps) {
  const { coinPriceHistory } = props;
  if (coinPriceHistory.length === 0) return <div>Keine Daten vorhanden</div>;
  console.log(coinPriceHistory);
  return (
    <div>
      <h3>CoinPriceHistoryUI</h3>
      <Linechart data={coinPriceHistory} />
    </div>
  );
}
