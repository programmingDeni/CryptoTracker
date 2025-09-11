//type import

import "./CoinCard.css";
import type { Coin } from "../../../api";

interface CoinCardProps {
  coin: Coin;
}

export default function CoinCard(props: CoinCardProps) {
  const { coin } = props;
  return (
    <div className="coin-card">
      <img
        src={coin.image}
        alt={coin.name}
        style={{ width: 30, height: 30, flex: "0 0 30px" }}
      />
      <span style={{ width: 160, display: "inline-block" }}>{coin.name}</span>
      <span style={{ width: 80, display: "inline-block" }}>{coin.symbol}</span>
      <span style={{ width: 120, display: "inline-block", textAlign: "right" }}>
        {coin.currentPrice}
      </span>
      <span style={{ width: 120, display: "inline-block", textAlign: "right" }}>
        {coin.priceChange24hPerCent}
      </span>
      <span style={{ width: 140, display: "inline-block", textAlign: "right" }}>
        {coin.marketCap}
      </span>
    </div>
  );
}
