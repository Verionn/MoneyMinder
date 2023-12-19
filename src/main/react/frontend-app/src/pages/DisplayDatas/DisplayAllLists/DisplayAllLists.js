import React, { useState } from "react";

import GetLists from "../../../components/communicationWithServer/GetLists";
import ListDescription from "../../../components/listDescription/listDescriptions";
import "./DisplayAllLists.css";
import GetDatasFromItems from "../../../components/functions/GetDatasFromItems";
import DisplayItems from "../DisplayItems/DisplayItems";
import ListDropdown from "../../../components/dropdownMenuLists/DropdownMenuList";

const DisplayAllLists = ({ onClickList, onCloseList }) => {
  const [ItemsID, setItemsID] = useState(-1);

  const handleListClick = (listId) => {
    setItemsID(listId);
    onClickList(listId);
  };

  const handleCloseItemsList = () => {
    setItemsID(-1);
    onCloseList();
  };

  const apiUrl = "http://localhost:8080/lists";
  let { data, loading, error } = GetLists({ apiUrl });
  const getListName = (listID) => {
    if (data && data.lists && data.lists.length > 0) {
      return data.lists.find((list) => list.listId === listID).name;
    }
    return "List";
  };
  if (loading) {
    return (
      <p className={ItemsID === -1 ? "listBox" : "listBoxSelectItems"}>
        <box-icon
          name="loader-alt"
          animation="spin"
          color="#002a4e"
          size="lg"
        ></box-icon>
      </p>
    );
  }

  if (error) {
    return (
      <p className={ItemsID === -1 ? "listBox" : "listBoxSelectItems"}>
        Error: Failed to load Lists
      </p>
    );
  }

  return (
    <div className={ItemsID === -1 ? "listBox" : "listBoxSelectItems"}>
      {ItemsID === -1 && data && data.lists && data.lists.length > 0 ? null : (
        <div className="listDropdown">
          <ListDropdown lists={data.lists} onSelect={handleListClick} />
        </div>
      )}

      {ItemsID === -1 ? (
        data && data.lists && data.lists.length > 0 ? (
          <ul className="userLists">
            {data.lists.map((list) => (
              <li
                key={list.listId}
                className="singleList"
                onClick={() => handleListClick(list.listId)}
              >
                <div className="listTitle">{list.name}</div>
                <div className={"listHeader"}>
                  <span className="NumberOfItems">
                    Number of Items :{" "}
                    <GetDatasFromItems
                      listID={list.listId}
                      operation={"count"}
                    />
                  </span>
                  <span className="FullPrice">
                    Total Price :{" "}
                    <GetDatasFromItems
                      listID={list.listId}
                      operation={"price"}
                    />{" "}
                    $
                  </span>
                </div>
                <div className="Description">
                  <ListDescription
                    Description={
                      list.description === ""
                        ? "No description"
                        : list.description
                    }
                    onClick={() => console.log("Clicked on description")}
                  ></ListDescription>
                </div>
              </li>
            ))}
          </ul>
        ) : (
          <p>No data available.</p>
        )
      ) : null}

      {ItemsID !== -1 && (
        <div>
          {/* Content to render when ItemsID is not -1 */}
          <DisplayItems
            listID={ItemsID}
            operation={getListName(ItemsID)}
            onClose={handleCloseItemsList}
          />
        </div>
      )}
    </div>
  );
};

export default DisplayAllLists;
