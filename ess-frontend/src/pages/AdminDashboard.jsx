import React from "react";
import AdminSideBar from "../components/AdminSideBar";
import DisplayLeaveRequests from "./DisplayLeaveRequests";
import CreateNewPage from "./CreateNewPage";

function AdminDashboard() {
  return (
    <div className="absolute overflow-x-hidden overflow-y-scroll h-full w-full bg-black">
      <div className="flex flex-row">
        <div className="left relative w-[15%]">
          <AdminSideBar />
        </div>
        <div className="right w-[85%] h-screen">
          <div className="relative top-20 text-white flex justify-center text-3xl">
            <h1>Admin Dashboard</h1>
          </div>
          <div>
            {/* <DisplayLeaveRequests /> */}
            <CreateNewPage />
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminDashboard;
