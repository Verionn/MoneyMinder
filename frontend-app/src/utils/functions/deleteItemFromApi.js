import { endpoint } from "../datas/serverInfo";

const deleteItemFromApi = async (url) => {
  const response = await fetch(url, {
    method: "DELETE",
  });
  return response;
};

export const deleteList = async (listId) => {
  try {
    // Send a DELETE request to the backend
    const response = await deleteItemFromApi(`${endpoint}/lists/${listId}`);
    if (response.status === 200) {
      return true;
    } else {
      // Handle any unsuccessful response here
      console.error("Failed to delete the list:", response);
      return false;
    }
  } catch (error) {
    // Handle any errors that occurred during the fetch
    console.error("Error deleting the list:", error);
    return false;
  }
};
