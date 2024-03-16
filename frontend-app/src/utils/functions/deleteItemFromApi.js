import { endpoint } from "../data/serverInfo";

const deleteItemFromApi = async (url) => {
  const response = await fetch(url, {
    method: "DELETE",
  });
  return response;
};

export const deleteList = async (listId, handleDelete) => {
  try {
    const response = await deleteItemFromApi(`${endpoint}/lists/${listId}`);
    if (response.status === 200) {
      handleDelete(listId);
      return true;
    } else {
      console.error("Failed to delete the list:", response);
      return false;
    }
  } catch (error) {
    console.error("Error deleting the list:", error);
    return false;
  }
};

export const deleteItem = async (itemId, listId) => {
  try {
    const response = await deleteItemFromApi(
      `${endpoint}/lists/${listId}/items/${itemId}`
    );
    if (response.status === 200) {
      return true;
    } else {
      console.error("Failed to delete the item:", response);
      return false;
    }
  } catch (error) {
    console.error("Error deleting the item:", error);
    return false;
  }
};
