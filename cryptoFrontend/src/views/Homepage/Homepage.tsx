import CoinListView from "../../components/CoinList";
import CoinDetailsView from "../../components/CoinDetails";
import { useState } from "react";
import { useIsMobile } from "../../utils/useIsMobile";
import Navbar from "../../components/Navbar/Navbar";
import { useNavigate } from "react-router-dom";

export default function Homepage() {
  const [selectedCoinId, setSelectedCoinId] = useState<number>(-1);
  const navigate = useNavigate();
  const isMobile = useIsMobile();

  // ViewName für Navigation in mobil
  let viewName = "Übersicht";
  if (isMobile && selectedCoinId !== -1) viewName = "Details";

  console.log("Homepage: isMobile", isMobile);

  // Mobile: Nur Details anzeigen, wenn Coin gewählt
  if (isMobile) {
    return (
      <div className="mobilePageWrapper">
        <Navbar />
        <CoinListView onSelectCoin={(id) => navigate(`/coin/${id}`)} />
      </div>
    );
  }

  return (
    <div className="pageWrapper">
      <Navbar />
      <div className="twoColumn twoColumn--withDivider">
        <div className="twoColumn__left twoColumn__scrollable">
          <CoinListView onSelectCoin={setSelectedCoinId} />
        </div>
        <div className="twoColumn__right">
          <CoinDetailsView id={selectedCoinId} />
        </div>
      </div>
    </div>
  );
}
