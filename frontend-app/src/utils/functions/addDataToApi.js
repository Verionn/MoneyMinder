import { categoriesUrl } from "../data/serverInfo";

export const PostNewList = async (listName, description, appendNewElement) => {
  try {
    const token = localStorage.getItem("token");
    
    const response = await fetch("http://localhost:8080/lists", {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name: listName, description: description }),
    });
    const newElement = await response.json();
    
    appendNewElement(newElement);
    if (!response.ok) {
      throw new Error("Failed to create a new list");
    }

    return true;
  } catch (error) {
    console.error("Error creating a new list:", error);
    return false;
  }
};


export  const PostNewItem = async (newItem, ItemsUrl, appendNewElement,listType) => {
  try {
    console.log(newItem);
    const token = localStorage.getItem("token");
    const response = await fetch(ItemsUrl, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newItem),
    });
    if (!response.ok) {
      const responseBody = await response.json();
      console.error("Failed to add a new item:", responseBody);
      throw new Error("Failed to add a new item");
    }
    const newElement = await response.json();
    if(listType==='purchasedItems'){}
    else appendNewElement(newElement);
  } catch (error) {
    console.error("Error adding a new item:", error);
    return false;
  }
  return true;
};


export const PostNewCategory = async (CategoryName) => {
  try {
    const token = localStorage.getItem("token");
    
    const response = await fetch(categoriesUrl, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name: CategoryName }),
    });
    const newElement = await response.json();
    
   // appendNewElement(newElement);
    if (!response.ok) {
      throw new Error("Failed to create a new list");
    }

    return true;
  } catch (error) {
    console.error("Error creating a new list:", error);
    return false;
  }
};
