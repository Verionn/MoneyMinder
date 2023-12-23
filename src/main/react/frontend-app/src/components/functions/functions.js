// GetNumberOfItems.js
import React, { useEffect, useState } from "react";
import { GetItemListData } from "../communicationWithServer/HandleDataRequest";
import {
  NotificationContainer,
  NotificationManager,
} from "react-notifications";

const GetDatasFromItems = ({ listID, operation }) => {
  const apiUrl = `http://localhost:8080/lists/${listID}/items`;

  let { items, loading, error } = GetItemListData({ apiUrl });
  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }
  if (operation === "count") return <div>{items.length}</div>;
  else if (operation === "price")
    return <div>{calculateTotalPrice(items)}</div>;
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
