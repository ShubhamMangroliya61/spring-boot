import React, { useEffect, useState } from "react";
import Column from "./Column";
import { dummyCards } from "../utils/dummyCardData";
import { columnArray } from "../utils/teskColumns";
import { useGlobalContext } from "../../context/appContext";
import { useParams } from "react-router-dom";

function Board() {
  const { authFetch } = useGlobalContext();
  const { projectId } = useParams();
  //   const [cards, setCards] = useState(dummyCards);
  const [cards, setCards] = useState([]);

  useEffect(() => {
    authFetch(`/task/${projectId}`)
      .then((res) => setCards(res.data))
      .catch((err) => console.log(err));
  }, []);

  useEffect(() => {
    console.log(cards);
  }, [cards]);

  return (
    <div className="h-full w-full flex gap-3 overflow-scroll">
      {columnArray.map((clm) => (
        <Column
          column={clm.column}
          title={clm.title}
          headingColor={clm.headingColor}
          cards={cards}
          setCards={setCards}
          key={clm.title}
        />
      ))}
      <Column />
    </div>
  );
}

export default Board;
