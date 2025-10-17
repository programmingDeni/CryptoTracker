import {
  Chart as ChartJS,
  LineElement,
  PointElement,
  LinearScale,
  TimeScale,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import "chartjs-adapter-date-fns"; // Für Datum auf X-Achse
import { Line } from "react-chartjs-2";
import type { CoinPriceHistory } from "../../api";

ChartJS.register(
  LineElement,
  PointElement,
  LinearScale,
  TimeScale,
  Title,
  Tooltip,
  Legend
);

interface Props {
  data: CoinPriceHistory[];
}

export default function PriceChart({ data }: Props) {
  // Sortieren nach Zeit (optional, falls nicht sortiert)
  const sortedData = [...data].sort((a, b) => a.timestamp! - b.timestamp!);

  const chartData = {
    labels: sortedData.map((entry) => new Date(entry.timestamp!)),
    datasets: [
      {
        label: "Preis in USD",
        data: sortedData.map((entry) => entry.price),
        fill: false,
        borderColor: "rgba(75,192,192,1)",
        tension: 0.1,
      },
    ],
  };

  const options = {
    responsive: true,
    scales: {
      x: {
        type: "time" as const,
        time: {
          unit: "hour" as const, // oder 'day', je nach Daten
        },
        title: {
          display: true,
          text: "Zeitpunkt",
        },
      },
      y: {
        title: {
          display: true,
          text: "Preis (USD)",
        },
      },
    },
  };

  return <Line data={chartData} options={options} />;
}
