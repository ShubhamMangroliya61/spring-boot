import React from "react";

function CounterCard({ countHeading, count, bg }) {
  return (
    <div
      className={`${
        bg == "red"
          ? "bg-red-400 duration-300 hover:shadow-[0_5px_40px_5px_rgba(100,0,0,0.7)]"
          : bg == "green"
          ? "bg-green-600 duration-300 hover:shadow-[0_5px_40px_5px_rgba(0,100,0,0.7)]"
          : "bg-gray-600 duration-300 hover:shadow-[0_5px_40px_5px_rgba(33,33,33,0.7)]"
      }  rounded-md flex flex-col align-middle items-center cursor-pointer`}
    >
      <div className="p-3">
        <h3>{countHeading}</h3>
        <h4 className="text-center">{count}</h4>
      </div>
    </div>
  );
}

export default CounterCard;
