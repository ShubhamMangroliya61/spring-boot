import React from "react";
import axios from "axios";

function UpdateTemp() {
  const updateData = () => {
    axios
      .put("http://localhost:8080/api/role/4", {
        name: "Extra Role",
        description: "Extra Role Description",
      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <button
        onClick={updateData}
        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
      >
        Update role
      </button>
    </div>
  );
}

export default UpdateTemp;
