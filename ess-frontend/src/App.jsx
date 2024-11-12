import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import { useGlobalContext } from "./context/appContext";
import DashBoard from "./pages/DashBoard.jsx";
import Login from "./components/Login";
import Navbar from "./components/Navbar.jsx";
import AdminDashboard from "./pages/AdminDashboard.jsx";
import ProjectManagementDashboard from "./pages/ProjectManagementDashboard.jsx";
import AttendanceDetailsPage from "./pages/AttendanceDetailsPage.jsx";
import LeaveRequestPage from "./pages/LeaveRequestPage.jsx";
import DisplayLeaveRequests from "./pages/DisplayLeaveRequests.jsx";
import DisplayListOfProjects from "./pages/DisplayListOfProjects.jsx";
import ProjectTaksPage from "./pages/ProjectTaksPage.jsx";
import ListOfEmplyeesPage from "./pages/AdminPages/ListOfEmplyeesPage.jsx";
import ListOfTeamsPage from "./pages/AdminPages/ListOfTeamsPage.jsx";
import ListOfRolesPage from "./pages/AdminPages/ListOfRolesPage.jsx";
import ListOfHolidaysPage from "./pages/AdminPages/ListOfHolidaysPage.jsx";
import EmployeeProjectManagementDashboard from "./pages/EmployeeProjectManagementDashboard.jsx";
import MyTeamPage from "./pages/MyTeamPage.jsx";
import GetMailPage from "./pages/GetMailPage.jsx";
import UpdatePasswordPage from "./pages/UpdatePasswordPage.jsx";
import ProfilePage from "./components/Employees/ProfilePage.jsx";
import ResetPassPage from "./pages/ResetPassPage.jsx";



function App() {
  const { jwtToken, role, userRedirect } = useGlobalContext();

  const routes = [
    { path: "/login", element: <Login />, guard: "public" },
    {
      path: "/",
      element: role === "admin" ? <AdminDashboard /> : <DashBoard />,
      guard: "private",
    },
    {
      path: "/projectManagement/dashboard",
      element:
        role === "admin" ? (
          <ProjectManagementDashboard />
        ) : (
          <EmployeeProjectManagementDashboard />
        ),
      guard: "private",
    },
    {
      path: "/attendanceDetails",
      element: <AttendanceDetailsPage />,
      guard: "private",
    },
    { path: "/leaveRequest", element: <LeaveRequestPage />, guard: "private" },
    {
      path: "/displayLeaveRequest",
      element: <DisplayLeaveRequests />,
      guard: "admin",
    },
    {
      path: "/projectManagementDashboard",
      element: <ProjectManagementDashboard />,
      guard: "private",
    },
    {
      path: "/listOfProjects/:status",
      element: <DisplayListOfProjects />,
      guard: "private",
    },
    {
      path: "/listOfProjects/managers/:name",
      element: <DisplayListOfProjects />,
      guard: "private",
    },
    {
      path: "/listOfTasks/:projectId",
      element: <ProjectTaksPage />,
      guard: "private",
    },
    { path: "/employees", element: <ListOfEmplyeesPage />, guard: "admin" },
    { path: "/teams", element: <ListOfTeamsPage />, guard: "admin" },
    { path: "/roles", element: <ListOfRolesPage />, guard: "admin" },
    { path: "/holidays", element: <ListOfHolidaysPage />, guard: "private" },
    { path: "/myTeam", element: <MyTeamPage />, guard: "private" },
    { path: "/getEmail", element: <GetMailPage />, guard: "public" },
    {
      path: "/updatePassword",
      element: <UpdatePasswordPage />,
      guard: "public",
    },
    { path: "/profile/:userId", element: <ProfilePage />, guard: "private" },
    { path: "/reset_pass", element: <ResetPassPage />, guard: "private" },
  ];


  const PublicRoute = ({ element }) => {
    if (!jwtToken) {

      return element;
    } else {
      if (userRedirect) {
        return <Navigate to="/reset_pass" />;
      } else {

        return <Navigate to="/" />;
      }
    }
  };

  const PrivateRoute = ({ element }) => {
    if (jwtToken) {
      if (userRedirect) {

        return <Navigate to="/reset_pass" />;
      } else {

        return element;
      }
    } else {

      return <Navigate to="/login" />;
    }
  };

  const AdminRoute = ({ element }) => jwtToken && role === "admin" ? element : <Navigate to="/login" />;

  return (
    <div className="absolute bg-black min-h-svh">
      <div className="w-screen relative z-10">{jwtToken && <Navbar />}</div>
      <Router>
        <Routes>
          {routes.map(({ path, element, guard }) => {
            if (guard === "public")
              return (
                <Route
                  key={path}
                  path={path}
                  element={<PublicRoute element={element} />}
                />
              );
            if (guard === "admin")
              return (
                <Route
                  key={path}
                  path={path}
                  element={<AdminRoute element={element} />}
                />
              );
            return (
              <Route
                key={path}
                path={path}
                element={<PrivateRoute element={element} />}
              />
            );
          })}
        </Routes>
      </Router>
    </div>
  );
}

export default App;
