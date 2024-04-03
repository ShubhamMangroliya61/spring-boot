import React, { useEffect, useState } from "react";
import { useGlobalContext } from "../context/appContext";
import SideBar from "../components/SideBar";
import CustomAlert from "../components/utils/CustomAlert";
import ListOfEmployeesTable from "../components/Employees/ListOfEmployeesTable";
import MyTeamEmployeesTable from "../components/MyTeam/MyTeamEmployeesTable";
import DisplayLeaveRequests from "./DisplayLeaveRequests";

function MyTeamPage() {
  const { authFetch, showAlert, alert, userId, role } = useGlobalContext();
  const [listOfEmployees, setListOfEmployees] = useState([]);
  const [currentEmployee, setCurrentEmployee] = useState({});
  const [listOfEmployeesToDisplay, setListOfEmployeesToDisplay] = useState([]);
  const [listOfLeaveRequest, setListOfLeaveRequest] = useState([]);
  const [listOfLeaveRequestToDisplay, setListOfLeaveRequestToDisplay] =
    useState([]);
  const [displayReq, setDisplayReq] = useState(false);
  const [isChanged, setIsChanged] = useState(false);

  useEffect(() => {
    authFetch
      .get(`/leave/team/${currentEmployee?.team?.id}/getAll`)
      .then((res) => {
        setListOfLeaveRequest(res.data);
        // console.log("Req:", res.data);
      })
      .catch((err) => console.log(err));
  }, [currentEmployee, isChanged]);

  useEffect(() => {
    authFetch
      .get("/employee/getCurrent")
      .then((res) => setCurrentEmployee(res.data))
      .catch((err) => console.log(err));
  }, []);

  useEffect(() => {
    authFetch
      .get(`/employee/team/${currentEmployee?.team?.id}/all`)
      .then((res) => setListOfEmployees(res.data))
      .catch((err) => console.log(err));
  }, [currentEmployee]);

  useEffect(() => {
    const tempArray = [];
    listOfEmployees.forEach((employee) => {
      const tempObj = {};
      tempObj.id = employee.id;
      tempObj.name = employee.firstName + " " + employee.lastName;
      tempObj.email = employee.email;
      tempObj.totalLeavesTaken = employee.totalLeavesTaken;
      tempObj.totalLeavesLeft = employee.totalLeavesLeft;
      tempObj.role = employee.role.name;
      tempObj.team = employee.team.name;
      tempArray.push(tempObj);
    });
    setListOfEmployeesToDisplay(tempArray);
  }, [listOfEmployees]);

  useEffect(() => {
    setListOfLeaveRequestToDisplay(listOfLeaveRequest);
    // setDisplayReq(true);
  }, [listOfLeaveRequest, role]);

  useEffect(() => {
    if (role.toLowerCase() === "admin" || role.toLowerCase() === "manager") {
      setDisplayReq(true);
    }
  }, []);

  return (
    <div className="absolute overflow-x-hidden overflow-y-scroll h-full w-full bg-black flex flex-row">
      <div className="left relative w-[15%]">
        <SideBar />
      </div>
      <div className="right w-[85%] h-screen">
        <div className="relative top-24">
          {showAlert && (
            <div className="absolute right-10 z-50">
              <CustomAlert />
            </div>
          )}
          <div className="w-[95.5%] mx-auto flex flex-col align-middle items-center justify-center bg-gray-800 backdrop-blur-md rounded-md">
            <div className="w-[95%] py-8">
              <p className="text-white text-base font-semibold mb-3">My team</p>
              <MyTeamEmployeesTable
                listOfEmployees={listOfEmployeesToDisplay}
              />
            </div>
          </div>
          {displayReq ? (
            <div className="w-full mx-auto flex flex-col align-middle items-center justify-center backdrop-blur-md rounded-md mb-5">
              <div className="w-full py-8">
                <DisplayLeaveRequests
                  allLeaveRequestsProps={listOfLeaveRequestToDisplay}
                  heading={"All the leave requests in team"}
                  setIsChanged
                />
              </div>
            </div>
          ) : (
            ""
          )}
        </div>
      </div>
    </div>
  );
}

export default MyTeamPage;
