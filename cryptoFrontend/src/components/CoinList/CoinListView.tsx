import type { Coin } from "../../api";

import { useCoins } from "../../queries/useCoinQueries";
import CoinListUI from "./CoinListUI";

export default function CoinListView() {
  const { data: coins = [], isLoading, error } = useCoins();
  if (isLoading) return <div>Lade Coins...</div>;
  if (error) return <div>Fehler beim Laden!</div>;

  return (
    <div>
      <h1>Hier wird die CoinListView entstehen</h1>
      <CoinListUI coins={coins} />
    </div>
  );
}
