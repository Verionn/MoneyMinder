import { endpoint } from "../data/serverInfo";

export const modifyList = async (listId, updatedData,updateListArray) => {
  try {
   
    const response = await fetch(`${endpoint}/lists/${listId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedData),
    });

    if (response.ok) {
      updateListArray(listId,updatedData);
      return true;
    } else {
      console.error("Failed to modify the list:", response);
    }
  } catch (error) {
    console.error("Error modifying the list:", error);
  }
  return false;
};
