import DashBoard from "./components/DashBoard.jsx";
import Login from "./components/Login";
import { useGlobalContext } from "./context/appContext.jsx";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
function App() {
  const { jwtToken } = useGlobalContext();
  return (
    <Router>
      <Routes>
        <Route
          path="/login"
          element={!jwtToken ? <Login /> : <Navigate to="/" />}
        />
        <Route
          path="/"
          element={jwtToken ? <DashBoard /> : <Navigate to="/login" />}
        />
      </Routes>
    </Router>
  );
}
export default App;
