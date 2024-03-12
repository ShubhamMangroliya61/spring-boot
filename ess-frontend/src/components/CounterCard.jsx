import React from "react";

function CounterCard({ countHeading, count, bg }) {
  return (
    <div
      className={`${
        bg == "red"
          ? "bg-red-400"
          : bg == "green"
          ? "bg-green-400"
          : "bg-gray-600"
      }  rounded-md flex flex-col align-middle items-center`}
    >
      <div className="p-3">
        <h3>{countHeading}</h3>
        <h4 className="text-center">{count}</h4>
      </div>
    </div>
  );
}

export default CounterCard;
