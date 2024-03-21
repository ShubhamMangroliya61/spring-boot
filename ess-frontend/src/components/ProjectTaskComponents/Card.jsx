import React from "react";
import { motion } from "framer-motion";

function Card({ name, id, column, description, handleDragStart }) {
  return (
    <>
      <motion.div
        layout
        layoutId={id}
        draggable="true"
        className="cursor-grab rounded border border-neutral-700 p-3 bg-neutral-800 active:cursor-grabbing"
        onDragStart={(e) =>
          handleDragStart(e, { name, id, column, description })
        }
      >
        <p className="text-md font-normal uppercase text-neutral-100">{name}</p>
        <p className="text-sm text-neutral-100">{description}</p>
      </motion.div>
      ;
    </>
  );
}

export default Card;
