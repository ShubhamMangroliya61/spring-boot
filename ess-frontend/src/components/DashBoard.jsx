import React, { useEffect, useState } from "react";
import { useGlobalContext } from "../context/appContext";
import TempTable from "./Temp/TempTable";

function DashBoard() {
  const { authFetch } = useGlobalContext();
  const [todayPunchs, setTodayPunchs] = useState({});

  // useEffect(() => {
  //   console.log(todayPunchs);
  // }, [todayPunchs]);

  useEffect(() => {
    authFetch
      .get("/punches/allDates")
      .then((res) => console.log(res.data))
      .catch((err) => console.log(err));
  }, []);

  useEffect(() => {
    let dateObj = new Date();

    const getPunchInAndOutRequest = {
      day: String(dateObj.getDate()).padStart(2, "0"),
      month: String(dateObj.getMonth() + 1).padStart(2, "0"),
      year: String(dateObj.getFullYear()),
    };

    authFetch
      .post("/punches", getPunchInAndOutRequest)
      .then((res) => setTodayPunchs(res.data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <>
      <div className="absolute min-h-svh w-screen bg-black">
        <div className="relative top-24 w-[95%] m-auto flex flex-row align-middle items-center justify-center px-[-40px] bg-gray-300/40 backdrop-blur-md rounded-md pb-5 mb-6">
          <div className="w-[50%] px-[20px] text-center">
            <p className="py-4 text-gray-200 text-lg">
              Punch logs of selected Date
            </p>
            <TempTable todaysPunches={todayPunchs} />
          </div>
          <div className="w-[50%] px-[20px] text-center">
            <p className="py-4 text-gray-200 text-lg">Select day from here</p>
            <TempTable todaysPunches={todayPunchs} />
          </div>
        </div>
      </div>
    </>
  );
}

export default DashBoard;
