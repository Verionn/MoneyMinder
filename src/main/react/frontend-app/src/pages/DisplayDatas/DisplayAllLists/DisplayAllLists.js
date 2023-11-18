import React from "react";

import GetDatas from "../../../components/communicationWithBackEnd/GetDatas";
import ListDescription from "../../../components/listDescription/listDescriptions";
import "./DisplayAllLists.css";

const DisplayAllLists = () => {
  const apiUrl = "http://localhost:8080/lists";
  const { data, loading, error } = GetDatas({ apiUrl });

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }
  console.log(data.lists);
  return (
    <div className="listBox">
      {data && data.lists && data.lists.length > 0 ? (
        <ul>
          {data.lists.map((list) => (
            <li key={list.listId} className="singleList">
              <div className="listTitle">{list.name}</div>
              <div className={"listHeader"}>
                <span className="NumberOfItems">Number of Items : 2</span>
                <span className="FullPrice">Total Price : 10 $</span>
              </div>
              <div className="Description">
                <ListDescription></ListDescription>
              </div>
            </li>
          ))}
        </ul>
      ) : (
        <p>No data available.</p>
      )}
    </div>
  );
};

export default DisplayAllLists;
