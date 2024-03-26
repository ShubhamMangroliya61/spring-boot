import React, { useEffect, useState } from "react";
import ListOfTeamsTable from "../../components/Teams/ListOfTeamsTable";
import { useGlobalContext } from "../../context/appContext";
import AddTeamModal from "../../components/Teams/AddTeamModal";
import SideBar from "../../components/SideBar";

function ListOfTeamsPage() {
  const { authFetch, showAlert, alert } = useGlobalContext();
  const [listOfAllTeams, setListOfAllTeams] = useState([]);
  const [listOfTeamsToDisplay, setListOfTeamsToDisplay] = useState([]);
  const [isAdding, setIsAdding] = useState(false);
  const [isChanged, setIsChanged] = useState(false);
  const [employeesInTeam, setEmployeesInTeam] = useState(new Map());

  const handleOpen = () => {
    setIsAdding(true);
  };

  const handleClose = () => {
    setIsAdding(false);
  };

  useEffect(() => {
    authFetch
      .get("/team/all")
      .then((res) => setListOfAllTeams(res.data))
      .catch((err) => console.log(err));
  }, [isChanged]);

  useEffect(() => {
    listOfAllTeams.forEach((team) => {
      authFetch
        .get(`/employee/${team.id}/all`)
        .then((res) => {
          let prevMap = employeesInTeam;
          prevMap.set(team.id, res.data);
          setEmployeesInTeam(prevMap);
        })
        .catch((err) => {
          console.log(err);
        });

      //   while (!isReqDone) {}

      tempObj.name = team.name;
      tempObj.members = members.length;
      managers = members.filter(
        (member) =>
          member.role.name.toLowerCase() === "admin" ||
          member.role.name.toLowerCase() === "manager"
      );
      tempObj.manager = managers.length > 0 ? managers[0] : "";

      tempArray.push(tempObj);
    });
    setListOfTeamsToDisplay(tempArray);
  }, [listOfAllTeams]);

  // useEffect(() => {
  //     const tempArray = []
  //     listOfAllTeams.forEach((team) => {
  //     const tempobj = {};
  //     }
  // })

  return (
    <div className="absolute overflow-x-hidden overflow-y-scroll h-full w-full bg-black flex flex-row">
      {/* <AddTeamModal
        open={isAdding}
        setOpen={setIsAdding}
        handleClose={handleClose}
        handleOpen={handleOpen}
        setIsChanged={setIsChanged}
      /> */}
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
          <div className="w-[95.5%] m-auto flex flex-col align-middle items-center justify-center bg-gray-300/40 backdrop-blur-md rounded-md mb-5">
            <div className="w-[95%] py-8">
              <ListOfTeamsTable listOfTeams={listOfTeamsToDisplay} />
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

export default ListOfTeamsPage;
