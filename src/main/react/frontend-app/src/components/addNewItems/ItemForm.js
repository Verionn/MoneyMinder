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

  return (
    <Form onSubmit={onSubmit}>
      <Form.Group controlId="formItemName" className="formItemName">
        <Form.Label>Item Name</Form.Label>
        <Form.Control
          as="select"
          name="itemName"
          value={itemName}
          onChange={onChange}
        >
          <option value="">Select an item name</option>
          {items.map((item) => (
            <option key={item.itemId} value={item.name}>
              {item.name}
            </option>
          ))}
          <option value="custom">Custom</option>
        </Form.Control>
      </Form.Group>
      {itemName === "custom" && (
        <Form.Control
          type="text"
          name="customItemName"
          placeholder="Enter custom item name"
          value={customItemName}
          onChange={onChange}
        />
      )}

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
          {categories.map((category) => (
            <option key={category.categoryId} value={category.name}>
              {category.name}
            </option>
          ))}
          <option value="custom">Custom</option>
        </Form.Control>
      </Form.Group>
      {category === "custom" && (
        <Form.Control
          type="text"
          name="customCategoryName"
          placeholder="Enter custom Category Name"
          value={customCategoryName}
          onChange={onChange}
        />
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

      <button type="submit">Submit</button>
    </Form>
  );
};

export default ItemForm;
