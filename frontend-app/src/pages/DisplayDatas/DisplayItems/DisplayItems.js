import React, {  useState,useEffect } from "react";
import {GetItemListData} from "../../../components/communicationWithServer/HandleDataRequest"
import "boxicons";
import "./DisplayItems.css";
import DisplayCategory from "../DisplayCategory/DisplayCategory";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import AddNewItem from "../../../components/addNewItems/addNewItem";
import { deleteItem } from "../../../components/delete/deleteItem";
import { checkItem } from "../../../components/functions/checkItem";
import { UseListArray } from "../../../components/Context/Contexts";

const GetDatasFromItems = ({ listID, operation, onClose }) => {
  const {itemsArray,allItemsArray} = UseListArray();
  const apiUrl = `http://localhost:8080/lists/${listID}/items`;
  const [addItems, setAddItems] = useState(false);
  

  const [checkedItems, setCheckedItems] = useState({});
  const [selectedItems, setSelectedItems] = useState([]);


  // Save state to local storage whenever the addItems or Items state changes
  useEffect(() => {
    localStorage.setItem('addItems', addItems.toString());
  }, [addItems]);

  useEffect(() => {
    //console.log("itemsArray : ", itemsArray);
    console.log("allItemsArray : ", allItemsArray);
  }, [itemsArray,allItemsArray]);

  const handleCheckboxChange = (itemId) => {
    setCheckedItems((prevCheckedItems) => ({
      ...prevCheckedItems,
      [itemId]: !prevCheckedItems[itemId],
    }));
  };

  const deleteAllSelectedItems = () => {
    // Filter out selected items and update state
    const newlySelectedItems = items.filter(
      (item) => checkedItems[item.itemId]
    );
    // Log the selected items to the consoles


    for (let i = 0; i < newlySelectedItems.length; i++) {
      const currentItem = newlySelectedItems[i];
 
      deleteItem(currentItem);
    }
    setSelectedItems((prevSelectedItems) => [
      ...prevSelectedItems,
      ...newlySelectedItems,
    ]);

    // Uncheck all items after processing
    setCheckedItems({});
  };
  const checkAllSelectedItems = () => {
    const newlySelectedItems = items.filter(
      (item) => checkedItems[item.itemId]
    );



    for (let i = 0; i < newlySelectedItems.length; i++) {
      const currentItem = newlySelectedItems[i];
      checkItem(currentItem);
    }
    setSelectedItems((prevSelectedItems) => [
      ...prevSelectedItems,
      ...newlySelectedItems,
    ]);
    setCheckedItems({});
  };

  const handleAddItems = () => {
    setAddItems(!addItems);
  };

const  { items, loading, error } = GetItemListData({ apiUrl });

  if (loading) {
    <p className={"Items"}>
      <box-icon
        name="loader-alt"
        animation="spin"
        color="#002a4e"
        size="lg"
      ></box-icon>
    </p>;
  }

  if (error) {
    return <p className={"Items"}>Error: {"List Not Found"}</p>;
  }
  if (operation === "count") return <div>{items.length}</div>;
  else if (operation === "price")
    return <div>{calculateTotalPrice(items)}</div>;
  let listName = operation;

  if (operation) {
    return (
      <div className="ItemsBox">
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
          {items.length > 0 ? (
            <>
              <Container>
                <Row className="itemListsHeader">
                  <Col className="textCentered"></Col>
                  <Col className="itemName">Name</Col>
                  <Col className="textCentered">Amount</Col>
                  <Col className="textCentered">Price</Col>
                  <Col className="textCentered">Category</Col>
                  <Col className="textCentered">Weigth</Col>
                </Row>
              </Container>
              <span className="uncheckAndCheckList">
                {/*---------------------------------------------------------------*/}

                <Container fluid className="allItems">
                  {items.map((item) => (
                    <Row className="itemLists " key={item.itemId}>
                      <Col className="textCentered">
                        <input
                          className="checkbox"
                          type="checkbox"
                          checked={checkedItems[item.itemId] || false}
                          onChange={() => handleCheckboxChange(item.itemId)}
                        />
                      </Col>
                      <Col className="itemName">{item.name}</Col>
                      <Col className="textCentered">{item.amount}</Col>
                      <Col className="textAlignedRight">
                        {(item.price * item.amount).toFixed(2)} $
                      </Col>
                      <Col className="textCentered">
                        <DisplayCategory
                          CategoryID={item.categoryId}
                        ></DisplayCategory>
                      </Col>
                      <Col className="textCentered">
                        {item.weight > 0 ? item.weight : ""}
                      </Col>
                    </Row>
                  ))}
                </Container>
                {/*---------------------------------------------------------------*/}
                <div className="Horizontal-line"></div>

                {/*-----------------------------------------------------
                <Container fluid className="allItems">
                  {items.map((item) => (
                    <Row className="itemLists " key={item.itemId}>
                      <Col className="textCentered">
                        <input
                          className="checkbox"
                          type="checkbox"
                          checked={checkedItems[item.itemId] || false}
                          onChange={() => handleCheckboxChange(item.itemId)}
                        />
                      </Col>
                      <Col className="itemName">{item.name}</Col>
                      <Col className="textCentered">{item.amount}</Col>
                      <Col className="textAlignedRight">
                        {(item.price * item.amount).toFixed(2)} $
                      </Col>
                      <Col className="textCentered">
                        <DisplayCategory
                          CategoryID={item.categoryId}
                        ></DisplayCategory>
                      </Col>
                      <Col className="textCentered">
                        {item.weight > 0 ? item.weight : ""}
                      </Col>
                    </Row>
                  ))}
                </Container>
               ----------------------------------------------------------*/}
              </span>

              <Container>
                <Row>
                  <Col>
                    <button onClick={checkAllSelectedItems}>
                      Check selected items
                    </button>
                  </Col>
                  <Col>
                    <button onClick={deleteAllSelectedItems}>
                      Delete selected items
                    </button>
                  </Col>
                </Row>
              </Container>
              <Container>
                <Row className="PricesTitle">
                  <Col>Unchecked</Col>
                  <Col>Checked</Col>
                  <Col className="priceTitle">Total</Col>
                  <Col></Col>
                </Row>
                <Row>
                  <Col className="calculatedPriceNotBold">
                    {calculateTotalPrice(items)} $
                  </Col>
                  <Col className="calculatedPriceNotBold">0 $</Col>
                  <Col className="calculatedPrice">
                    {calculateTotalPrice(items)} $
                  </Col>
                  <Col>
                    <box-icon name="dots-vertical"></box-icon>
                  </Col>
                </Row>
              </Container>
            </>
          ) : (
            <div className="ItemsEmpty">
              <div className="Horizontal-line-bold"></div>
              <div>
                <lord-icon
                  src="https://cdn.lordicon.com/zfzufhzk.json"
                  trigger="hover"
                  state="hover-line"
                  style={{ width: "250px", height: "250px" }}
                ></lord-icon>
                <p className="infoAboutEmptyList">What do you need to buy?</p>
                <p className="suggestionText">
                  Start searching products to add them to your list
                </p>
                <button className="addItemsButton" onClick={handleAddItems}>
                  <box-icon name="plus" color="#fff"></box-icon>
                  <span>Add items</span>
                </button>
              </div>
            </div>
          )}
        </div>
        {addItems === false && (
          <div className="addItemsButtonBox">
            <Button
              variant="primary"
              onClick={handleAddItems}
              className="ButtonAddItem"
            >
              <box-icon name="plus" color={"#fff"}></box-icon>
            </Button>
          </div>
        )}
        {addItems === true && (
          <div className="AddItems">
            <AddNewItem
              listID={listID}
              onClick={handleAddItems}
              ItemsUrl={apiUrl}
              items={items}
            />
          </div>
        )}
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
