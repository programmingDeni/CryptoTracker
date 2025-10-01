import type { Coin } from "../../api";
import CoinCard from "../cards/coinCard";

interface CoinListUIProps {
  coins: Coin[];
  onSelectCoin: (id: number) => void;
}
export default function CoinListUI({ coins, onSelectCoin }: CoinListUIProps) {
  return (
    <div>
      <ul style={{ listStyle: "none", padding: "10px 10px", margin: 10 }}>
        {coins.map((coin) => (
          <li
            key={coin.id}
            className="coinlist-row"
            onClick={() => onSelectCoin(coin.id!)}
          >
            <CoinCard coin={coin} />
          </li>
        ))}
      </ul>
    </div>
  );
}
