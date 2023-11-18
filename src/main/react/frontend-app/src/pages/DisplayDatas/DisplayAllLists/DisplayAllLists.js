import React from "react";

import GetDatas from "../../../components/communicationWithBackEnd/GetDatas";
import ListDescription from "../../../components/listDescription/listDescriptions";
import "./DisplayAllLists.css";
import GetNumberOfItems from "../../../components/functions/GetDatasFromItems";

const DisplayAllLists = () => {
  const apiUrl = "http://localhost:8080/lists";
  let { data, loading, error } = GetDatas({ apiUrl });

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }

  return (
    <div className="listBox">
      {data && data.lists && data.lists.length > 0 ? (
        <ul>
          {data.lists.map((list) => (
            <li key={list.listId} className="singleList">
              <div className="listTitle">{list.name}</div>
              <div className={"listHeader"}>
                <span className="NumberOfItems">
                  Number of Items :{" "}
                  <GetNumberOfItems listID={list.listId} operation={"count"} />
                </span>
                <span className="FullPrice">
                  Total Price :{" "}
                  <GetNumberOfItems listID={list.listId} operation={"price"} />{" "}
                  $
                </span>
              </div>
              <div className="Description">
                <ListDescription
                  Description={
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed d"
                  }
                ></ListDescription>
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
