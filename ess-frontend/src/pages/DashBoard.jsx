import React, { useEffect, useState } from "react";
import SideBar from "../components/SideBar";
import Calendar from "react-calendar";
import "../components/utils/customCalendar.css";
import { useGlobalContext } from "../context/appContext";
import DashboardLeaveRequestTable from "../components/DashboardLeaveRequestTable";
function DashBoard() {
  const { authFetch } = useGlobalContext();
  const [datesWithNethovers, setDatesWithNethovers] = useState([]);
  const [pendingLeaves, setPendingLeaves] = useState([]);

  useEffect(() => {
    authFetch
      .get("/punches/allDates")
      .then((res) => setDatesWithNethovers(res.data))
      .catch((err) => console.log(err));

    authFetch
      .get("/leave")
      .then((res) => {
        const tempPending = [];
        res.data.forEach((leaveRequest) => {
          if (leaveRequest.status.toString().toLowerCase() === "pending") {
            tempPending.push(leaveRequest);
          }
        });
        setPendingLeaves(tempPending);
      })
      .catch((err) => console.log(err));
  }, []);

  useEffect(() => {
    console.log(datesWithNethovers);
  }, [datesWithNethovers]);

  const setAbsent = (date) => {
    const formattedDate = moment(date).format("YYYY-MM-DD");
    const dateWithNetHours = datesWithNethovers.find(
      (x) => x.date === formattedDate
    );
    console.log(dateWithNetHours);
  };

  return (
    <>
      <div className="absolute h-screen w-screen bg-black overflow-hidden">
        <div className="flex flex-row">
          <div className="left w-[15%]">
            <SideBar />
          </div>
          <div className="right w-[85%] h-screen">
            <div className="relative top-20 text-white flex justify-center text-xl">
              <h1>Dashboard</h1>
            </div>
            <div className="flex">
              <div className="relative mx-[10px] top-24 w-[50%] flex flex-col align-middle items-center justify-center bg-gray-300/40 backdrop-blur-md rounded-md p-[30px]">
                <h1 className="text-gray-200 mb-5 text-base">
                  Monthly attendance details
                </h1>
                <Calendar titleClassName={"highlight"} />
              </div>
              <div className="relative mx-[10px] top-24 w-[50%] flex flex-col align-middle items-center justify-center bg-gray-300/40 backdrop-blur-md rounded-md p-[30px]">
                <h1 className="text-gray-200 mb-5 text-base">Holidays list</h1>
                <Calendar />
              </div>
              <div className="relative mx-[10px] top-24 w-[50%] flex flex-col items-center bg-gray-300/40 backdrop-blur-md rounded-md p-[30px]">
                <h1 className="text-gray-200 mb-5 text-base">
                  Pending leave requests
                </h1>
                <DashboardLeaveRequestTable
                  allPreviousLeaveRequests={pendingLeaves}
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default DashBoard;
