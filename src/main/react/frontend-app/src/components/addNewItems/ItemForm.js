// ItemForm.jsx

import React from "react";
import { Form } from "react-bootstrap";

const ItemForm = ({ onSubmit, onChange, formData, categories, items }) => {
  const {
    itemName,
    customItemName,
    quantity,
    price,
    category,
    customCategoryName,
    weight,
  } = formData;
  let displayalert = false;
  const isSubmitDisabled =
    !itemName ||
    (itemName === "custom" && !customItemName) ||
    customItemName.length > 50;

  const HandleOnsubmit = () => {
    if (!isSubmitDisabled) displayalert = true;
  };

  return (
    <Form onSubmit={onSubmit}>
      <Form.Group controlId="formItemName" className="formItemName">
        <Form.Label>Item Name</Form.Label>
        <Form.Control
          as="select"
          name="itemName"
          value={itemName}
          onChange={onChange}
          required
        >
          <option value="">Select an item name</option>
          <option value="custom" className="custom">Personal item Name</option>
          {items.map((item) => (
            <option key={item.itemId} value={item.name}>
              {item.name}
            </option>
          ))}
        </Form.Control>
        {itemName === "custom" && (
          <Form.Control
            type="text"
            name="customItemName"
            placeholder="Enter Personal item Name"
            value={customItemName}
            onChange={onChange}
            required
            className="customItemNameMargin"
            minLength={3}
          />
        )}
        {customItemName && customItemName.length > 50 && (
          <div className="text-danger">
            Name cannot be more than 50 characters.
          </div>
        )}
      </Form.Group>

      <div className="smallBoxNumbers customMarginBottom">
        <Form.Group controlId="formQuantity">
          <Form.Label>Quantity</Form.Label>
          <Form.Control
            type="number"
            name="quantity"
            placeholder="Enter quantity"
            value={quantity}
            onChange={onChange}
          />
        </Form.Group>

        <Form.Group controlId="formPrice">
          <Form.Label>Price</Form.Label>
          <Form.Control
            type="number"
            name="price"
            placeholder="Enter price"
            value={price}
            onChange={onChange}
          />
        </Form.Group>
      </div>

      <Form.Group controlId="formCategory" className="formCategory">
        <Form.Label>Category</Form.Label>
        <Form.Control
          as="select"
          name="category"
          value={category}
          onChange={onChange}
        >
          <option value="">Select a category</option>
          <option value="custom" className="custom">Personal Category</option>
          {categories.map((category) => (
            <option key={category.categoryId} value={category.name}>
              {category.name}
            </option>
          ))}
        </Form.Control>
      </Form.Group>
      {category === "custom" && (
        <Form.Control
          type="text"
          name="customCategoryName"
          placeholder="Enter Personal Category name"
          value={customCategoryName}
          onChange={onChange}
          minLength={3}
          required
        />
      )}
      {customCategoryName && customCategoryName.length > 50 && (
        <div className="text-danger">
          Category name cannot be more than 50 characters.
        </div>
      )}

      <Form.Group controlId="formWeight" className="customMarginBottom">
        <Form.Label>Weight</Form.Label>
        <Form.Control
          type="number"
          name="weight"
          placeholder="Enter weight"
          value={weight}
          onChange={onChange}
        />
      </Form.Group>
      {displayalert && (
        <div className="alert alert-success" role="alert">
          You must enter at least
        </div>
      )}
      <button
        type="submit"
        className="AddItemSubmitButton"
        onClick={HandleOnsubmit}
      >
        Submit
      </button>
    </Form>
  );
};

export default ItemForm;
