export const PostNewList = async (listName, description, appendNewElement) => {
  try {
    const response = await fetch("http://localhost:8080/lists", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name: listName, description: description }),
    });
    const newElement = await response.json();
    console.log("newElement", newElement);
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
