import React, { useEffect, useState } from "react";
import { useGlobalContext } from "../context/appContext";
import TempTable from "./Temp/TempTable";

function DashBoard() {
  const { authFetch } = useGlobalContext();
  const [todayPunchs, setTodayPunchs] = useState({});

  useEffect(() => {
    console.log(todayPunchs);
  }, [todayPunchs]);

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
      <div className="flex flex-col h-screen align-middle justify-center text-5xl bg-login-bg">
        <div className="w-[95%] m-auto bg-black">
          <TempTable todaysPunches={todayPunchs} />
        </div>
      </div>
    </>
  );
}

export default DashBoard;
