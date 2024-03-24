import React, { useState, useEffect } from "react";
import AdminSideBar from "../components/AdminSideBar";
import Board from "../components/ProjectTaskComponents/Board";
import { useParams } from "react-router-dom";
import { useGlobalContext } from "../context/appContext";
import SearchIcon from "@mui/icons-material/Search";
import { styled, alpha } from "@mui/material/styles";
import InputBase from "@mui/material/InputBase";
import PersonAddAltIcon from "@mui/icons-material/PersonAddAlt";
import ProjectActivity from "../components/ProjectActivity/ProjectActivity";

const Search = styled("div")(({ theme }) => ({
  position: "relative",
  borderRadius: theme.shape.borderRadius,
  backgroundColor: alpha(theme.palette.common.white, 0.15),
  "&:hover": {
    backgroundColor: alpha(theme.palette.common.white, 0.25),
  },
  marginRight: theme.spacing(2),
  marginLeft: 0,
  width: "100%",
  [theme.breakpoints.up("sm")]: {
    marginLeft: theme.spacing(3),
    width: "auto",
  },
}));

const SearchIconWrapper = styled("div")(({ theme }) => ({
  padding: theme.spacing(0, 2),
  height: "100%",
  position: "absolute",
  pointerEvents: "none",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: "inherit",
  "& .MuiInputBase-input": {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create("width"),
    width: "100%",
    [theme.breakpoints.up("md")]: {
      width: "20ch",
    },
  },
}));

function ProjectTaksPage() {
  const { authFetch, role, userId } = useGlobalContext();
  const { projectId } = useParams();
  const [selectedProject, setSelectedProject] = useState({});
  const [isButtonEnable, setIsButtonEnable] = useState(false);
  const [adding, setAdding] = useState(false);
  const [displayLogs, setDisplayLogs] = useState(false);

  const handleClose = () => {
    setAdding(false);
  };
  const handleOpen = () => {
    setAdding(true);
  };

  useEffect(() => {
    for (let i = 0; i < selectedProject?.members?.length; i++) {
      if (
        role === "admin" ||
        ((selectedProject?.members[i].role.toLowerCase() === "manager" ||
          selectedProject?.members[i].role.toLowerCase() === "owner") &&
          selectedProject?.members[i].employee.id.toString() === userId)
      ) {
        setIsButtonEnable(true);
        break;
      }
    }
  }, [selectedProject]);

  useEffect(() => {
    authFetch
      .get(`/project/${projectId}`)
      .then((res) => setSelectedProject(res.data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className="h-full overflow-x-hidden overflow-y-scroll w-full bg-black">
      <div className="flex flex-row">
        <div className="left relative w-[15%]">
          <AdminSideBar />
        </div>
        <div className="right w-[85%] h-screen">
          <div className="relative top-20 text-white flex mx-5 justify-between align-middle items-center">
            <div>
              <h1
                className="text-xl pt-2 mt-5 cursor-pointer"
                onClick={() => setDisplayLogs((prev) => !prev)}
              >
                {selectedProject.name}
              </h1>
              {/* <span className="pl-2 text-neutral-100/35 cursor-pointer">
                projects
              </span>
              <span className="cursor-pointer text-neutral-100/45">
                /{selectedProject.name}
              </span> */}
            </div>
            <div className="flex items-center">
              <Search>
                <SearchIconWrapper>
                  <SearchIcon />
                </SearchIconWrapper>
                <StyledInputBase
                  placeholder="Search…"
                  inputProps={{ "aria-label": "search" }}
                  onChange={(e) => console.log(e.target.value)}
                />
              </Search>
              {isButtonEnable && (
                <div
                  className="rounded-[50%] p-2 bg-blue-600 flex justify-center items-center cursor-pointer"
                  onClick={() => setAdding(true)}
                >
                  <PersonAddAltIcon fontSize="small" />
                </div>
              )}
            </div>
          </div>
          <div className="mt-20 h-full ml-5 overflow-scroll mb-40">
            {!displayLogs ? (
              <Board selectedProject={selectedProject} />
            ) : (
              <ProjectActivity />
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default ProjectTaksPage;
