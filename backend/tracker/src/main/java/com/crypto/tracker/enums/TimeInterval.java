package com.crypto.tracker.enums;

/**
 * Zeitintervalle für historische Preisdaten.
 * Die CoinGecko API bestimmt die Granularität automatisch:
 * - 1 Tag: 5-Minuten-Intervalle
 * - 1-90 Tage: Stündliche Daten
 * - >90 Tage: Tägliche Daten
 */
public enum TimeInterval {
    HOUR_1(1, "1h"), // Letzte Stunde (wird aus 1-Tag-Daten gefiltert)
    HOURS_24(1, "24h"), // Letzte 24h (API: 1 Tag = 5-min Intervalle)
    DAYS_7(7, "7d"), // Letzte Woche (stündliche Daten)
    DAYS_30(30, "30d"), // Letzter Monat (stündliche Daten)
    YEAR_1(365, "1y"), // Letztes Jahr (tägliche Daten)
    MAX(0, "max"); // Alle verfügbaren Daten

    private final int days;
    private final String label;

    TimeInterval(int days, String label) {
        this.days = days;
        this.label = label;
    }

    /**
     * Anzahl der Tage für den CoinGecko API-Aufruf.
     * 0 bedeutet "max" (alle verfügbaren Daten).
     */
    public int getDays() {
        return days;
    }

    /**
     * Label für die Anzeige im Frontend.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gibt den API-Parameter zurück ("max" oder Anzahl Tage als String).
     */
    public String toApiParam() {
        return days == 0 ? "max" : String.valueOf(days);
    }
}