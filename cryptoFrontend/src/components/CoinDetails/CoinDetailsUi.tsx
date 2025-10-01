import type { Coin } from "../../api";
import CoinCard from "../cards/coinCard";

interface CoinDetailsUiProps {
  coin: Coin;
}

export default function CoinDetailsUi(props: CoinDetailsUiProps) {
  const { coin } = props;
  return (
    <div>
      <CoinCard coin={coin} hoverable={false} />
      {/* <PreisVerlauf coin={coin} /> */}
    </div>
  );
}
