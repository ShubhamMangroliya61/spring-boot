import React from "react";

function CreateNewPage() {
  return (
    <div className="flex flex-row">
      <div className="relative top-32 w-[70%] m-auto flex flex-row align-middle items-center justify-center bg-gray-100/40 backdrop-blur-md rounded-md mb-5 mx-5">
        <div className="w-[95%] flex justify-between p-5 pb-10">
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
                <div>
                  <label
                    htmlFor="lastName"
                    className="block text-sm font-medium leading-6 text-gray-300"
                  >
                    Last Name
                  </label>
                  <div className="mt-2 mb-2">
                    <input type="text" name="lastName" id="lastName" />
                  </div>
                </div>
                <div>
                  <label
                    htmlFor="email"
                    className="block text-sm font-medium leading-6 text-gray-300"
                  >
                    Email
                  </label>
                  <div className="mt-2 mb-2">
                    <input type="email" name="email" id="email" />
                  </div>
                </div>
              </div>
              <div className="flex flex-row justify-between mt-10">
                <div>
                  <label
                    htmlFor="password"
                    className="block text-sm font-medium leading-6 text-gray-300"
                  >
                    Password
                  </label>
                  <div className="mt-2 mb-2">
                    <input type="password" name="password" id="password" />
                  </div>
                </div>
                <div>
                  <label
                    htmlFor="role"
                    className="block text-sm font-medium leading-6 text-gray-300"
                  >
                    Role
                  </label>
                  <div className="mt-2 mb-2">
                    <select
                      name="role"
                      id="role"
                      className="w-[100%]"
                      defaultValue={""}
                    >
                      <option value="" disabled>
                        ---------------select role---------------
                      </option>
                      <option value="Admin" className="text-center">
                        Admin
                      </option>
                      <option value="Employee" className="text-center">
                        Employee
                      </option>
                    </select>
                  </div>
                </div>
                <div>
                  <label
                    htmlFor="team"
                    className="block text-sm font-medium leading-6 text-gray-300"
                  >
                    Team
                  </label>
                  <div className="mt-2 mb-2">
                    <select
                      name="team"
                      id="team"
                      className="w-[100%]"
                      defaultValue={""}
                    >
                      <option value="" disabled>
                        ---------------select team---------------
                      </option>
                      <option value="java" className="text-center">
                        Java
                      </option>
                      <option value="Python" className="text-center">
                        Python
                      </option>
                    </select>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div>
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
    </div>
  );
}

export default CreateNewPage;
