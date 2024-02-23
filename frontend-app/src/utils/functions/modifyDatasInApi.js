import { endpoint } from "../datas/serverInfo";

export const modifyList = async (listId, updatedData) => {
  try {
    console.log("listId", `${endpoint}/lists/${listId}`);
    const response = await fetch(`${endpoint}/lists/${listId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedData),
    });

    if (response.ok) {
      return true;
    } else {
      console.error("Failed to modify the list:", response);
    }
  } catch (error) {
    console.error("Error modifying the list:", error);
  }
  return false;
};
