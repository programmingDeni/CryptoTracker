//type import
import "./CoinCard.css";
import type { Coin } from "../../../api";

interface CoinCardProps {
  coin: Coin;
  hoverable?: boolean;
}

export default function CoinCard({ coin, hoverable = true }: CoinCardProps) {
  const isPositive = coin.priceChange24hPerCent! >= 0;

  return (
    <div className={`coin-card${hoverable ? " hoverable" : ""}`}>
      <div className="coin-info">
        <img src={coin.image} alt={coin.name} className="coin-icon" />
        <div className="coin-text">
          <div className="coin-name">{coin.name}</div>
          <div className="coin-label">Aktueller Kurs:</div>
        </div>
      </div>

      <div className="coin-price">
        <div className={`coin-change ${isPositive ? "positive" : "negative"}`}>
          {coin.priceChange24hPerCent}%
          <span className="arrow">{isPositive ? "↑" : "↓"}</span>
        </div>
        <div className="coin-value">{coin.currentPrice}$</div>
      </div>
    </div>
  );
}
