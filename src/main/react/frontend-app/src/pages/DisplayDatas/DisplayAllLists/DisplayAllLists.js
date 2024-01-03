import React, { useEffect, useState } from "react";
import { GetListsData,GetItemListData } from "../../../components/communicationWithServer/HandleDataRequest";
import ListDescription from "../../../components/listDescription/listDescriptions";
import "./DisplayAllLists.css";
import GetDatasFromItems from "../../../components/functions/functions";
import DisplayItems from "../DisplayItems/DisplayItems";
import ListDropdown from "../../../components/dropdownMenuLists/DropdownMenuList";
import { useDarkMode,UseListArray,UseViewList } from "../../../components/Context/Contexts";
import { ProgressBarFunction } from "../../../components/functions/functions";

const DisplayAllLists = ({ onClickList, onCloseList, ItemsID }) => {
  const { listArray,allListAndItesm } = UseListArray();
  const { darkMode } = useDarkMode();
  const [listLenght ] = useState(-1);
  const {view}=UseViewList();


  const handleListClick = (listId) => {
    onClickList(listId);
  };

  const handleCloseItemsList = () => {
    onCloseList();
  };

  useEffect(() => {
    console.log("view: ", view);
  }, [listArray,allListAndItesm,view]);

  const apiUrl = "http://localhost:8080/lists";
  let { data, loading, error } = GetListsData({ apiUrl });

  const getListName = (listID) => {
    if (data && data.lists && data.lists.length > 0) {
      return data.lists.find((list) => list.listId === listID).name;
    }
    return "List";
  };

  const getListClassName = (itemsID, darkMode) => {
    if (darkMode) {
      if (itemsID === -1) {
        return "listBox listDarkMode";
      } else {
        return "listBoxSelectItems listDarkMode";
      }
    } else {
      if (itemsID === -1) {
        return "listBox";
      } else {
        return "listBoxSelectItems";
      }
    }
  };

  if (loading) {
    return (
      <p className={getListClassName(ItemsID, darkMode)}>
        <box-icon
          name="loader-alt"
          animation="spin"
          color="var(--primary-color)"
          size="lg"
        ></box-icon>
      </p>
    );
  }

  if (error) {
    return (
      <p className={getListClassName(ItemsID, darkMode)}>
        Error: Failed to load Lists
      </p>
    );
  }

  return (
    <div className={getListClassName(ItemsID, darkMode)}>
      {ItemsID === -1 && data && data.lists && data.lists.length > 0 ? null : (
        <div className="listDropdown">
          <ListDropdown lists={data.lists} onSelect={handleListClick} />
        </div>
      )}

      {ItemsID === -1 ? (
        data && data.lists && data.lists.length > 0 ? (
          <ul className="userLists" style={{ flexDirection: view === "list" ? "column" : "row" }}>
            {allListAndItesm.lists.map((list) => (
              <li
                key={list.listId}
                className={
                  darkMode ? "singleList singleListDark" : "singleList"
                }
                onClick={() => handleListClick(list.listId)}
              >
                <div className="listTitle">{list.name}</div>
                <div className={"listHeader"}>
                  <span className="NumberOfItems">
                    Items boughts :{" "}
                    <GetDatasFromItems
                      listID={list.listId}
                      operation={"countBought"}
                    />
                    /
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
                <div className="progressBar">
                  
                  {ProgressBarFunction(list.listId)}
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
