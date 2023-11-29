import React from "react";
import GetItemsFromList from "../../../components/communicationWithBackEnd/GetItemsFromList";
import "boxicons";
import "./DisplayItems.css";
import DisplayCategory from "../DisplayCategory/DisplayCategory";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

const GetDatasFromItems = ({ listID, operation, onClose }) => {
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
        <div className="closeItemsList">
          <box-icon name="x" onClick={onClose}></box-icon>
        </div>
        <div className="ItemsHeader">
          <div className="ListName">{listName}</div>
          <div className="itemHeaderRight">
            <box-icon name="search-alt-2"></box-icon>
            <box-icon name="dots-vertical-rounded"></box-icon>
          </div>
        </div>
        <Container>
          <Row className="itemLists">
            <Col className="textCentered"></Col>
            <Col className="itemName">Name</Col>
            <Col className="textCentered">Amount</Col>
            <Col className="textCentered">Price</Col>
            <Col className="textCentered">Category</Col>
            <Col className="textCentered">Weigth</Col>
          </Row>
          </Container>
          <Container fluid className="allItems">
          {items.map((item) => (
            <Row className="itemLists" key={item.itemId}>
              <Col className="textCentered">
                <box-icon name="radio-circle"></box-icon>
              </Col>
              <Col className="itemName">{item.name}</Col>
              <Col className="textCentered">{item.amount}</Col>
              <Col className="textCentered">
                {(item.price * item.amount).toFixed(2)} $
              </Col>
              <Col className="textCentered">
                <DisplayCategory CategoryID={item.categoryId}></DisplayCategory>
              </Col>
              <Col className="textCentered">
                {item.weight > 0 ? item.weight : ""}
              </Col>
            </Row>
          ))}
        </Container>
        <div className="Horizontal-line"></div>
        <div className={"TotalPrice"}>
          <span className="priceTitle">Total price :</span>
          <span className="calculatedPrice">{calculateTotalPrice(items)} $</span>
           </div>
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
