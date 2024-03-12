import React, { useState, useEffect } from "react";
import CounterCard from "./CounterCard";

function LeaveCount({ allPreviousLeaveRequests }) {
  const [approvedLeavesCount, setApprovedLeavesCount] = useState(0);
  const [pendingLeavesCount, setPendingLeavesCount] = useState(0);
  const [rejectedLeavesCount, setRejectedLeavesCount] = useState(0);

  const countLeavesByStatus = () => {
    allPreviousLeaveRequests.forEach((leave) => {
      if (leave.status.toString().toLowerCase() === "approved") {
        setApprovedLeavesCount((prevCnt) => prevCnt + 1);
      } else if (leave.status.toString().toLowerCase() === "pending") {
        setPendingLeavesCount((prevCnt) => prevCnt + 1);
      } else {
        setRejectedLeavesCount((prevCnt) => prevCnt + 1);
      }
    });
  };

  useEffect(() => {
    countLeavesByStatus();
  }, [allPreviousLeaveRequests]);

  return (
    <div className="relative top-24 w-[95%] m-auto flex flex-row align-middle items-center justify-center bg-gray-300/40 backdrop-blur-md rounded-md p-[30px]">
      <div className="bg-gray-500 w-[70%] h-[50%] m-auto p-10 flex flex-row justify-between align-middle items-center">
        <CounterCard
          countHeading={"Approved"}
          count={approvedLeavesCount}
          bg={"green"}
        />
        <CounterCard
          countHeading={"Pending"}
          count={pendingLeavesCount}
          bg={"grey"}
        />
        <CounterCard
          countHeading={"Rejected"}
          count={rejectedLeavesCount}
          bg={"red"}
        />
      </div>
    </div>
  );
}

export default LeaveCount;
