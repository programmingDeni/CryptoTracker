import { Routes, Route, Link } from "react-router-dom";

import Homepage from "./views/Homepage";
import { RootRedirect } from "./auth/components/RootRedirect";
import { LoginView } from "./auth/views/LoginView";
import { AuthGuard } from "./auth/components/AuthGuard";
import CoinDetailsView from "./components/CoinDetails";

function App() {
  return (
    <div className="app-main">
      <Routes>
        <Route path="/" element={<RootRedirect />} />
        <Route
          path="/login"
          element={
            <AuthGuard requireAuth={false}>
              <LoginView />
            </AuthGuard>
          }
        />
        <Route path="/home" element={<Homepage />} />
        <Route path="/coin/:id" element={<CoinDetailsView />} />
      </Routes>
    </div>
  );
}

export default App;
