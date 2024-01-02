// GetNumberOfItems.js
import React, { useEffect, useState } from "react";
import { GetItemListData } from "../communicationWithServer/HandleDataRequest";
import { NotificationManager } from "react-notifications";
import ProgressBar from "react-bootstrap/ProgressBar";
import { useListArray } from "../Context/Contexts";

const GetDatasFromItems = ({ listID, operation }) => {
  const apiUrl = `http://localhost:8080/lists/${listID}/items`;

  let { items, loading, error } = GetItemListData({ apiUrl });
  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }
  if (operation === "count") return items.length;
  else if (operation === "countBought") {
    return listID;
  } else if (operation === "price") return calculateTotalPrice(items);
};
export default GetDatasFromItems;

const calculateTotalPrice = (items) => {
  return items
    .reduce((total, item) => total + item.price * item.amount, 0)
    .toFixed(2);
};

export const GetCategoryNameID = async (categoryName, categories) => {
  let categoryID = 0;
  let found = false;

  categories.forEach((category) => {
    if (category.name === categoryName) {
      categoryID = category.categoryId;
      found = true;
    }
  });
  if (found) return categoryID;
  return -1;
};

export const useLocalStorageState = (key, initialValue) => {
  const storedValue = JSON.parse(localStorage.getItem(key)) || initialValue;
  // console.log(`Key: ${key}, Stored Value: ${storedValue}`);
  const [value, setValue] = useState(storedValue);

  useEffect(() => {
    const storedItem = localStorage.getItem(key);
    if (storedItem !== null) {
      setValue(JSON.parse(storedItem));
    }
  }, [key]);

  useEffect(() => {
    localStorage.setItem(key, JSON.stringify(value));
  }, [key, value]);

  return [value, setValue];
};

export const CreateNotification = (type, message) => {
  switch (type) {
    case "info":
      NotificationManager.info(message);
      break;
    case "success":
      console.log("success");
      NotificationManager.success(message);

      break;
    case "warning":
      NotificationManager.warning(message, "Close after 3000ms", 3000);
      break;
    case "error":
      NotificationManager.error(message, "Close after 5000ms", 5000, () => {
        alert("callback");
      });
      break;
    default:
      break;
  }
};

function getPercentForProgressBar(bought, items) {
  if (items === 0) return 0;
  return parseInt((bought / items) * 100, 10);
}

/*const getItemsByListId = (targetListId, allItemsArray) => {
  if(allItemsArray.length === 0) return console.log("allItemsArray is empty");
  for (let i = 0; i < allItemsArray.length; i++) {

    if (allItemsArray[i].items.itemId === targetListId) {
      return allItemsArray[i];
    }
  }
};*/

export function ProgressBarFunction(list) {
  const { allItemsArray } = useListArray();
//console.log("checking :", getItemsByListId(list.listId, allItemsArray));
  const now = getPercentForProgressBar(1, 3);
  return (
    <ProgressBar
      now={now}
      label={`${now}%`}
      className="ProgessBarInside"
      variant="customVariant"
    />
  );
}
