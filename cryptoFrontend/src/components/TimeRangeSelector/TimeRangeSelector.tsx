import {
  TIME_INTERVAL_LABELS,
  INTERVALS,
} from "../../types/timeIntervalLables";
import type { TimeInterval } from "../../types/timeIntervalLables";

interface TimeRangeSelectorProps {
  selectedInterval: TimeInterval;
  onIntervalChange: (interval: TimeInterval) => void;
  vertical?: boolean;
}

/**
 * Button-Gruppe zur Auswahl des Zeitraums für Preisverlauf
 */
export function TimeRangeSelector({
  selectedInterval,
  onIntervalChange,
  vertical = false,
}: TimeRangeSelectorProps) {
  return (
    <div className={`btn-group ${vertical ? "btn-group--vertical" : ""}`}>
      {INTERVALS.map((interval) => (
        <button
          key={interval}
          className={`btn btn--secondary btn--sm ${
            selectedInterval === interval ? "btn--active" : ""
          }`}
          onClick={() => onIntervalChange(interval)}
        >
          {TIME_INTERVAL_LABELS[interval]}
        </button>
      ))}
    </div>
  );
}
