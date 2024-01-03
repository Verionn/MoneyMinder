/*
import React, { useState } from "react";



const GetCategoriesID = (categoryName) => {

    let categoryID = 0;
    let found = false;
    categories.forEach((category) => {
      if (category.name === categoryName) {
        categoryID = category.categoryId;
        found = true;
      }
    });
    if (found) return categoryID;
    else {
      if (AddCategory({ categoryName }) === false) return -1;
     /* setNewCategory(true);
      setMessage("New category added successfully!");
      handleShowNotification();
      return categories.length + 1;
    }
  };
const updateItemTab = async (existingItem, ItemsUrl) => {
  try {
    const response = await fetch(`${ItemsUrl}/${existingItem.itemId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(existingItem),
    });

    if (!response.ok) {
      const responseBody = await response.json();
      console.error("Failed to update the item:", responseBody);
      throw new Error("Failed to update the item");
    }
  } catch (error) {
    console.error("Error updating the item:", error);
    return -1;
  }
  return 0;
};

const AddNewItem = async ({ newItem, ItemsUrl }) => {
  try {
    const response = await fetch(ItemsUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newItem),
    });
    if (!response.ok) {
      const responseBody = await response.json();
      console.error("Failed to add a new item:", responseBody);
      throw new Error("Failed to add a new item");
    }
  } catch (error) {
    console.error("Error adding a new item:", error);
    return -1;
  }

  return 0;
};

const OraganizeNewItem = (DatasToFetch, listID) => {
  let categoryName =
    DatasToFetch.customCategoryName === ""
      ? DatasToFetch.category
      : DatasToFetch.customCategoryName;

  let categoryID = GetCategoriesID(categoryName);
  const newItem = {
    name:
      DatasToFetch.itemName === "custom"
        ? DatasToFetch.customItemName
        : DatasToFetch.itemName,
    listId: listID,
    price: DatasToFetch.price === "" ? 0 : parseFloat(DatasToFetch.price),
    amount: DatasToFetch.quantity === "" ? 1 : parseInt(DatasToFetch.quantity),
    categoryId: categoryID,
    weight: DatasToFetch.weight === "" ? 0 : parseFloat(DatasToFetch.weight),
    timeCreated: new Date().toISOString(),
  };
  return newItem;
};

const handleAddNewItem = async (DatasToFetch, itemsTab) => {
  const newItem = OraganizeNewItem(DatasToFetch);
  const existingItem = itemsTab.find((item) => item.name === newItem.name);

  if (existingItem) {
    const updatedItem = {
      ...existingItem,
      price: newItem.price === 0 ? existingItem.price : newItem.price,
      amount: newItem.amount === 1 ? existingItem.amount + 1 : newItem.amount,
      categoryId: newItem.categoryId,
      weight: newItem.weight === 0 ? existingItem.weight : newItem.weight,
      timeCreated: new Date().toISOString(),
    };
    if (updateItemTab(updatedItem) === -1) return -1;
  } else {
    if (AddNewItem(newItem) === -1) return -1;
    /*setNewCategory(true);
    setMessage("New Item added successfully!");
    handleShowNotification();
  }
};

export const test = () => {
  console.log("test");
};
*/