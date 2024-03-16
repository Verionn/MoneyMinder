import { Row, Col } from "react-bootstrap";
import React from "react";
import "./ItemList.css";
import { GetDataFromApi } from "../../utils/functions/getDataFromApi";
import { categoriesUrl } from "../../utils/data/serverInfo";
import { findCategoryNameById } from "../../utils/functions/function";
const ItemRow = ({ item, onSelect, isSelected }) => {
  const { data: CategoryData } = GetDataFromApi({ apiUrl: categoriesUrl });
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
        {item.price * item.amount}
      </Col>
      <Col xs={2} className="Col">
        {CategoryData &&
          findCategoryNameById(CategoryData.categories, item.categoryId)}
      </Col>
      <Col xs={2} className="Col">
        {item.weight}
      </Col>
    </Row>
  );
};

export default ItemRow;
