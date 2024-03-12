import React, { useState, useEffect } from "react";
import { useGlobalContext } from "../context/appContext";
import RequestLeave from "../components/RequestLeave";
import LeaveCount from "../components/LeaveCount";
import LeaveRequestTable from "../components/LeaveRequestTable";

function LeaveRequestPage() {
  const { authFetch } = useGlobalContext();

  const [allPreviousLeaveRequests, setAllPreviousLeaveRequests] = useState([]);

  useEffect(() => {
    authFetch
      .get("/leave")
      .then((res) => {
        setAllPreviousLeaveRequests(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className="flex flex-wrap">
      <div className="w-[50%] h-full">
        <RequestLeave allPreviousLeaveRequests={allPreviousLeaveRequests} />
      </div>
      <div className="w-[50%] h-full">
        <LeaveCount allPreviousLeaveRequests={allPreviousLeaveRequests} />
      </div>
      <div className="relative top-32 w-[97.5%] m-auto flex flex-row align-middle items-center justify-center bg-gray-300/40 backdrop-blur-md rounded-md p-5 px-16">
        <div className="w-[95%] text-center p-5">
          <p className="py-4 text-gray-200 text-lg">all the prev requests</p>
          <LeaveRequestTable
            allPreviousLeaveRequests={allPreviousLeaveRequests}
          />
        </div>
      </div>
    </div>
  );
}

export default LeaveRequestPage;
