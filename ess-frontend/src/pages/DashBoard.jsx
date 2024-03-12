import React, { useEffect, useState } from "react";
import SideBar from "../components/SideBar";

import LeaveRequestPage from "./LeaveRequestPage";

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
            <LeaveRequestPage />
            {/* Section 2 start */}
          </div>
        </div>
      </div>
    </>
  );
}

export default DashBoard;
