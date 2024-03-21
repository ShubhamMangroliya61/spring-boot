import React from "react";
import AdminSideBar from "../components/AdminSideBar";
import Board from "../components/ProjectTaskComponents/Board";

function ProjectTaksPage() {
  return (
    <div className="h-full overflow-x-hidden overflow-y-hidden w-full bg-black">
      <div className="flex flex-row">
        <div className="left relative w-[15%]">
          <AdminSideBar />
        </div>
        <div className="right w-[85%] h-screen">
          <div className="relative top-20 text-white flex justify-center text-3xl">
            <h1>Project tasks</h1>
          </div>
          <div className="relative top-20 h-full ml-5">
            <Board />
          </div>
        </div>
      </div>
    </div>
  );
}

export default ProjectTaksPage;
