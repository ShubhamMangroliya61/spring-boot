import React, { useEffect, useState } from "react";
import SideBar from "../components/SideBar";
import { useParams } from "react-router-dom";
import { useGlobalContext } from "../context/appContext";

function DisplayListOfProjects() {
  const { authFetch } = useGlobalContext();
  let { status } = useParams();
  const [listOfProjects, setListOfProjects] = useState([]);
  const [listOfProjectsToDisplay, setListOfProjectsToDisplay] = useState([]);

  const countProgress = (tasks) => {
    if (tasks.length == 0) return 0;
    let completedTasks = 0;
    tasks.forEach((task) => {
      if (task.status.toLowerCase() === "completed") completedTasks++;
    });
    return (completedTasks / tasks.length) * 100;
  };

  const extractInfo = () => {
    const extrectedArray = [];
    listOfProjects.forEach((project) => {
      if (
        status.toLowerCase() !== "all" &&
        status.toLowerCase() === project.status.toLowerCase()
      ) {
        const tempObj = {};
        tempObj.name = project.name;
        tempObj.status = project.status;
        const managers = project.members.filter(
          (member) => member.role.toLowerCase() === "manager"
        );
        tempObj.manager =
          managers.length > 0
            ? managers[0].employee.firstName +
              " " +
              managers[0].employee.lastName
            : "";
        tempObj.status = project.status;
        tempObj.progress = countProgress(project.tasks) + "%";
        tempObj.createdOn = project.createdOn;
        extrectedArray.push(tempObj);
      }
    });
    setListOfProjectsToDisplay(extrectedArray);
  };

  useEffect(() => {
    authFetch
      .get("/project/all")
      .then((res) => setListOfProjects(res.data))
      .catch((err) => console.log(err));
  }, []);

  useEffect(() => {
    extractInfo();
  }, [listOfProjects]);

  return (
    <div className="flex flex-wrap overflow-y-hidden">
      <div className="left w-[15%] h-svh">
        <SideBar />
      </div>
      <div className="right relative top-24 w-[85%] mx-auto">
        <div className="w-[95.5%] m-auto flex flex-row bg-gray-300/40 backdrop-blur-md rounded-md mb-5">
          <div className="w-[95%] text-center p-5 pb-10">
            <p className="text-gray-200 text-lg pb-5">All the projects</p>
            {/* <AdminLeaveRequestTable
              allPreviousLeaveRequests={leavesToDisplay}
            /> */}
          </div>
        </div>
      </div>
    </div>
  );
}

export default DisplayListOfProjects;
