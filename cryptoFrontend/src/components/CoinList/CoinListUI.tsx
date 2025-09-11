import type { Coin } from "../../api";
import CoinCard from "../cards/coinCard";
interface CoinListUIProps {
  coins: Coin[];
}
export default function CoinListUI(props: CoinListUIProps) {
  const { coins } = props;
  return (
    <div>
      <div
        style={{
          display: "flex",
          alignItems: "center",
          gap: "1rem",
          fontWeight: "bold",
          marginBottom: 8,
        }}
      >
        <span style={{ width: 40 }}></span>
        <span style={{ width: 160 }}>Name</span>
        <span style={{ width: 80 }}>Symbol</span>
        <span style={{ width: 120, textAlign: "right" }}>Preis</span>
        <span style={{ width: 120, textAlign: "right" }}>24h %</span>
        <span style={{ width: 140, textAlign: "right" }}>Market Cap</span>
      </div>
      <ul style={{ listStyle: "none", padding: 0, margin: 10 }}>
        {coins.map((coin) => (
          <li key={coin.id}>
            <CoinCard coin={coin} />
          </li>
        ))}
      </ul>
    </div>
  );
}
