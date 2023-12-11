import React from "react";

const AddCategory = ({ categoryName }) => {
  const handleCreateNewCategory = async (newCategoryData) => {
    try {
      const response = await fetch("http://localhost:8080/categories", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({name: newCategoryData.name} ),
      });

      if (!response.ok) {
        throw new Error("Failed to add a new category");
      }

      
    } catch (error) {
      console.error("Error adding new category:", error);
      return false;
    }
    
    return true;
  };
  const newCategory = { name: categoryName };

  if (handleCreateNewCategory(newCategory)) return true;
  return false;
};

export default AddCategory;
