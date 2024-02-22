import { useContextElements } from "../../utils/hooks/customHooks";
import { GetListsData } from "../../utils/functions/getDatasFromApi";
import { endpoint } from "../../utils/datas/serverInfo";
import { useEffect } from "react";
import "./shoppingLists.css";
import { Styles } from "./styles";
import { useState } from "react";
import {
  TrashIcon,
  EditIcon,
  ChevronDown,
} from "../sharedComponents/icons/svgIcons";
import { ConfirmAction } from "../sharedComponents/confirmAction";

const ShoppingLists = () => {
  const { listArray, updateListArray, isDarkMode } = useContextElements();
  const apiURL = `${endpoint}/lists`;
  const { data, loading, error } = GetListsData({ apiUrl: apiURL });
  const [isShowingDescription, setIsShowingDescription] = useState([]);
  const [isDeletingList, setIsDeletingList] = useState(-1);
  useEffect(() => {
    if (data) {
      updateListArray(data);
    }
  }, [data, updateListArray]);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  const styles = Styles({ darMokde: isDarkMode });
  const toggleDescription = (index) => {
    setIsShowingDescription((prevState) => {
      const newState = [...prevState];
      newState[index] = !newState[index];
      return newState;
    });
  };
  const toglleDeleteList = (index) => {
    setIsDeletingList(index);
  };
  return (
    <div className="listContainer">
      {listArray?.lists?.map((list, index) => (
        <div
          key={`list-${list.listId}`}
          className="singleList"
          style={{ ...styles.singleList }}
        >
          <div className="singleListHeader">
            {list.name}
            <div className="listIcons">
              <EditIcon style={{ ...styles.icons }} />
              <TrashIcon
                style={{ ...styles.icons }}
                onClick={() => toglleDeleteList(index)}
              />
              <ConfirmAction
                show={index === isDeletingList}
                listName={list.name}
                setIsDeletingList={setIsDeletingList}
                listId={list.listId}
              />
            </div>
          </div>
          <div className="singleListBody">
            <div className="SingleListStats">
              <p>{`Items Boughts : 1/3`}</p>
              <p>{`Total Price : 12$`}</p>
            </div>
            <div className="ListProgessionBar">Progress Bar</div>
            <div
              className={`ListDescription ${
                isShowingDescription[index] ? "active" : ""
              }`}
            >
              <button
                onClick={() => toggleDescription(index)}
                className="ButtonExpandList"
              >
                Description <ChevronDown />{" "}
              </button>
              {isShowingDescription[index] && (
                <p style={{ ...styles.listExpanded }} className="listExpanded">
                  {list.description || "No description provided."}
                </p>
              )}
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default ShoppingLists;
