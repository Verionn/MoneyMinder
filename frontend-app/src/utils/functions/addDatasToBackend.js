export const PostNewList = async (listName, description) => {
  try {
    const response = await fetch("http://localhost:8080/lists", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name: listName, description: description }),
    });
     await response.json();
    if (!response.ok) {
      throw new Error("Failed to create a new list");
    }
   
    return true;
  } catch (error) {
    console.error("Error creating a new list:", error);
    return false;
  }
};
