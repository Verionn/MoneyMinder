import { useContextElements } from "../../utils/hooks/customHooks";
import { GetDatasFromApi } from "../../utils/functions/getDatasFromApi";
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
import ModifyList from "./modifyList";
import {
  GetInfosFromItemList,
  GetInfosFromPurchasedItemsList,
} from "../../utils/functions/function";
import { SingleListStats } from "./singleListStats";
import { useNavigate } from "react-router-dom";

const ShoppingLists = () => {
  const { listArray, updateListArray, isDarkMode } = useContextElements();
  const apiURL = `${endpoint}/lists`;
  const { data, loading, error } = GetDatasFromApi({ apiUrl: apiURL });
  const [isShowingDescription, setIsShowingDescription] = useState([]);
  const [isDeletingList, setIsDeletingList] = useState(-1);
  const [isModifyingName, setIsModifyingName] = useState(-1);
  const [isModifyingDescription, setIsModifyingDescription] = useState(-1);
  const navigate = useNavigate();
  

  useEffect(() => {
    if (data && data.lists) {
      updateListArray(data);
    }
  }, [data, updateListArray]);
  useEffect(() => {}, [listArray]);

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
  const toggleModifyIcon = (index) => {
    setIsModifyingName(index);
    setIsModifyingDescription(index);
    toggleDescription(index);
  };

  const handleListClick = (listId) => {
    navigate(`/shopping-list/${listId}`);
  };

  return (
    <div className="listContainer">
      {listArray?.lists?.map((list, index) => (
        <div
          key={`list-${list.listId}`}
          className="singleList"
          style={{ ...styles.singleList }}
          onClick={() => handleListClick(list.listId)}
        >
          <div className="singleListHeader">
            {isModifyingName === index ? (
              <ModifyList
                modifying={"name"}
                listID={list.listId}
                listName={list.name}
                description={list.description}
                setDefault={setIsModifyingName}
              />
            ) : (
              <div
                onClick={() => setIsModifyingName(index)}
                style={{ cursor: "pointer" }}
                className="listNameInListContainer"
              >
                {list.name}
              </div>
            )}
            <div className="listIcons">
              <EditIcon
                style={{ ...styles.icons }}
                onClick={() => toggleModifyIcon(index)}
              />
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
              <p>
                {`Items Boughts : `}
                <GetInfosFromPurchasedItemsList
                  listID={list.listId}
                  operationType={"len"}
                />
                {" / "}
                <GetInfosFromItemList
                  listID={list.listId}
                  operationType={"itemsCount"}
                />
              </p>
              <p>
                {`Total Price : `}{" "}
                <GetInfosFromItemList
                  listID={list.listId}
                  operationType={"price"}
                />{" "}
                $
              </p>
            </div>
            <div className="ListProgessionBar">
              <SingleListStats listID={list.listId} />
            </div>
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
              {isShowingDescription[index] &&
                (isModifyingDescription === index ? (
                  <ModifyList
                    modifying="description"
                    listID={list.listId}
                    listName={list.name}
                    description={list.description}
                    setDefault={setIsModifyingDescription}
                  />
                ) : (
                  <p
                    style={{ ...styles.listExpanded }}
                    className="listExpanded"
                    onClick={() => setIsModifyingDescription(index)}
                  >
                    {list.description || "No description provided."}
                  </p>
                ))}
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default ShoppingLists;
