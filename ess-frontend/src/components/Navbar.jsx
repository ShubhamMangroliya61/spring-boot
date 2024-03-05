import React, { useState } from "react";

function Navbar() {
  const [punchOptions, setPunchOptions] = useState(false);
  const [subOptions, setSubOptions] = useState(false);
  const [profileOptions, setProfileOptions] = useState(false);

  const handleOptionsClick = () => {
    if (subOptions) {
      setSubOptions(false);
      setPunchOptions(false);
      setProfileOptions(false);
    } else {
      setSubOptions(true);
      setPunchOptions(false);
      setProfileOptions(false);
    }
  };

  const handlePunchClick = () => {
    if (punchOptions) {
      setPunchOptions(false);
      setSubOptions(false);
    } else {
      setPunchOptions(true);
      setSubOptions(false);
      setProfileOptions(false);
    }
  };

  const handleProfileClick = () => {
    if (profileOptions) {
      setProfileOptions(false);
      setPunchOptions(false);
      setSubOptions(false);
    } else {
      setProfileOptions(true);
      setPunchOptions(false);
      setSubOptions(false);
    }
  };

  return (
    <div className="relative bg-slate-800 w-full text-gray-400 py-6">
      <div className="w-[95%] m-auto flex flex-row text-center justify-between items-center">
        <div className="left">TruFlux</div>
        <div className="right">
          <ul>
            <div className="relative flex flex-row mx-[-20px] items-center">
              <li
                className="px-[10px] cursor-pointer duration-200 hover:text-gray-300"
                onClick={handlePunchClick}
              >
                Punch
              </li>
              <div
                className={`${
                  punchOptions ? "" : "hidden"
                } absolute top-14 bg-slate-500 w-[150px] h-[100px] rounded-md right-32 bg-slate-500/50 backdrop-blur-md`}
              >
                <ul>
                  <li className="cursor-pointer duration-200 hover:text-gray-300">
                    Punch in
                  </li>
                  <li className="cursor-pointer duration-200 hover:text-gray-300">
                    Punch out
                  </li>
                </ul>
              </div>
              <li
                className="px-[10px] cursor-pointer duration-200 hover:text-gray-300"
                onClick={handleOptionsClick}
              >
                Options
              </li>
              <div
                className={`${
                  subOptions ? "" : "hidden"
                } absolute top-14 bg-slate-500 w-[150px] h-[100px] rounded-md bg-slate-500/50 backdrop-blur-md`}
              >
                <ul>
                  <li className="cursor-pointer duration-200 hover:text-gray-300">
                    Sub-op-1
                  </li>
                  <li className="cursor-pointer duration-200 hover:text-gray-300">
                    Sub-op-2
                  </li>
                  <li className="cursor-pointer duration-200 hover:text-gray-300">
                    Sub-op-3
                  </li>
                </ul>
              </div>
              <li className="px-[10px] w-[50px] h-[30px] box-border rounded-[50%]">
                <img
                  src="https://media.stage.truflux.drcsystems.ooo/uploads/user/profile/1_2024_02_14_07_35_19.jpg"
                  className="object-cover w-[100%] h-[100%] rounded-[50%] cursor-pointer"
                  onClick={handleProfileClick}
                />
              </li>
              <div
                className={`${
                  profileOptions
                    ? "opacity-100"
                    : "hidden opacity-0 pointer-events-none"
                } transition-opacity duration-300 absolute top-14 bg-slate-500/50 backdrop-blur-md w-[150px] h-[100px] rounded-md right-3`}
              >
                <ul>
                  <li className="cursor-pointer duration-200 hover:text-gray-300">
                    Sub-op-1
                  </li>
                  <li className="cursor-pointer duration-200 hover:text-gray-300">
                    View profile
                  </li>
                  <li className="cursor-pointer duration-200 hover:text-gray-300">
                    Logout
                  </li>
                </ul>
              </div>
            </div>
          </ul>
        </div>
      </div>
    </div>
  );
}

export default Navbar;
