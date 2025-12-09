import { useState, useEffect, useRef } from "react";
//import type { Coin } from "../../api";

import { useCoins } from "../../queries/useCoinQueries";
import CoinListUI from "./CoinListUI";
import CoinListSearch from "./CoinListSearch";

interface CoinListViewProps {
  onSelectCoin: (id: number) => void;
}

export default function CoinListView({ onSelectCoin }: CoinListViewProps) {
  const { data: coins = [], isLoading, error } = useCoins();
  // SUCHE NACH COINS NACH NAMEN
  //state für suche
  const [search, setSearch] = useState("");
  //funktion zur suche
  const filteredCoins = coins.filter(
    (coin) =>
      coin.name!.toLowerCase().includes(search.toLowerCase()) ||
      coin.symbol!.toLowerCase().includes(search.toLowerCase())
  );

  // SORTIERUNG DER COINS
  const [sortField, setSortField] = useState<"name" | "currentPrice">("name");
  const [sortAsc, setSortAsc] = useState(true);
  //funktion zum sortieren
  const sortedCoins = [...filteredCoins].sort((a, b) => {
    let aValue = a[sortField];
    let bValue = b[sortField];

    // Für Strings (name, symbol) localeCompare, für Zahlen normal vergleichen
    if (typeof aValue === "string" && typeof bValue === "string") {
      return sortAsc
        ? aValue.localeCompare(bValue)
        : bValue.localeCompare(aValue);
    }
    if (typeof aValue === "number" && typeof bValue === "number") {
      return sortAsc ? aValue - bValue : bValue - aValue;
    }
    return 0;
  });

  //State für Sortiermenü
  const [showSortMenu, setShowSortMenu] = useState(false);
  // Ref für das Sortiermenü (für das schließen beim Klick außerhalb)
  const menuRef = useRef<HTMLDivElement>(null);

  const sortOptions = [
    { field: "name", asc: true, label: "Nach Name (aufsteigend)" },
    { field: "name", asc: false, label: "Nach Name (absteigend)" },
    { field: "currentPrice", asc: true, label: "Nach Preis (aufsteigend)" },
    { field: "currentPrice", asc: false, label: "Nach Preis (absteigend)" },
  ];

  useEffect(() => {
    if (!showSortMenu) return;
    function handleClick(event: MouseEvent) {
      if (menuRef.current && !menuRef.current.contains(event.target as Node)) {
        setShowSortMenu(false);
      }
    }
    document.addEventListener("mousedown", handleClick);
    return () => document.removeEventListener("mousedown", handleClick);
  }, [showSortMenu]);

  if (isLoading) return <div>Lade Coins...</div>;
  if (error) return <div>Fehler beim Laden!</div>;

  return (
    <div style={{ paddingLeft: "40px", paddingRight: "40px" }}>
      <div className="row">
        <CoinListSearch search={search} onSearch={setSearch} />
        <div style={{ position: "relative" }}>
          <button
            onClick={() => setShowSortMenu((v) => !v)}
            className="btn btn--primary"
            style={{
              height: "40px",
              width: "40px",
              borderRadius: "4px",
              cursor: "pointer",
            }}
            title="Sortieren"
          >
            ⇅
          </button>
          {showSortMenu && (
            <div
              ref={menuRef}
              style={{
                position: "absolute",
                top: "45px",
                right: 0,
                background: "#222",
                color: "color--primary",
                borderRadius: "8px",
                boxShadow: "0 2px 8px rgba(0,0,0,0.2)",
                zIndex: 10,
                minWidth: "180px",
                padding: "0.5rem 0",
              }}
            >
              {sortOptions.map((option) => (
                <div
                  key={option.field + option.asc}
                  style={{
                    padding: "0.5rem 1rem",
                    cursor: "pointer",
                    background:
                      sortField === option.field && sortAsc === option.asc
                        ? "#1976d2"
                        : "transparent",
                  }}
                  onClick={() => {
                    setSortField(option.field as "name" | "currentPrice");
                    setSortAsc(option.asc);
                    setShowSortMenu(false);
                  }}
                >
                  {option.label}
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
      <CoinListUI coins={sortedCoins} onSelectCoin={onSelectCoin} />
    </div>
  );
}
