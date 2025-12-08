import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";

export default defineConfig({
  plugins: [react()],
  server: {
    host: "0.0.0.0", // Wichtig: auf alle Interfaces binden
    port: 5173,
    watch: {
      usePolling: true, // Nötig für Docker auf Windows/Mac
      interval: 1000, // Polling-Intervall in ms
    },
    hmr: {
      host: "localhost", // Host für WebSocket-Verbindung vom Browser
      port: 5173,
    },
  },
});
