import React, { useState } from "react";
import CloseButton from "react-bootstrap/CloseButton";
import "./addNewItem.css";
import { GetCategoryNameID } from "../functions/functions";
import {
  GetCategoryData,
  PostNewItem,
  PostNewCategory,
} from "../communicationWithServer/HandleDataRequest";
import { useListArray } from "../Context/Contexts";
import ItemForm from "./ItemForm";
import Toast from "react-bootstrap/Toast";
import ToastContainer from "react-bootstrap/ToastContainer";

const AddNewItem = ({ listID, onClick, ItemsUrl, items }) => {
  const { addElement } = useListArray();
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
  let { categories } = GetCategoryData({ apiUrl });

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
      if (PostNewCategory({ categoryName }) === false) return -1;
      setNewCategory(true);
      setMessage("New category added successfully!");
      handleShowNotification();
      // window.location.reload(true);
      return GetCategoryNameID(categoryName, categories);
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

  const handleAddNewItem = async (DatasToFetch, itemsTab) => {
    const newItem = OraganizeNewItem(DatasToFetch);

    const existingItem = itemsTab.find((item) => item.name === newItem.name);

    if (existingItem) {
      // If item exists, update its details
      const updatedItem = {
        ...existingItem,
        price: newItem.price === 0 ? existingItem.price : newItem.price,
        amount: newItem.amount === 1 ? existingItem.amount + 1 : newItem.amount,
        categoryId: newItem.categoryId,
        weight: newItem.weight === 0 ? existingItem.weight : newItem.weight,
        timeCreated: new Date().toISOString(),
      };

      try {
        const response = await fetch(`${ItemsUrl}/${existingItem.itemId}`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(updatedItem),
        });

        if (!response.ok) {
          const responseBody = await response.json();
          console.error("Failed to update the item:", responseBody);
          throw new Error("Failed to update the item");
        }
      } catch (error) {
        console.error("Error updating the item:", error);
      }
    } else {
      const state = await PostNewItem(newItem, ItemsUrl,addElement);
      if (state) {
        setNewCategory(true);
        setMessage("New Item added successfully!");
        handleShowNotification();
      } else {
        setNewCategory(true);
        setMessage("Error adding new item!");
        handleShowNotification();
      }
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
    handleAddNewItem(FormData, items);
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
