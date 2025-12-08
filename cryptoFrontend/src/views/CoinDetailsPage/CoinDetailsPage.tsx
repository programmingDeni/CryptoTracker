import { useParams, useNavigate } from "react-router-dom";
import CoinDetailsView from "../../components/CoinDetails/CoinDetailsView";
import Navbar from "../../components/Navbar/Navbar";

export default function CoinDetailsPage() {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const coinId = Number(id);

  return (
    <div className="mobilePageWrapper">
      <Navbar />
      <CoinDetailsView id={coinId} />
    </div>
  );
}
