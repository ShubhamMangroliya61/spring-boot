import React, { useEffect, useState } from "react";
import SideBar from "../components/SideBar";
import Calendar from "react-calendar";
import "../components/utils/customCalendar.css";
import { useGlobalContext } from "../context/appContext";
import DashboardLeaveRequestTable from "../components/DashboardLeaveRequestTable";
import moment from "moment";
import Button from "@mui/material/Button";
import BasicModal from "../components/BasicModal";

function DashBoard() {
  const { authFetch } = useGlobalContext();
  const [datesWithNethovers, setDatesWithNethovers] = useState([]);
  const [pendingLeaves, setPendingLeaves] = useState([]);
  const [selectedDate, setSelectedDate] = useState({
    date: "0000-00-00",
    netHours: "00:00:00",
  });

  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

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
    if (!dateWithNetHours && date < Date.now()) return "highlight";
  };

  const getNetHoversOfSelectedDate = (date) => {
    const formattedDate = moment(date).format("YYYY-MM-DD");
    const selecteDateWithNetHours = datesWithNethovers.find(
      (x) => x.date === formattedDate
    );
    if (selecteDateWithNetHours) setSelectedDate(selecteDateWithNetHours);
    else setSelectedDate({ date: formattedDate, netHours: "00:00:00" });
  };

  return (
    <>
      <BasicModal
        open={open}
        setOpen={setOpen}
        handleClose={handleClose}
        handleOpen={handleOpen}
      />
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
              <div className="relative mx-[10px] top-24 w-[50%] flex flex-col justify-center bg-gray-300/40 backdrop-blur-md rounded-md p-[30px]">
                <h1 className="text-gray-200 mb-5 text-base">
                  Monthly attendance details
                </h1>
                <Calendar
                  showNeighboringMonth={false}
                  tileClassName={({ date }) => {
                    return setAbsent(date);
                  }}
                  onClickDay={(date) => getNetHoversOfSelectedDate(date)}
                />
                <div className="flex justify-start text-left mt-5">
                  <div className="w-full">
                    <h1 className="text-gray-200">Selected date:</h1>
                    &nbsp;&nbsp;
                    <div className="text-gray-400">
                      {selectedDate && <div>{selectedDate.date}</div>}
                    </div>
                  </div>
                  <br />
                  <div className="w-full">
                    <h1 className="text-gray-200">Net hours:</h1>
                    &nbsp;&nbsp;
                    <div className="text-gray-400">
                      {selectedDate && <div>{selectedDate.netHours}</div>}
                    </div>
                  </div>
                </div>
                <Button onClick={handleOpen}>Open modal</Button>
              </div>
              <div className="relative mx-[10px] top-24 w-[50%] flex flex-col items-center bg-gray-300/40 backdrop-blur-md rounded-md p-[30px]">
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
