import { useState, useEffect } from "react";
import { endpoint } from "../datas/serverInfo";
import { GetDatasFromApi } from "../functions/getDatasFromApi";


export function setFonSize(tag, isGoodTag) {
  if (!isGoodTag) return "inherit";

  switch (tag) {
    case "h1":
      return "2.5rem";
    case "h2":
      return "2rem";
    case "h3":
      return "1.75rem";
    case "h4":
      return "1.5rem";
    case "h5":
      return "1.25rem";
    case "h6":
      return "1rem";
    case "p":
      return "1rem";
    case "span":
      return "1rem";
    case "div":
      return "1rem";
    default:
      return "inherit";
  }
}

export const useLocalStorageState = (key, initialValue) => {
  const storedValue = JSON.parse(localStorage.getItem(key)) || initialValue;
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

export function getActiveTitle(activeSection, sectionList) {
  let title = "No Title";
  sectionList.forEach((section) => {
    if (section.id === activeSection) {
      title = section.title;
    }
  });

  return title;
}

export const GetInfosFromItemList = ({ listID, operationType }) => {
  const apiUrl = `${endpoint}/lists/${listID}/items`;
  const { data } = GetDatasFromApi({ apiUrl: apiUrl });
  if (operationType === "price") {
    let price = 0;
    data?.items?.forEach((item) => {
      price += item.price*item.amount;
    });
    return price.toFixed(2);
  }
  if (operationType === "len") return data?.items?.length;
  return null;
};

export const GetInfosFromPurchasedItemsList = ({ listID, operationType }) => {
  const apiUrl = `${endpoint}/purchasedItems/lists/${listID}`;
  const { data } = GetDatasFromApi({ apiUrl: apiUrl });
  if (operationType === "price") {
    let price = 0;
    data?.purchasedItems?.forEach((item) => {
      price += item.price*item.amount;
    });
    return price.toFixed(2);
  }
  if (operationType === "len") return data?.purchasedItems?.length;
  return null;
};

export const GetInfosFromList = ({ listID, operationType }) => {
  const apiUrl = `${endpoint}/lists/${listID}`;
  const { data } = GetDatasFromApi({ apiUrl: apiUrl });
  if (operationType === "name") {
    return data?.name;
  }
  if (operationType === "description") return data?.description;
  return null;
};

/*
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
};*/