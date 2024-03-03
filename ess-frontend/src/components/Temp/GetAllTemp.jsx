import React, { useState, useEffect } from "react";
import axios from "axios";

function GetAllTemp() {
  const [roles, setRoles] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/role/all").then((response) => {
      setRoles(response.data);
    });
  }, []);

  useEffect(() => {
    console.log(roles);
  }, [roles]);

  return <div className="text-white"> Getting all data </div>;
}

export default GetAllTemp;
