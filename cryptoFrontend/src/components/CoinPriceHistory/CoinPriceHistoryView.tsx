import CoinPriceHistoryUI from "./CoinPriceHistoryUI";

import { useCoinPriceHistory } from "../../queries/useCoinPriceHistoryQueries";

//hat ein mandatory coinId prop
interface CoinPriceHistoryProps {
  coinId: number;
}

export default function CoinPriceHistoryView(props: CoinPriceHistoryProps) {
  const { coinId } = props;
  const {
    data: coinPriceHistory = [],
    isLoading,
    error,
  } = useCoinPriceHistory(coinId);

  if (isLoading) return <div>Lade CoinPriceHistory...</div>;
  if (error) return <div>Fehler beim Laden!</div>;
  return (
    <div>
      <CoinPriceHistoryUI coinPriceHistory={coinPriceHistory} />
    </div>
  );
}
