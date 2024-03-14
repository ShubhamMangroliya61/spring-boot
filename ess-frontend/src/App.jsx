import { useEffect } from "react";
import DashBoard from "./pages/DashBoard.jsx";
import Login from "./components/Login";
import Navbar from "./components/Navbar.jsx";
import { useGlobalContext } from "./context/appContext.jsx";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import AdminDashboard from "./pages/AdminDashboard.jsx";
import ProjectManagementDashboard from "./pages/ProjectManagementDashboard.jsx";

function App() {
  const { jwtToken, role } = useGlobalContext();

  return (
    <div className="absolute bg-black min-h-svh">
      <div className="w-screen relative z-10">{jwtToken && <Navbar />}</div>
      <Router>
        <Routes>
          <Route
            path="/login"
            element={!jwtToken ? <Login /> : <Navigate to="/" />}
          />
          <Route
            path="/"
            element={
              jwtToken ? (
                role === "admin" ? (
                  <AdminDashboard />
                ) : (
                  <DashBoard />
                )
              ) : (
                <Navigate to="/login" />
              )
            }
          />
          <Route
            path="/projectManagement/dashdoard"
            element={
              jwtToken ? (
                role === "admin" ? (
                  <ProjectManagementDashboard />
                ) : (
                  <DashBoard />
                )
              ) : (
                <Navigate to="/login" />
              )
            }
          />
        </Routes>
      </Router>
    </div>
  );
}
export default App;
