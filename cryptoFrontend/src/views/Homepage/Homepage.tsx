import CoinListView from "../../components/CoinList";
import CoinDetailsView from "../../components/CoinDetails";
import "./Homepage.css";
import { useState } from "react";
import { useIsMobile } from "../../utils/useIsMobile";
import NavbarUI from "../../components/Navbar/NavbarUI";

export default function Homepage() {
  const [selectedCoinId, setSelectedCoinId] = useState<number>(-1);
  const isMobile = useIsMobile();

  // ViewName für Navigation in mobil
  let viewName = "Übersicht";
  if (isMobile && selectedCoinId !== -1) viewName = "Details";

  // Mobile: Nur Details anzeigen, wenn Coin gewählt
  if (isMobile && selectedCoinId !== -1) {
    return (
      <>
        <NavbarUI viewName={viewName} />
        <CoinDetailsView
          id={selectedCoinId}
          onBack={() => setSelectedCoinId(-1)}
        />
      </>
    );
  }

  return (
    <>
      <NavbarUI viewName={viewName} />
      <div className="homepage-container">
        <div className="coinlist-wrapper">
          <CoinListView onSelectCoin={setSelectedCoinId} />
        </div>
        <div className="other-wrapper">
          <CoinDetailsView id={selectedCoinId} />
        </div>
      </div>
    </>
  );
}
