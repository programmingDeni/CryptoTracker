import CoinDetailsUi from "./CoinDetailsUi";

import { useCoins } from "../../queries/useCoinQueries";

interface CoinDetailsViewProps {
  id: number;
  onBack?: () => void;
}

export default function CoinDetailsView({ id, onBack }: CoinDetailsViewProps) {
  const { data: coins = [], isLoading, error } = useCoins();
  if (isLoading) return <div>Lade Coins...</div>;
  if (error) return <div>Fehler beim Laden!</div>;
  const coin = coins.find((c) => c.id === id);
  if (id === -1) return <div>Kein Coin ausgewählt!</div>;
  if (!coin) return <div>Coin nicht gefunden!</div>;
  return (
    <div>
      {onBack && <button onClick={onBack}>Zurück</button>}
      <CoinDetailsUi coin={coin} />
    </div>
  );
}
