import React, { useState } from "react";
import CreateNewEmployee from "../components/CreateNewEmployee";

function CreateNewPage() {
  return (
    <div className="flex flex-row">
      <div className="relative top-32 w-[70%] m-auto flex flex-row align-middle items-center justify-center bg-gray-100/40 backdrop-blur-md rounded-md mb-5 mx-5">
        <CreateNewEmployee />
      </div>

      {/* <div className="relative top-32 w-[30%] m-auto flex flex-row align-middle items-center justify-center bg-gray-100/40 backdrop-blur-md rounded-md mb-5 mx-5">
          <div className="w-[95%] flex justify-between text-center p-5 pb-10">
            <div className="w-[100%] m-auto">
              <form className="w-[100%]">
                <div className="flex flex-row justify-between">
                  <div>
                    <label
                      htmlFor="firstName"
                      className="block text-sm font-medium leading-6 text-gray-300"
                    >
                      First Name
                    </label>
                    <div className="mt-2 mb-2">
                      <input type="text" name="firstName" id="firstName" />
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div> */}
    </div>
  );
}

export default CreateNewPage;
