import { useState, useEffect } from "react";
import { UseListArray } from "../Context/Contexts";

const endpoint = "http://localhost:8080";
const categoriesUrl = `http://localhost:8080/categories`;
//GET DATA FROM SERVER
export const GetCategoryData = ({ apiUrl }) => {
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(apiUrl);
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const result = await response.json();

        setCategories(result.categories);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [apiUrl]);

  return { categories, loading, error };
};

export const GetListsData = ({ apiUrl }) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const { initializeArray, listArray, allListAndItesm } = UseListArray();
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(apiUrl);

        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const result = await response.json();
        setData(result);
        await initializeArray(result.lists, "list");
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [apiUrl, initializeArray]);

  return { data, loading, error };
};

export const GetItemListData = ({ apiUrl }) => {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const { initializeArray, addElement, allListAndItesm, itemsArray } =
    UseListArray();
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(apiUrl);
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const result = await response.json();
        setItems(result.items);
        await initializeArray(result.items, "items");
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [apiUrl]);

  return { items, loading, error };
};

/// purchased-itemss/lists/{listId}
export const GetPurchasedItemListData = ({ listID }) => {
  const [purchasedItems, setPurchasedItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const apiUrl = `${endpoint}/ purchased-itemss/lists/${listID}`;
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(apiUrl);
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const result = await response.json();
        setPurchasedItems(result.purchasedItems);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [apiUrl]);
  return { purchasedItems, loading, error };
};

//POST DATA TO SERVER

export const PostNewList = async (
  listName,
  description,
  maxListNameLength,
  addElement
) => {
  try {
    if (listName.length > 0 && listName.length <= maxListNameLength) {
      const response = await fetch("http://localhost:8080/lists", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ name: listName, description: description }),
      });
      const result = await response.json();
      addElement(result.lists, "list");
      if (!response.ok) {
        throw new Error("Failed to create a new list");
      }
      // window.location.reload(true);
      return true;
    } else {
      alert("Please enter a list name");
    }
  } catch (error) {
    console.error("Error creating a new list:", error);
    return false;
  }
};

export const PostNewItem = async (newItem, ItemsUrl, addElement) => {
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
    addElement(newItem, "items");
  } catch (error) {
    console.error("Error adding a new item:", error);
    return false;
  }
  return true;
};

export const PostNewCategory = async ({ NewCategoryName }) => {
  try {
    const newCategory = { name: NewCategoryName };
    const response = await fetch(categoriesUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name: newCategory.name }),
    });

    if (!response.ok) {
      throw new Error("Failed to add a new category");
    }

    return true;
  } catch (error) {
    console.error("Error adding new category:", error);
    return false;
  }
};

export const PostCheckedItem = async (item, addElement) => {
  try {
    let itemId = item.itemId;
    let listId = item.listId;
    const checkedItemsUrl = `${endpoint}/lists/${listId}/items/${itemId}/purchased`;
    const response = await fetch(checkedItemsUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(item),
    });
    if (!response.ok) {
      const responseBody = await response.json();
      console.error("Failed to add a new item:", responseBody);
      throw new Error("Failed to add a new item");
    }
    //addElement(newItem, "items");
  } catch (error) {
    console.error("Error adding a new item:", error);
    return false;
  }
  return true;
};

//Delete data from server
export const deleteItem = async (item) => {
  try {
    const response = await fetch(
      `http://localhost:8080/lists/${item.listId}/items/${item.itemId}`,
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          // Add any other headers if required (e.g., authentication headers)
        },
      }
    );

    if (!response.ok) {
      const responseBody = await response.json();
      console.error("Failed to delete item. Server response:", responseBody);
      throw new Error("Failed to delete item");
    }

    // Check if the response body is not empty before attempting to parse
    const responseBody = await response.text();
    if (responseBody.trim()) {
      console.log("Item deleted successfully");
    } else {
      console.log("Item deleted successfully (empty response body)");
    }
  } catch (error) {
    console.error("Error deleting item:", error);
  }
};

const getByText = (method, type, message) => {
  if (message === "success") {
    if (method === "POST") {
      return `${type} added successfully`;
    } else if (method === "PUT") {
      return `${type} updated successfully`;
    } else if (method === "DELETE") {
      return `${type} deleted successfully`;
    }
  }
  if (message === "fail") {
    if (method === "POST") {
      return `Failed to add ${type}`;
    } else if (method === "PUT") {
      return `Failed to update ${type}`;
    } else if (method === "DELETE") {
      return `Failed to delete ${type}`;
    }
  }
  if (message === "error") {
    if (method === "POST") {
      return `Error adding ${type}`;
    } else if (method === "PUT") {
      return `Error updating ${type}`;
    } else if (method === "DELETE") {
      return `Error deleting ${type}`;
    }
  }
  return "error in getByText function";
};

const determineTheMethod = (type, method, datas, url) => {
  let apiUrl = null,
    requestBody = null;
  switch (type) {
    case "categories":
      apiUrl = url;
      if (method === "POST") {
        requestBody = { name: datas.name };
      } else if (method === "PUT") {
        console.log("PUT method for categories not implemented yet");
      } else if (method === "DELETE") {
        console.log("DELETE method for categories not implemented yet");
      } else if (method === "GET") {
      }
      break;
    case "items":
      if (method === "POST") {
        apiUrl = url;
        requestBody = datas;
      } else if (method === "PUT") {
        apiUrl = `${url}/${datas.itemId}`;
        requestBody = datas;
      } else if (method === "DELETE") {
        console.log("DELETE method for items not implemented yet");
      } else if (method === "GET") {
        console.log("GET method for items not implemented yet");
      }
      break;
    case "lists":
      if (method === "POST") {
        apiUrl = url;
        requestBody = datas;
      } else if (method === "PUT") {
        apiUrl = `${url}/${datas.listId}`;
        requestBody = datas;
      } else if (method === "DELETE") {
        apiUrl = `${url}/${datas.listId}`;
        requestBody = datas;
      } else if (method === "GET") {
        console.log("GET method for items not implemented yet");
      }
      break;
  }
  return { apiUrl, requestBody };
};

export async function GetDatas(type, url) {
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(url);
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const result = await response.json();
        setCategories(result.categories);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [url]);

  return { categories, loading, error };
}

export const HandleDataRequest = ({
  DataType,
  Apiurl,
  datasToSend,
  methodTobeUsed,
}) => {
  const fetchReceivedDatas = async (method, type, datas, url) => {
    if (method === "GET") {
      return await GetDatas(type, method, datas, url);
    }
  };
  return fetchReceivedDatas(methodTobeUsed, DataType, datasToSend, Apiurl);
};

export async function DataRequest(typeOfdataRequested, method, url, datas) {
  if (method === "GET") {
    let { categories } = await GetDatas(typeOfdataRequested, url);

    return { categories };
  }
}

//export default HandleDataRequest;
