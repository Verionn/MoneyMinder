// GetNumberOfItems.js
import React from "react";
import GetItemsFromList from "../communicationWithBackEnd/GetItemsFromList";

const GetNumberOfItems = ({ listID,operation }) => {
  const apiUrl = `http://localhost:8080/lists/${listID}/items`;

  let { items, loading, error } = GetItemsFromList({ apiUrl });
  
  
  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }
  if(operation === "count") return <div>{items.length}</div>

  else if(operation === "price") return <div>{calculateTotalPrice(items)}</div>
  
};

export default GetNumberOfItems;

const calculateTotalPrice = ( items) => {

  return items.reduce((total, item) => total + item.price * item.amount, 0).toFixed(2);
};