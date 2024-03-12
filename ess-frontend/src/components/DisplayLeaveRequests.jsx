import React, { useEffect, useState } from "react";
import { useGlobalContext } from "../context/appContext";
import AdminLeaveRequestTable from "./AdminLeaveRequestTable";
import CounterCard from "./CounterCard";

function DisplayLeaveRequests() {
  const { authFetch } = useGlobalContext();

  const [allLeaveRequests, setAllLeaveRequests] = useState([]);
  const [pendingLeaveRequests, setPendingLeaveRequests] = useState([]);
  const [rejectedLeaveRequests, setRejectedLeaveRequests] = useState([]);
  const [approvedLeaveRequests, setApprovedLeaveRequests] = useState([]);
  const [typeToDisplay, setTypeToDisplay] = useState("all");
  const [leavesToDisplay, setLeavesToDisplay] = useState([]);

  const filterLeaveRequests = () => {
    const pending = [];
    const rejected = [];
    const approved = [];

    allLeaveRequests?.forEach((leaveRequest) => {
      if (leaveRequest?.status?.toString().toLowerCase() === "pending") {
        pending.push(leaveRequest);
      } else if (
        leaveRequest?.status?.toString().toLowerCase() === "rejected"
      ) {
        rejected.push(leaveRequest);
      } else {
        approved.push(leaveRequest);
      }
    });

    setPendingLeaveRequests(pending);
    setApprovedLeaveRequests(approved);
    setRejectedLeaveRequests(rejected);
  };

  useEffect(() => {
    authFetch
      .get("/leave/getAll")
      .then((res) => {
        setLeavesToDisplay(res.data);
        setAllLeaveRequests(res.data);
      })
      .catch((err) => console.log(err.data));
  }, []);

  useEffect(() => {
    filterLeaveRequests();
  }, [allLeaveRequests]);

  const handleTypeChange = (type) => {
    setTypeToDisplay(type);
    if (type === "pending") {
      setLeavesToDisplay(pendingLeaveRequests);
    } else if (type === "rejected") {
      setLeavesToDisplay(rejectedLeaveRequests);
    } else if (type === "approved") {
      setLeavesToDisplay(approvedLeaveRequests);
    } else {
      setLeavesToDisplay(allLeaveRequests);
    }
  };

  return (
    <div className="flex flex-wrap">
      <div className="relative top-32 w-[97.5%] m-auto flex flex-row align-middle items-center justify-center bg-gray-100/40 backdrop-blur-md rounded-md mb-5">
        <div className="w-[95%] flex justify-between text-center p-5 pb-10">
          <div onClick={() => handleTypeChange("all")}>
            <CounterCard
              countHeading={"Total req"}
              count={allLeaveRequests.length}
              bg={"gray"}
            />
          </div>
          <div onClick={() => handleTypeChange("approved")}>
            <CounterCard
              countHeading={"Approved"}
              count={approvedLeaveRequests.length}
              bg={"green"}
            />
          </div>
          <div onClick={() => handleTypeChange("pending")}>
            <CounterCard
              countHeading={"Pending"}
              count={pendingLeaveRequests.length}
              bg={"gray"}
            />
          </div>
          <div onClick={() => handleTypeChange("rejected")}>
            <CounterCard
              countHeading={"Rejected"}
              count={rejectedLeaveRequests.length}
              bg={"red"}
            />
          </div>
        </div>
      </div>
      <div className="relative top-32 w-[97.5%] m-auto flex flex-row align-middle items-center justify-center bg-gray-300/40 backdrop-blur-md rounded-md mb-5">
        <div className="w-[95%] text-center p-5 pb-10">
          <p className="text-gray-200 text-lg pb-5">
            All the {typeToDisplay === "all" ? "" : typeToDisplay} requests
          </p>
          <AdminLeaveRequestTable allPreviousLeaveRequests={leavesToDisplay} />
        </div>
      </div>
    </div>
  );
}

export default DisplayLeaveRequests;
