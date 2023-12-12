import React, { useState } from "react";
import CloseButton from "react-bootstrap/CloseButton";
import "./addNewItem.css";
import GetCategories from "../communicationWithBackEnd/GetCotegories";
import ItemForm from "./ItemForm";
import AddCategory from "../communicationWithBackEnd/addCategory";
import Toast from 'react-bootstrap/Toast';
import ToastContainer from 'react-bootstrap/ToastContainer';
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

  const [NewCategory, setNewCategory] = useState(false);
  const [message, setMessage] = useState("");
  const [showNotification, setShowNotification] = useState(false);

  const handleShowNotification = () => {
    setShowNotification(true);
    setTimeout(() => setShowNotification(false), 3000);
  };

  const apiUrl = `http://localhost:8080/categories`;
  let { categories, loading, error } = GetCategories({ apiUrl });

  const GetCategoriesID = (categoryName) => {
    let categoryID = 0;
    let found = false;
    categories.forEach((category) => {
      if (category.name === categoryName) {
        categoryID = category.categoryId;
        found = true;
      }
    });
    if (found) return categoryID;
    else {
      if (AddCategory({ categoryName }) === false) return -1;
      setNewCategory(true);
      setMessage("New category added successfully!");
      handleShowNotification();
      return categories.length + 1;
    }
  };

  const OraganizeNewItem = (DatasToFetch) => {
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
      price: DatasToFetch.price === "" ? 0 : parseFloat(DatasToFetch.price),
      amount:
        DatasToFetch.quantity === "" ? 1 : parseInt(DatasToFetch.quantity),
      categoryId: categoryID,
      weight: DatasToFetch.weight === "" ? 0 : parseFloat(DatasToFetch.weight),
      timeCreated: new Date().toISOString(),
    };
    return newItem;
  };

  const handleAddNewItem = async (DatasToFetch) => {
    const newItem = OraganizeNewItem(DatasToFetch);

    try {
      const response = await fetch(ItemsUrl + `?${new Date().getTime()}`, {
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
    } catch (error) {
      console.error("Error adding a new item:", error);
    }
    setNewCategory(true);
    setMessage("New Item added successfully!");
    handleShowNotification();
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
    <div>
      <div className="addingItems">
        <div className="HeaderAddItems">
          <p>Add new item</p>
          <CloseButton onClick={handleOncloseclick} />
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
      {NewCategory && (
        <ToastContainer
          className="p-3"
          position="bottom-end"
          style={{ zIndex: 1 }}
        >
          <Toast
            show={showNotification}
            onClose={() => setShowNotification(false)}
          >
            <Toast.Body className="Notfication">{message}</Toast.Body>
          </Toast>
        </ToastContainer>
      )}
    </div>
  );
};

export default AddNewItem;
