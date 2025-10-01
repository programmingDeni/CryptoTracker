import react, { useState } from "react";
import type { Coin } from "../../api";

import { useCoins } from "../../queries/useCoinQueries";
import CoinListUI from "./CoinListUI";

interface CoinListViewProps {
  onSelectCoin: (id: number) => void;
}

export default function CoinListView({ onSelectCoin }: CoinListViewProps) {
  const { data: coins = [], isLoading, error } = useCoins();
  if (isLoading) return <div>Lade Coins...</div>;
  if (error) return <div>Fehler beim Laden!</div>;

  return (
    <div>
      <CoinListUI coins={coins} onSelectCoin={onSelectCoin} />
    </div>
  );
}
