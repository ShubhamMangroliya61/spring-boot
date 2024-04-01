import React, { useState, useEffect } from "react";
import DisplayLeaveRequests from "./DisplayLeaveRequests";
import CreateNewPage from "./CreateNewPage";
import SideBar from "../components/SideBar";
import { useGlobalContext } from "../context/appContext";
import CustomAlert from "../components/utils/CustomAlert";

function AdminDashboard() {
  const { authFetch, showAlert } = useGlobalContext();
  const [allLeaveRequests, setAllLeaveRequests] = useState([]);
  const [isChanged, setIsChanged] = useState(false);

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
  }, [isChanged]);

  return (
    <div className="absolute overflow-x-hidden overflow-y-scroll h-full w-full bg-black">
      <div className="flex flex-row">
        <div className="left relative w-[15%]">
          <SideBar />
        </div>
        <div className="right w-[85%] h-screen">
          <div className="relative top-12">
            {showAlert && (
              <div className="absolute right-10 z-50">
                <CustomAlert />
              </div>
            )}
            <DisplayLeaveRequests
              allLeaveRequestsProps={allLeaveRequests}
              heading={"Toal leave requests"}
              setIsChanged
            />
            {/* <CreateNewPage /> */}
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminDashboard;
