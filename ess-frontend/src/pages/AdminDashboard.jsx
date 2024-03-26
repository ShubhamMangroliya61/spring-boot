import React from "react";
import DisplayLeaveRequests from "./DisplayLeaveRequests";
import CreateNewPage from "./CreateNewPage";
import SideBar from "../components/SideBar";

function AdminDashboard() {
  return (
    <div className="absolute overflow-x-hidden overflow-y-scroll h-full w-full bg-black">
      <div className="flex flex-row">
        <div className="left relative w-[15%]">
          <SideBar />
        </div>
        <div className="right w-[85%] h-screen">
          <div className="relative top-12">
            <DisplayLeaveRequests />
            {/* <CreateNewPage /> */}
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminDashboard;
