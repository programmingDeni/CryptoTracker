# CryptoTracker (work in progress)
CryptoTracker ist eine Fullstack-Anwendung zum Anzeigen und Verwalten von Kryptowährungen. Das Projekt besteht aus einem Java Spring Boot Backend und einem modernen React/TypeScript Frontend.

## Features
Übersicht der wichtigsten Kryptowährungen mit aktuellen Kursen und Marktdaten
Responsive Design: Optimiert für Desktop und Mobile
Detailansicht für einzelne Coins
Datenaktualisierung über die CoinGecko API
Moderne UI mit React, React Query und flexibler Komponentenstruktur
## Projektstruktur
...
CryptoTracker/ ├── backend/ # Spring Boot Backend (Java) │ 
                └── tracker/  
                  └── src/ │ 
                    └── main/ 
                    └── test/ 
              ├── cryptoFrontend/ # React Frontend (TypeScript)  
                └── src/ 
                  └── api/ # Automatisch generierte API-Types (OpenAPI)
                  └── assets/ 
                  └── components/
                  └── queries/ 
                  └── services/
                  └── utils/
                  └── views/ 
...

## Setup & Entwicklung
### Backend (Spring Boot)
Voraussetzungen: Java 17+, Maven
Konfiguration:
Lege eine Datei application-secret.properties mit deinen DB- und API-Zugangsdaten an.
Starten: cd backend/tracker mvn spring-boot:run
Frontend (React)
Voraussetzungen: Node.js, pnpm
Installieren: cd cryptoFrontend pnpm install
API-Types generieren (optional, nach Backend-Start): pnpm run generate:api
Starten: pnpm dev
API-Types automatisch generieren
Das Frontend nutzt automatisch generierte TypeScript-Types und API-Clients aus dem OpenAPI-Schema des Backends.
Nach Backend-Start kannst du die Types so generieren:

pnpm run generate:api


Lizenz
MIT License
