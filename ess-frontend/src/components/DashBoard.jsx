import React, { useEffect } from "react";
import { useGlobalContext } from "../context/appContext";

function DashBoard() {
  const { authFetch } = useGlobalContext();
  useEffect(() => {
    authFetch.get("/employee/all").then((res) => console.log(res.data));
  }, []);

  return (
    <div className="flex h-screen align-middle justify-center text-5xl bg-login-bg">
      <div className="">DashBoard</div>
    </div>
  );
}

export default DashBoard;
