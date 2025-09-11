import { Routes, Route, Link } from "react-router-dom";

import Homepage from "./views/Homepage";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Homepage />} />
      </Routes>
    </>
  );
}

export default App;
