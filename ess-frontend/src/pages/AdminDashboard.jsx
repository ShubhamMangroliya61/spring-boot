import React, { useState, useEffect } from "react";
import DisplayLeaveRequests from "./DisplayLeaveRequests";
import CreateNewPage from "./CreateNewPage";
import SideBar from "../components/SideBar";
import { useGlobalContext } from "../context/appContext";

function AdminDashboard() {
  const { authFetch } = useGlobalContext();
  const [allLeaveRequests, setAllLeaveRequests] = useState([]);

  // useEffect(() => {
  //   authFetch
  //     .get("/leave/getAll")
  //     .then((res) => {
  //       setLeavesToDisplay(res.data);
  //       setAllLeaveRequests(res.data);
  //     })
  //     .catch((err) => console.log(err.data));
  // }, []);
  useEffect(() => {
    authFetch
      .get("/leave/getAll")
      .then((res) => {
        setAllLeaveRequests(res.data);
      })
      .catch((err) => console.log(err.data));
  }, []);

  return (
    <div className="absolute overflow-x-hidden overflow-y-scroll h-full w-full bg-black">
      <div className="flex flex-row">
        <div className="left relative w-[15%]">
          <SideBar />
        </div>
        <div className="right w-[85%] h-screen">
          <div className="relative top-12">
            <DisplayLeaveRequests
              allLeaveRequestsProps={allLeaveRequests}
              heading={"Toal leave requests"}
            />
            {/* <CreateNewPage /> */}
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminDashboard;
