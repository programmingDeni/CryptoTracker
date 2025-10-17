import type { Coin } from "../../api";

import CoinCard from "../cards/coinCard";
import CoinPriceHistory from "../CoinPriceHistory";

interface CoinDetailsUiProps {
  coin: Coin;
}

export default function CoinDetailsUi(props: CoinDetailsUiProps) {
  const { coin } = props;

  return (
    <div>
      <CoinCard coin={coin} hoverable={false} />
      {/* <PreisVerlauf coin={coin} /> */}
      <CoinPriceHistory coinId={coin.id!} />
    </div>
  );
}
