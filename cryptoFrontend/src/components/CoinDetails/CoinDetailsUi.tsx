import type { Coin } from "../../api";

import CoinCard from "../cards/coinCard";
import CoinPriceHistory from "../CoinPriceHistory";

interface CoinDetailsUiProps {
  coin: Coin;
  isMobile?: boolean;
}

export default function CoinDetailsUi(props: CoinDetailsUiProps) {
  const { coin, isMobile = false } = props;

  console.log("CoinDetailsUi: Coin", isMobile);

  return (
    <div className="pageWrapper stack stack--md">
      <CoinCard coin={coin} hoverable={false} />
      {/* <PreisVerlauf coin={coin} /> */}
      <CoinPriceHistory coinId={coin.id!} />
    </div>
  );
}
