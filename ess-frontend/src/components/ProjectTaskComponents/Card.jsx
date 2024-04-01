import React, { useState } from "react";
import { motion } from "framer-motion";
import PriorityHighIcon from "@mui/icons-material/PriorityHigh";
import BorderColorIcon from "@mui/icons-material/BorderColor";
import { useGlobalContext } from "../../context/appContext";

const priorityData = [
  {
    name: "None",
    value: "NONE",
    color: "white",
  },
  {
    name: "Low",
    value: "LOW",
    color: "yellow",
  },
  {
    name: "Medium",
    value: "MEDIUM",
    color: "blue",
  },
  {
    name: "High",
    value: "HIGH",
    color: "red",
  },
];

function Card({
  name,
  id,
  column,
  description,
  priority,
  assignTo,
  assignBy,
  handleDragStart,
  setTaskUpdate,
  isAddButtomActive,
  handleOpen,
}) {
  const { role, userId } = useGlobalContext();
  const [expanded, setExpanded] = useState(false);

  const toggleExpanded = () => {
    setExpanded(!expanded);
  };
  return (
    <>
      <motion.div
        layout
        layoutId={id}
        draggable={isAddButtomActive || assignTo.id.toString() === userId}
        className="cursor-grab rounded border border-neutral-700 p-3 bg-neutral-800 active:cursor-grabbing"
        onDragStart={(e) =>
          handleDragStart(e, { name, id, column, description })
        }
      >
        <p className="text-md font-normal uppercase text-neutral-100/50 mb-3">
          {name}
        </p>
        <p className="text-sm text-neutral-100/70 mb-3">
          {assignTo.firstName + " " + assignTo.lastName}
        </p>
        <div className="description mb-3">
          <p
            className={`text-sm text-neutral-100/70 ${
              expanded ? "line-clamp-none" : "line-clamp-1"
            }`}
          >
            {description}
          </p>
          {!expanded && (
            <button
              onClick={toggleExpanded}
              className="text-sm text-neutral-100/50 cursor-pointer"
            >
              See more
            </button>
          )}
          {expanded && (
            <button
              onClick={toggleExpanded}
              className="text-sm text-neutral-100/50 cursor-pointer"
            >
              Hide
            </button>
          )}
        </div>
        <p className="border-b-2 border-neutral-400/40 border-spacing-x-3 mb-3"></p>

        <div className="flex justify-between">
          <select
            name="priority"
            id="priority"
            className="rounded-sm bg-black text-neutral-100/70"
            defaultValue={priority}
            disabled={
              isAddButtomActive || assignTo.id.toString() === userId
                ? false
                : true
            }
          >
            <option value={""} className="text-center" disabled>
              <p>Priority</p>
            </option>
            {priorityData.map((item) => {
              return (
                <option
                  key={item.value}
                  value={item.value}
                  className="text-center"
                >
                  {item.name}
                </option>
              );
            })}
          </select>
          {isAddButtomActive ? (
            <div onClick={handleOpen}>
              <BorderColorIcon
                color="text-neutral-100/70"
                className="text-neutral-100/70 cursor-pointer duration-300 hover:text-neutral-50"
                fontSize="small"
              />
            </div>
          ) : (
            ""
          )}
        </div>
      </motion.div>
      ;
    </>
  );
}

export default Card;
