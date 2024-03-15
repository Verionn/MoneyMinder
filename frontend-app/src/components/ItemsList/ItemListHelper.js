import { useNavigate } from "react-router-dom";
import { useContextElements } from "../../utils/hooks/customHooks";
import { GetDataFromApi } from "../../utils/functions/getDataFromApi";
import { endpoint } from "../../utils/data/serverInfo";
import { useEffect, useState } from "react";
import { deleteItem } from "../../utils/functions/deleteItemFromApi";
import { PostNewItem } from "../../utils/functions/addDataToApi";
import "./ItemList.css";
export const ListSelectionNavigator = ({ listId }) => {
  const { listArray, updateListArray } = useContextElements();
  const apiURL = `${endpoint}/lists`;
  const { data, loading, error } = GetDataFromApi({ apiUrl: apiURL });
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
        <option value="">Select list</option>
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

export const DisplaySelectedItems = (selectedItems, data) => {
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

export const initiateItemsDeletion = (setIsDeletting, selectedItems) => {
  if (selectedItems.items.size > 0) {
    setIsDeletting(1);
  } else alert("No items selected");
};

export const deleteSelectedItems = async (
  listId,
  selectedItems,
  data,
  setSelectedItems,
  setIsDeletting,
  handleDeleteInItemArray
) => {
  let numberOfDeletedItems = 0;
  for (const itemId of selectedItems.items) {
    const item = data.items.find((item) => item.itemId === itemId);
    if (item) {
      try {
        const success = await performItemDeletion(item.itemId, listId);
        if (success) {
          numberOfDeletedItems++;
          const succed = await handleDeleteInItemArray(listId, item.itemId);
          if (succed) console.log("item deleting ", item.ItemId);
        }
      } catch (err) {
        console.error("Error deleting item:", item.itemId, err);
      }
    }
  }
  setSelectedItems({
    items: new Set(),
    purchasedItems: new Set(),
  });

  setIsDeletting(-1);
  if (numberOfDeletedItems >= 2) window.location.reload();
};

export const performItemDeletion = async (itemId, listId) => {
  const success = await deleteItem(itemId, listId);
  return success;
};

export const initiateItemsCheck = (setIsChecking, selectedItems) => {
  if (selectedItems.items.size > 0) {
    setIsChecking(1);
  } else alert("No items selected");
};

export const checkAndProcessPurchasedItems = async (
  setIsChecking,
  selectedItems,
  setSelectedItems,
  data,
  listId,
  handleAddPurchasedItem,
  handleDeleteInItemArray
) => {
  let numberOfDeletedItems = 0;
  for (const itemId of selectedItems.items) {
    const item = data.items.find((item) => item.itemId === itemId);

    if (item) {
      try {
        const success = await addItemsToPurchasedList(
          item,
          item.itemId,
          listId,
          handleAddPurchasedItem
        );
        if (success) {
          numberOfDeletedItems++;
          let succed = handleAddPurchasedItem(item);
          if (succed) console.log("item checked ", item.ItemId);
          succed = handleDeleteInItemArray(listId, item.itemId);
          if (succed) console.log("item checked ", item.ItemId);
        }
      } catch (err) {
        console.error("Error deleting item:", item.itemId, err);
      }
    }
  }
  setSelectedItems({
    items: new Set(),
    purchasedItems: new Set(),
  });
  setIsChecking(-1);
  if (numberOfDeletedItems >= 2) window.location.reload();
};

export const addItemsToPurchasedList = async (
  newItem,
  itemId,
  listId,
  appendNewElement
) => {
  // POST /lists/{listId}/items/{itemId}/purchased
  const ItemsUrl = `${endpoint}/lists/${listId}/items/${itemId}/purchased`;
  const success = await PostNewItem(
    newItem,
    ItemsUrl,
    appendNewElement,
    "purchasedItems"
  );
  return success;
};
