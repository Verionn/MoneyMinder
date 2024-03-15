import { useContextElements } from "../../utils/hooks/customHooks";
import { Styles } from "./styles";
import { useEffect, useState } from "react";
import { GetDataFromApi } from "../../utils/functions/getDataFromApi";
import { endpoint } from "../../utils/data/serverInfo";
import { Container, Row, Col } from "react-bootstrap";
import "./ItemList.css";
import TotalPriceFooter from "./TotalPriceFooter";
import { useNavigate } from "react-router-dom";
import ItemListHeader from "./ItemListHeader";
import ItemRow from "./itemRow";
import { CustomModal } from "../sharedComponents/customModal/customModal";

import {
  DisplaySelectedItems,
  initiateItemsDeletion,
  deleteSelectedItems,
  initiateItemsCheck,
  checkAndProcessPurchasedItems,
} from "./ItemListHelper";
const ItemBox = ({ listId }) => {
  const {
    windowWidth,
    isDarkMode,
    itemsArray,
    purchasedItemsArray,
    handleDeleteItemsInItemsArray,
    updateItemsArray,
    handleAddPurchasedItem,
    updatePurchasedItemsArray,
  } = useContextElements();
  const styles = Styles({ darkMode: isDarkMode, windowWidth });
  const apiURL = `${endpoint}/lists/${listId}/items`;
  const apiUlrPurchasedItems = `${endpoint}/purchased-items/lists/${listId}`;
  const { data, loading, error } = GetDataFromApi({ apiUrl: apiURL });
  const {
    data: purchasedItems,
    loading: loadingPurchasedItems,
    error: errorPurchasedItems,
  } = GetDataFromApi({ apiUrl: apiUlrPurchasedItems });

  const navigation = useNavigate();
  useEffect(() => {}, [windowWidth, isDarkMode]);
  const handleCloseItemLists = () => {
    navigation("/shopping-list");
  };
  const [isDeletting, setIsDeletting] = useState(-1);
  const [isChecking, setIsChecking] = useState(-1);
  const [selectedItems, setSelectedItems] = useState({
    items: new Set(),
    purchasedItems: new Set(),
  });
  const handleSelect = (itemId, isSelected, isPurchased = false) => {
    setSelectedItems((prevSelectedItems) => {
      const newSelectedItems = {
        items: new Set(prevSelectedItems.items),
        purchasedItems: new Set(prevSelectedItems.purchasedItems),
      };

      const key = isPurchased ? "purchasedItems" : "items";

      if (isSelected) {
        newSelectedItems[key].add(itemId);
      } else {
        newSelectedItems[key].delete(itemId);
      }

      return newSelectedItems;
    });
  };
  const isItemSelected = (itemId, isPurchased = false) => {
    const key = isPurchased ? "purchasedItems" : "items";
    return selectedItems[key].has(itemId);
  };

  useEffect(() => {
    if (data?.items && !loading) {
      updateItemsArray(data);
    }
    if (purchasedItems?.purchasedItems && !loadingPurchasedItems) {
      updatePurchasedItemsArray(purchasedItems);
    }
  }, [
    data,
    loading,
    updateItemsArray,
    purchasedItems,
    loadingPurchasedItems,
    updatePurchasedItemsArray,
  ]);
  useEffect(() => {
    console.log("itemsArray", itemsArray);
  }, [itemsArray, purchasedItemsArray]);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;
  if (loadingPurchasedItems) return <div>Loading...</div>;
  if (errorPurchasedItems)
    return <div>Error: {errorPurchasedItems.message}</div>;
  return (
    <div className="ItemBox" style={{ ...styles.ItemBox }}>
      <Container fluid className="Items">
        <ItemListHeader
          listId={listId}
          handleCloseItemLists={handleCloseItemLists}
        />

        <Row className="displayItems HeaderRow">
          <Col xs={2} className="Col1">
            <input type="checkbox" />
          </Col>
          <Col xs={2} className="Col">
            Name
          </Col>
          <Col xs={2} className="Col">
            Quantity
          </Col>
          <Col xs={2} className="Col">
            Price
          </Col>
          <Col xs={2} className="Col">
            Category
          </Col>
          <Col xs={2} className="Col">
            Weight
          </Col>
        </Row>
        <div className="allItems">
          {itemsArray.items &&
            itemsArray.items.map(
              (item, index) =>
                item.listId === Number(listId) && (
                  <ItemRow
                    key={`Item-${item.itemId}`}
                    item={item}
                    onSelect={(itemId, isSelected) =>
                      handleSelect(itemId, isSelected, false)
                    }
                    isSelected={isItemSelected(item.itemId, false)}
                  />
                )
            )}
          <div className="PurchasedItemsTitle">Purchased Items</div>

          {purchasedItemsArray.purchasedItems &&
            purchasedItemsArray.purchasedItems.map((item, index) => (
              <ItemRow
                key={`purchasedItem-${item.itemId}`}
                item={item}
                onSelect={(itemId, isSelected) =>
                  handleSelect(itemId, isSelected, true)
                }
                isSelected={isItemSelected(item.itemId, true)}
              />
            ))}
        </div>
        <Row className="ButtonsItems">
          <Col xs={6} className="">
            <button
              className="ButtonItem checkButton"
              onClick={() => initiateItemsCheck(setIsChecking, selectedItems)}
            >
              Check selected Items
            </button>
          </Col>
          <Col xs={6} className="">
            <button
              className="ButtonItem deleteButton"
              onClick={() =>
                initiateItemsDeletion(setIsDeletting, selectedItems)
              }
            >
              Delete selected Items
            </button>
          </Col>
        </Row>
        <TotalPriceFooter listId={listId} />
      </Container>
      {isDeletting === 1 && (
        <CustomModal
          show={true}
          onClose={() => setIsDeletting(-1)}
          ModalTitle={`Do you want to delete selected items ?`}
          confirmButtonColor="red"
          ModalConfirmationButton="Delete"
          functionTOCall={() =>
            deleteSelectedItems(
              listId,
              selectedItems,
              data,
              setSelectedItems,
              setIsDeletting,
              handleDeleteItemsInItemsArray
            )
          }
          canConfirm={true}
        >
          {DisplaySelectedItems(selectedItems, data)}
        </CustomModal>
      )}
      {isChecking === 1 && (
        <CustomModal
          show={true}
          onClose={() => setIsChecking(-1)}
          ModalTitle={`Do you want to selected selected items ?`}
          confirmButtonColor="var(--secondary-color)"
          ModalConfirmationButton="check"
          functionTOCall={() =>
            checkAndProcessPurchasedItems(
              setIsChecking,
              selectedItems,
              setSelectedItems,
              data,
              listId,
              handleAddPurchasedItem,
              handleDeleteItemsInItemsArray
            )
          }
          canConfirm={true}
        >
          {DisplaySelectedItems(selectedItems, data)}
        </CustomModal>
      )}
    </div>
  );
};

export default ItemBox;
