import type { ChangeEvent } from "react";

interface CoinListSearchProps {
  search: string;
  onSearch: (value: string) => void;
}

export default function CoinListSearch({
  search,
  onSearch,
}: CoinListSearchProps) {
  return (
    <input
      type="text"
      placeholder="Suche Coin..."
      value={search}
      onChange={(e: ChangeEvent<HTMLInputElement>) => onSearch(e.target.value)}
      style={{
        margin: "1rem",
        padding: "10px 10px",
        width: "100%",
        boxSizing: "border-box",
      }}
    />
  );
}
