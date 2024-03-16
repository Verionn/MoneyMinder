import { useState, useEffect } from "react";
import { endpoint } from "../data/serverInfo";
import { GetDataFromApi } from "./getDataFromApi";
import { useGetInfosFromPurchasedItemsList } from "../hooks/customHooks";

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
  const {
    result: purchasedItemListLength,
    loading: purchasedItemListLoading,
    error: purchasedItemListError,
  } = useGetInfosFromPurchasedItemsList({ listID, operationType: "len" });

  const {
    result: purchasedItemPrice,
    loading: purchasedItemListLoadingPrice,
    error: purchasedItemListErrorPrice,
  } = useGetInfosFromPurchasedItemsList({ listID, operationType: "price" });

  const apiUrl = `${endpoint}/lists/${listID}/items`;
  const { data } = GetDataFromApi({ apiUrl: apiUrl });

  if (data && purchasedItemListLength && purchasedItemPrice) {
    if (operationType === "price") {
      let price = 0;
      data?.items?.forEach((item) => {
        price += item.price * item.amount;
      });
      return price.toFixed(2);
    }
    if (operationType === "len") return data?.items?.length;
    if (operationType === "itemsCount") {
      if (purchasedItemListLoading) return null;
      if (purchasedItemListError) return null;

      return data?.items?.length + purchasedItemListLength;
    }
    if (operationType === "allItemsPrice") {
      let price = 0;
      data?.items?.forEach((item) => {
        price += item.price * item.amount;
      });
      if (purchasedItemListLoadingPrice) return null;
      if (purchasedItemListErrorPrice) return null;
      return (Number(purchasedItemPrice) + Number(price)).toFixed(2);
    }
  }

  return 0;
};

export const GetInfosFromPurchasedItemsList = ({ listID, operationType }) => {
  const apiUrl = `${endpoint}/purchased-items/lists/${listID}`;
  const { data } = GetDataFromApi({ apiUrl: apiUrl });
  if (data) {
    if (operationType === "price") {
      let price = 0;
      data?.purchasedItems?.forEach((item) => {
        price += item.price * item.amount;
      });
      return price.toFixed(2);
    }
    if (operationType === "len") return data?.purchasedItems?.length;
  }
  return 0;
};

export const GetInfosFromList = ({ listID, operationType }) => {
  const apiUrl = `${endpoint}/lists/${listID}`;
  const { data } = GetDataFromApi({ apiUrl: apiUrl });
  if (data) {
    if (operationType === "name") {
      return data?.name;
    }
    if (operationType === "description") return data?.description;
  }

  return null;
};

export function isElementInListCategory(CategoryList, categoryName) {
  console.log(CategoryList, categoryName);
  let isCategoryInList = false;
  CategoryList.forEach((category) => {
    if (category.name === categoryName) {
      isCategoryInList = true;
    }
  });
  return isCategoryInList;
}

export function findIdCategoryByName(CategoryList, categoryName) {
  let categoryId = null;
  CategoryList.forEach((category) => {
    if (category.name === categoryName) {
      categoryId = category.categoryId;
    }
  });
  return categoryId;
}

export function findCategoryNameById  (CategoryList, categoryId) {
  let categoryName = null;
  CategoryList.forEach((category) => {
    if (category.categoryId === categoryId) {
      categoryName = category.name;
    }
  });
  return categoryName;
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
