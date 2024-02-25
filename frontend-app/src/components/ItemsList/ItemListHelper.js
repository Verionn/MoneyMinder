import { useNavigate } from "react-router-dom";
import { useContextElements } from "../../utils/hooks/customHooks";
import { GetDatasFromApi } from "../../utils/functions/getDatasFromApi";
import { endpoint } from "../../utils/datas/serverInfo";
import { useEffect, useState } from "react";
import { deleteItem } from "../../utils/functions/deleteItemFromApi";
import "./ItemList.css";
export const NavigateToSelectedList = ({ listId }) => {
  const { listArray, updateListArray } = useContextElements();
  const apiURL = `${endpoint}/lists`;
  const { data, loading, error } = GetDatasFromApi({ apiUrl: apiURL });
  const [selectedValue, setSelectedValue] = useState("");
  const navigate = useNavigate();
  useEffect(() => {
    if (data && data.lists) {
      updateListArray(data);
    }
  }, [data, updateListArray]);

  useEffect(() => {
    if (listArray.lists) {
      const currentList = listArray.lists.find(
        (list) => list.listId.toString() === listId
      );
      if (currentList) {
        setSelectedValue(currentList.name);
      }
    }
  }, [listArray, listId]);

  const handleChange = (event) => {
    const newValue = event.target.value;
    setSelectedValue(newValue);
    navigate(`/shopping-list/${newValue}`);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;
  return (
    <div className="SelectList">
      <select
        value={selectedValue}
        onChange={handleChange}
        className="dropdownMenu"
      >
        {listArray.lists &&
          listArray.lists.map((list, index) => (
            <option key={index} value={list.listId}>
              {list.name}
            </option>
          ))}
      </select>
    </div>
  );
};

export const GetListOfSelectedItems = (selectedItems, data) => {
  let items = [];
  selectedItems.items.forEach((itemId) => {
    data.items.forEach((item) => {
      if (item.itemId === itemId) {
        items.push(item);
      }
    });
  });

  return (
    <div>
      {items.map((item, index) => (
        <div key={`item-${item.itemId}`}>
          <p>{item.name}</p>
        </div>
      ))}
    </div>
  );
};

export const handleDeleteItems = (setIsDeletting, selectedItems) => {
  if (selectedItems.items.size > 0) {
    setIsDeletting(1);
  } else alert("No items selected");
};

export const handleAllDelete = (
  listId,
  selectedItems,
  data,
  setSelectedItems,
  setIsDeletting
) => {
  let items = [];
  selectedItems.items.forEach((itemId) => {
    data.items.forEach((item) => {
      if (item.itemId === itemId) {
        items.push(item);
      }
    });
  });
  let purchasedItems = [];
  selectedItems.purchasedItems.forEach((itemId) => {
    purchasedItems.items.forEach((item) => {
      if (item.itemId === itemId) {
        purchasedItems.push(item);
      }
    });
  });
  items.forEach((item) => {
    handleDelete(item.itemId, listId);
  });
  purchasedItems.forEach((item) => {
    handleDelete(item.itemId, listId);
  });
  setSelectedItems({
    items: new Set(),
    purchasedItems: new Set(),
  });
  setIsDeletting(-1);
};

export const handleDelete = (itemId, listId) => {
  deleteItem(itemId, listId);
};
