import CoinDetailsUi from "./CoinDetailsUi";

import { useCoins } from "../../queries/useCoinQueries";

import { useParams } from "react-router-dom";

import { useIsMobile } from "../../utils/useIsMobile";
import Navbar from "../Navbar";

interface CoinDetailsViewProps {
  id?: number;
}

export default function CoinDetailsView({ id: propId }: CoinDetailsViewProps) {
  //id entweder aus props oder url
  const { id: paramId } = useParams<{ id: string }>();
  const id = propId ?? (paramId ? parseInt(paramId) : -1);

  const isMobile = useIsMobile();

  const showNavbar = isMobile && paramId !== undefined;

  const { data: coins = [], isLoading, error } = useCoins();

  if (isLoading) return <div>Lade Coins...</div>;
  if (error) return <div>Fehler beim Laden!</div>;

  const coin = coins.find((c) => c.id === id);

  if (id === -1) return <div>Kein Coin ausgewählt!</div>;
  if (!coin) return <div>Coin nicht gefunden!</div>;

  console.log("CoinDetailsView: Coin", coin);
  return (
    <div>
      {showNavbar && <Navbar />}
      <CoinDetailsUi coin={coin} />
    </div>
  );
}
