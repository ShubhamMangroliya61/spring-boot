import React, { useEffect, useState } from "react";
import { useGlobalContext } from "../../context/appContext";
import SideBar from "../../components/SideBar";
import ListOfEmployeesTable from "../../components/Employees/ListOfEmployeesTable";
import AddEmployeeMoal from "../../components/Employees/AddEmployeeMoal";
import CustomAlert from "../../components/utils/CustomAlert";
import UpdateEmployeeModal from "../../components/Employees/UpdateEmployeeModal";

function ListOfEmplyeesPage() {
  const { authFetch, showAlert, alert } = useGlobalContext();
  const [listOfAllEmployees, setListOfAllEmployees] = useState([]);
  const [listOfEmployeesToDisplay, setListOfEmployeesToDisplay] = useState([]);
  const [isAdding, setIsAdding] = useState(false);
  const [allTeams, setAllTeams] = useState([]);
  const [allRoles, setAllRoles] = useState([]);
  const [isChanged, setIsChanged] = useState(false);
  const [selectedEmployee, setSelectedEmployee] = useState({});
  const [isUpdating, setIsUpdating] = useState(false);

  const handleOpen = () => {
    setIsAdding(true);
  };

  const handleClose = () => {
    setIsAdding(false);
  };

  const handleUpdateOpen = () => {
    setIsUpdating(true);
  };

  const handleUpdateClose = () => {
    setIsUpdating(false);
  };

  useEffect(() => {
    authFetch
      .get("/role/all")
      .then((res) => {
        setAllRoles(res.data);
      })
      .catch((err) => console.log(err));

    authFetch
      .get("/team/all")
      .then((res) => {
        setAllTeams(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  useEffect(() => {
    authFetch
      .get("/employee/all")
      .then((res) => setListOfAllEmployees(res.data))
      .catch((err) => console.log(err));
  }, [isChanged]);

  useEffect(() => {
    const tempArray = [];
    listOfAllEmployees.forEach((employee) => {
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
  }, [listOfAllEmployees]);

  return (
    <div className="absolute overflow-x-hidden overflow-y-scroll h-full w-full bg-black flex flex-row">
      <AddEmployeeMoal
        open={isAdding}
        setOpen={setIsAdding}
        handleClose={handleClose}
        handleOpen={handleOpen}
        allRoles={allRoles}
        allTeams={allTeams}
        setIsChanged={setIsChanged}
      />
      <UpdateEmployeeModal
        allRoles={allRoles}
        allTeams={allTeams}
        employee={selectedEmployee}
        handleClose={handleUpdateClose}
        handleOpen={handleUpdateOpen}
        open={isUpdating}
        setIsChanged={setIsChanged}
        setOpen={setIsUpdating}
      />
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
          <div className="w-[95.5%] m-auto flex flex-col align-middle justify-center bg-gray-800 backdrop-blur-md rounded-md mb-5">
            <div className="w-[95%] pb-8 mx-auto">
              <p className="text-white text-base font-semibold my-3">
                Employees
              </p>
              <ListOfEmployeesTable
                listOfEmployees={listOfEmployeesToDisplay}
                setSelectedEmployee={setSelectedEmployee}
                handleOpen={handleUpdateOpen}
              />
              <button
                className="bg-blue-400/70 p-2 mt-4 text-sm text-black rounded-md hover:bg-blue-200 duration-300"
                onClick={handleOpen}
              >
                Add +
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ListOfEmplyeesPage;
