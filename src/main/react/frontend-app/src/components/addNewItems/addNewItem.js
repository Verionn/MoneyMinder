import React, { useState } from "react";
import CloseButton from "react-bootstrap/CloseButton";
import "./addNewItem.css";
import GetCategories from "../communicationWithBackEnd/GetCotegories";
import ItemForm from "./ItemForm";
import AddCategory from "../communicationWithBackEnd/addCategory";
const AddNewItem = ({ listID, onClick, ItemsUrl, items }) => {
  const [FormData, setFormData] = useState({
    itemName: "",
    customItemName: "",
    quantity: "",
    price: "",
    category: "",
    customCategoryName: "",
    weight: "",
  });

  const apiUrl = `http://localhost:8080/categories`;
  let { categories, loading, error } = GetCategories({ apiUrl });

  const GetCategoriesID = (categoryName) => {
    let categoryID = 0;
    let found = false;
    categories.forEach((category) => {
      if (category.name === categoryName) {
        console.log(category.name + " compare to " + categoryName);
        categoryID = category.categoryId;
        found = true;
      }
    });
    if (found) return categoryID;
    else {
      AddCategory({ categoryName });
      console.log("new category added" + categories.length+1);
      return categories.length;
    }
  };

  const handleAddNewItem = async (DatasToFetch) => {
    console.log(DatasToFetch.itemName);
    console.log(DatasToFetch.customItemName);
    let categoryName =
      DatasToFetch.customCategoryName === ""
        ? DatasToFetch.category
        : DatasToFetch.customCategoryName;

    let categoryID = GetCategoriesID(categoryName);
   
    const newItem = {
      name:
        DatasToFetch.itemName === "custom"
          ? DatasToFetch.customItemName
          : DatasToFetch.itemName,
      listId: listID,
      price: parseFloat(DatasToFetch.price),
      amount: parseInt(DatasToFetch.quantity),
      categoryId: categoryID,
      weight: parseFloat(DatasToFetch.weight),
      timeCreated: new Date().toISOString(),
    };

    try {
      // Send a request to create a new list
      console.log(newItem);
      const response = await fetch(ItemsUrl, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newItem),
      });
      console.log("Response:", response);
      if (!response.ok) {
        const responseBody = await response.json();
        console.error("Failed to add a new item:", responseBody);
        throw new Error("Failed to add a new item");
      }
    } catch (error) {
      console.error("Error adding a new item:", error);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...FormData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    handleAddNewItem(FormData);
    setFormData({
      itemName: "",
      customItemName: "",
      quantity: "",
      price: "",
      category: "",
      customCategoryName: "",
      weight: "",
    });
  };

  const handleOncloseclick = () => {
    onClick();
  };

  return (
    <div className="addingItems">
      <div className="HeaderAddItems">
        <p>Add new item</p>
        <CloseButton onClick={onClick} />
      </div>

      <div className="BodyAddItems">
        <ItemForm
          onSubmit={handleSubmit}
          onChange={handleInputChange}
          formData={FormData}
          categories={categories}
          items={items}
        />
      </div>
    </div>
  );
};

export default AddNewItem;
