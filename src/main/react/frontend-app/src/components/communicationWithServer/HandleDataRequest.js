import { useState, useEffect } from "react";
let endpoint = "http://localhost:8080";
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

export async function GetDatas (type, url) {
  console.log("type: ", type, " url: ", url);
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
  console.log("categories: ", categories);
  return { categories, loading, error };
};
export const HandleDataRequest = ({
  DataType,
  Apiurl,
  datasToSend,
  methodTobeUsed,
}) => {
  const fetchReceivedDatas = async (method, type, datas, url) => {
    console.log(
      "method: ",
      method,
      " type: ",
      type,
      " datas: ",
      datas,
      " url: ",
      url
    );
    if (method === "GET") {
      return await GetDatas(type, method, datas, url);
    }
  };
  return fetchReceivedDatas(methodTobeUsed, DataType, datasToSend, Apiurl);
};

export async function DataRequest  (typeOfdataRequested, method, url, datas) {
  console.log(
    "method: ",
    method,
    " type: ",
    typeOfdataRequested,
    " datas: ",
    datas,
    " url: ",
    url
  );
  if (method === "GET") {
    let { categories, loading, error } = await GetDatas(
      typeOfdataRequested,
      url
    );
    console.log("categories second check: ", categories);
    return { categories };
  }
};

//export default HandleDataRequest;
