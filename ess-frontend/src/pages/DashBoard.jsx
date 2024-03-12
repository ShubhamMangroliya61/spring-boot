import React, { useEffect, useState } from "react";
import { useGlobalContext } from "../context/appContext";
import DatesAndPunchInOutLog from "../components/DatesAndPunchInOutLog";
import SideBar from "../components/SideBar";
import RequestLeave from "../components/RequestLeave";
import LeaveCount from "../components/LeaveCount";

function DashBoard() {
  return (
    <>
      <div className="absolute h-screen w-screen bg-black overflow-hidden">
        <div className="flex flex-row">
          <div className="left w-[15%]">
            <SideBar />
          </div>
          <div className="right w-[85%] h-screen">
            {/* Section 1 start */}
            {/* <DatesAndPunchInOutLog /> */}
            {/* Section 1 end */}

            {/* Section 2 start */}
            <div className="flex">
              <div className="w-[50%]">
                <RequestLeave />
              </div>
              <div className="w-[50%]">
                <LeaveCount />
              </div>
            </div>
            {/* Section 2 start */}
          </div>
        </div>
      </div>
    </>
  );
}

export default DashBoard;
