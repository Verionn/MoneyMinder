import React from "react";
import GetItemsFromList from "../../../components/communicationWithBackEnd/GetItemsFromList";
import "boxicons";
import "./DisplayItems.css";

const GetDatasFromItems = ({ listID, operation,onClose }) => {
  const apiUrl = `http://localhost:8080/lists/${listID}/items`;

  let { items, loading, error } = GetItemsFromList({ apiUrl });
  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }
  if (operation === "count") return <div>{items.length}</div>;
  else if (operation === "price")
    return <div>{calculateTotalPrice(items)}</div>;
  let listName = operation;

  if (operation) {
    return (
      <div className="Items">
        <div className="closeItemsList" ><box-icon name='x'onClick={onClose} ></box-icon></div>
        <div className="ItemsHeader">
          <div className="ListName">{listName}</div>
          <div className="itemHeaderRight">
            <box-icon name="search-alt-2"></box-icon>
            <box-icon name="dots-vertical-rounded"></box-icon>
          </div>
        </div>
        {items.map((item) => (
          <div className="itemLists" key={item.itemId}>
            <div className="itemName">
              <div>
                <box-icon name="radio-circle"></box-icon>
              </div>
              <div>{item.name}</div>
            </div>
            <div className="itemBody">
              <div className="itemAmount">{item.amount}</div>
              <div className="itemPrice">{(item.price * item.amount).toFixed(2)}$</div>
              <div className="itemCategory">{item.categoryId}</div>
            </div>
          </div>
        ))}
      </div>
    );
  }
};

export default GetDatasFromItems;

const calculateTotalPrice = (items) => {
  return items
    .reduce((total, item) => total + item.price * item.amount, 0)
    .toFixed(2);
};

