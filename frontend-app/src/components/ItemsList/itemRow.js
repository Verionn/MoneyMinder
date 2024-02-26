import { Row, Col } from "react-bootstrap";
import React from 'react';
import "./ItemList.css";
const ItemRow = ({ item, onSelect, isSelected }) => {
  return (
    <Row key={`purchasedItem-${item.itemId}`} className="displayItems">
      <Col xs={2} className="Col1">
        <input
          type="checkbox"
          checked={isSelected}
          onChange={(e) => onSelect(item.itemId, e.target.checked)}
        />
      </Col>
      <Col xs={2} className="Col">
        {item.name}
      </Col>
      <Col xs={2} className="Col">
        {item.amount}
      </Col>
      <Col xs={2} className="Col">
        {item.price*item.amount}
      </Col>
      <Col xs={2} className="Col">
        {item.categoryId}
      </Col>
      <Col xs={2} className="Col">
        {item.weight}
      </Col>
    </Row>
  );
};

export default ItemRow;
