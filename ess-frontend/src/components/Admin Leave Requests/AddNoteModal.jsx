import * as React from "react";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import LoadingButton from "@mui/lab/LoadingButton";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

const handleSubmit = () => {
  // authFetch
  //   .put(`/leave/${leave.id}`, leave)
  //   .then((res) => console.log(res.data))
  //   .catch((err) => console.log(err.data));
};

export default function AddNoteModal({
  open,
  setOpen,
  handleOpen,
  handleClose,
  option,
  leave,
}) {
  const [isLoading, setIsLoading] = React.useState(false);
  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Are you sure you want to {option}
          </Typography>
          <div className="flex justify-between mt-5">
            <Box
              sx={{
                "& > button": { m: 1 },
              }}
            >
              <div className="bg-white rounded-md">
                <LoadingButton
                  loading={isLoading}
                  loadingPosition="start"
                  variant="contained"
                  className="bg-blue-500 p-2 rounded-md mt-5 cursor-pointer duration-300 hover:bg-blue-400"
                  onClick={handleSubmit}
                >
                  <span>Add Employee</span>
                </LoadingButton>
              </div>
            </Box>
            <Box
              sx={{
                "& > button": { m: 1 },
              }}
            >
              <div className="bg-white rounded-md">
                <LoadingButton
                  loading={isLoading}
                  loadingPosition="start"
                  variant="contained"
                  className="bg-blue-500 p-2 rounded-md mt-5 cursor-pointer duration-300 hover:bg-blue-400"
                  onClick={handleClose}
                >
                  <span>Close</span>
                </LoadingButton>
              </div>
            </Box>
          </div>
        </Box>
      </Modal>
    </div>
  );
}
