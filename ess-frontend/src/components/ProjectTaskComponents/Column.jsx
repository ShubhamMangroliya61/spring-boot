import React, { useState } from "react";
import Card from "./Card";
import AddCard from "./AddCard";

function Column({ title, headingColor, column, cards, setCards }) {
  const [active, setActive] = useState(false);

  const filteredCards = cards?.filter(
    (card) => card.status.toLowerCase() === column.toLowerCase()
  );

  const handleDragStart = (e, card) => {
    e.dataTransfer.setData("cardId", card.id);
  };

  const handleDragOver = (e) => {
    e.preventDefault();
    setActive(true);
  };

  const handleDragLeave = (e) => {
    setActive(false);
  };

  const handleDragEnd = (e) => {
    setActive(false);

    const cardId = e.dataTransfer.getData("cardId");
    let copy = [...cards];
    copy = cards.filter((card) => card.id != cardId);

    let removedCards = cards.filter((card) => card.id == cardId);

    removedCards = removedCards.map((card) => {
      card.status = column.toUpperCase();
      return card;
    });

    let finalCards = copy.concat(removedCards);

    setCards(finalCards);
  };

  return (
    <div className="w-56 shrink-0">
      <div className="flex items-center mb-3">
        <h3 className={`flex font-medium ${headingColor}`}>{title}</h3>
        <span className="rounded text-sm text-neutral-400 px-3">
          {filteredCards?.length}
        </span>
      </div>
      <div
        className={`h-full w-full transition-colors ${
          active ? "bg-neutral-800/50" : "bg-neutral-800/0"
        }`}
        onDragOver={(e) => handleDragOver(e)}
        onDragLeave={(e) => handleDragLeave(e)}
        onDrop={(e) => handleDragEnd(e)}
      >
        {filteredCards?.map((c) => {
          return <Card key={c.id} {...c} handleDragStart={handleDragStart} />;
        })}
        <AddCard column={column} setCards={setCards} />
      </div>
    </div>
  );
}

export default Column;
