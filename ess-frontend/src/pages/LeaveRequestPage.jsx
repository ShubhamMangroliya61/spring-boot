import React, { useState, useEffect } from "react";
import { useGlobalContext } from "../context/appContext";
import RequestLeave from "../components/RequestLeave";
import LeaveCount from "../components/LeaveCount";
import LeaveRequestTable from "../components/LeaveRequestTable";
import SideBar from "../components/SideBar";
import Box from "@mui/system/Box";
import { FocusTrap } from "@mui/base/FocusTrap";

import RequestLeaveModal from "../components/RequestLeaveModal";

function LeaveRequestPage() {
  const { authFetch } = useGlobalContext();

  const [allPreviousLeaveRequests, setAllPreviousLeaveRequests] = useState([]);

  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  useEffect(() => {
    authFetch
      .get("/leave")
      .then((res) => {
        setAllPreviousLeaveRequests(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className="h-screen w-screen bg-black overflow-hidden">
      <RequestLeaveModal
        open={open}
        setOpen={setOpen}
        handleClose={handleClose}
        handleOpen={handleOpen}
      />

      <div className="flex flex-row">
        <div className="left w-[15%]">
          <SideBar />
        </div>
        <div className="right w-[85%] h-screen flex flex-wrap">
          <div className="w-[97.5%] mx-auto">
            <LeaveCount allPreviousLeaveRequests={allPreviousLeaveRequests} />
          </div>
          <div className="w-[93%] mx-auto bg-gray-300/40 backdrop-blur-md rounded-md px-16 mb-5">
            <div className="w-full">
              <p className="text-gray-200 text-lg py-5">
                all the prev requests &nbsp; &nbsp; &nbsp;
                <button
                  className="bg-blue-400/70 p-2 mt-2 text-sm text-black rounded-md hover:bg-blue-200 duration-300"
                  onClick={handleOpen}
                >
                  Request leave
                </button>
              </p>
              <LeaveRequestTable
                allPreviousLeaveRequests={allPreviousLeaveRequests}
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default LeaveRequestPage;
