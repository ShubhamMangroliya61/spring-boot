import React, { useEffect, useState } from "react";
import Column from "./Column";
import { dummyCards } from "../utils/dummyCardData";
import { columnArray } from "../utils/teskColumns";
import { useGlobalContext } from "../../context/appContext";
import { useParams } from "react-router-dom";
import CustomAlert from "../utils/CustomAlert";

function Board() {
  const { authFetch, showAlert } = useGlobalContext();
  const { projectId } = useParams();
  //   const [cards, setCards] = useState(dummyCards);
  const [cards, setCards] = useState([]);
  const [taskUpdate, setTaskUpdate] = useState(false);

  const getAllTasksOfCurrentProject = () => {
    authFetch(`/task/${projectId}`)
      .then((res) => setCards(res.data))
      .catch((err) => console.log(err));
  };

  useEffect(() => {
    getAllTasksOfCurrentProject();
  }, [taskUpdate]);

  return (
    <div className="h-full w-full flex gap-3 overflow-scroll">
      {/* ------ Display alert start ------ */}
      {showAlert && (
        <div className="absolute right-10 z-50">
          <CustomAlert />
        </div>
      )}
      {/* ------ Display alert end ------ */}
      {columnArray.map((clm) => (
        <Column
          column={clm.column}
          title={clm.title}
          headingColor={clm.headingColor}
          cards={cards}
          setCards={setCards}
          key={clm.title}
          setTaskUpdate={setTaskUpdate}
        />
      ))}
      <Column />
    </div>
  );
}

export default Board;
