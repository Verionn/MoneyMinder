import React, { useState, useEffect } from "react";
import Lists from "../../../components/communicationWithBackEnd/GetList.js";
import "./listsDisplay.css";

const listsDisplay = () => {
  const lists = Lists();

  return (
    <div className="listBox">
    {lists.map((list) => (
      <div key={list.listId} className="listItem">
        <div className="listHeader">
          <span>{list.name}</span>
          <span>{`${list.ItemBought || 2}/${list.numberOfItem || 5}`}</span>
        </div>
        <div className="progressBar">
          <div
            className="progress"
            style={{
              width: `${((list.ItemBought || 2) / (list.numberOfItem || 5)) * 100}%`,
            }}
          ></div>
        </div>
      </div>
    ))}
  </div>
  );
};

export default listsDisplay;
