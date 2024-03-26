import * as React from "react";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import { Button } from "@mui/material";
import { useGlobalContext } from "../../context/appContext";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 1200,
  bgcolor: "black",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

export default function AddEmployeeMoal({
  open,
  setOpen,
  handleOpen,
  handleClose,
  allRoles,
  allTeams,
  setIsChanged,
}) {
  const { authFetch, displayAlert } = useGlobalContext();
  const [newEmployee, setNewEmployee] = React.useState({});

  const handleChange = (e) => {
    const name = e.target.name;
    const value = e.target.value;
    setNewEmployee((employee) => ({
      ...employee,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(newEmployee);
    authFetch
      .post("/employee", newEmployee)
      .then((res) => {
        displayAlert(
          res.data.firstName + " " + res.data.lastName + " added successfully",
          "success"
        );
        handleClose();
      })
      .catch((err) => {
        if (err?.response?.data?.message) {
          displayAlert(err?.response?.data?.message, "error");
          setIsChanged((prev) => !prev);
        } else {
          displayAlert("something sent wrong", "error");
        }
        console.log(err);
      });
  };

  return (
    <div className="rounded-md">
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
        className="ronded-md"
      >
        <Box sx={style}>
          <div className="w-[95%] flex justify-between p-5 pb-10">
            <div className="w-[100%] m-auto">
              <h1 className="text-xl text-gray-100 align-middle font-medium text-center mb-8">
                Add new Emplyee
              </h1>
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
                      <input
                        type="text"
                        name="firstName"
                        id="firstName"
                        onChange={handleChange}
                      />
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
                      <input
                        type="text"
                        name="lastName"
                        id="lastName"
                        onChange={handleChange}
                      />
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
                      <input
                        type="email"
                        name="email"
                        id="email"
                        onChange={handleChange}
                      />
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
                      <input
                        type="password"
                        name="password"
                        id="password"
                        onChange={handleChange}
                      />
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
                        name="roleId"
                        id="role"
                        className="w-[100%]"
                        defaultValue={""}
                        onChange={handleChange}
                      >
                        <option value="" disabled>
                          ---------------select role---------------
                        </option>
                        {allRoles.map((role) => (
                          <option
                            value={role.id}
                            key={role.id}
                            className="text-center"
                          >
                            {role.name}
                          </option>
                        ))}
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
                        name="teamId"
                        id="team"
                        className="w-[100%]"
                        defaultValue={""}
                        onChange={handleChange}
                      >
                        <option value="" disabled>
                          ---------------select team---------------
                        </option>
                        {allTeams.map((team) => (
                          <option
                            value={team.id}
                            key={team.id}
                            className="text-center"
                          >
                            {team.name}
                          </option>
                        ))}
                      </select>
                    </div>
                  </div>
                </div>
                <div className="flex justify-between">
                  <button
                    type="submit"
                    className="bg-blue-500 p-2 rounded-md mt-5 cursor-pointer duration-300 hover:bg-blue-400"
                    onClick={handleSubmit}
                  >
                    Add Employee
                  </button>
                  <button
                    type="close"
                    className="bg-blue-500 p-2 rounded-md mt-5 cursor-pointer duration-300 hover:bg-blue-400"
                    onClick={handleClose}
                  >
                    Close
                  </button>
                </div>
              </form>
            </div>
          </div>
        </Box>
      </Modal>
    </div>
  );
}
