import React from "react";
import options from "./utils/SideBarOptions.js";
import { Link } from "react-router-dom";

function SideBar() {
  return (
    <div className="relative top-20 h-full bg-gray-300/40 backdrop-blur-md rounded-md">
      <div className="">
        <ul className="text-slate-100">
          {options.map((option) => (
            <Link to={option.link} key={option.id}>
              <li className="text-center py-1 cursor-pointer duration-200 hover:text-slate-300">
                {option.text}
              </li>
            </Link>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default SideBar;
