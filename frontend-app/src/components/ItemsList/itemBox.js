import { useContextElements } from "../../utils/hooks/customHooks";
import { Styles } from "./styles";
import { useEffect, useState } from "react";
import { GetDatasFromApi } from "../../utils/functions/getDatasFromApi";
import { endpoint } from "../../utils/datas/serverInfo";
import { Container, Row, Col } from "react-bootstrap";
import "./ItemList.css";
import TotalPriceFooter from "./TotalPriceFooter";
import { useNavigate } from "react-router-dom";
import ItemListHeader from "./ItemListHeader";
import ItemRow from "./itemRow";
import { CustomModal } from "../sharedComponents/customModal/customModal";

import { GetListOfSelectedItems,handleDeleteItems,handleAllDelete } from "./ItemListHelper";
const ItemBox = ({ listId }) => {
  const { windowWidth, isDarkMode } = useContextElements();
  const styles = Styles({ darkMode: isDarkMode, windowWidth });
  const apiURL = `${endpoint}/lists/${listId}/items`;
  const apiUlrPurchasedItems = `${endpoint}/purchasedItems/lists/${listId}`;
  const { data, loading, error } = GetDatasFromApi({ apiUrl: apiURL });
  const {
    data: purchasedItems,
    loading: loadingPurchasedItems,
    error: errorPurchasedItems,
  } = GetDatasFromApi({ apiUrl: apiUlrPurchasedItems });
  const navigation = useNavigate();
  useEffect(() => {}, [windowWidth, isDarkMode]);
  const handleCloseItemLists = () => {
    navigation("/shopping-list");
  };
  const [isDeletting, setIsDeletting] = useState(-1);

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
          {data.items &&
            data.items.map((item, index) => (
              <ItemRow
                key={`Item-${item.itemId}`}
                item={item}
                onSelect={(itemId, isSelected) =>
                  handleSelect(itemId, isSelected, false)
                }
                isSelected={isItemSelected(item.itemId, false)}
              />
            ))}

          {purchasedItems.items &&
            purchasedItems.items.map((item, index) => (
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
            <button className="ButtonItem checkButton">
              Check selected Items
            </button>
          </Col>
          <Col xs={6} className="">
            <button
              className="ButtonItem deleteButton"
              onClick={()=>handleDeleteItems(setIsDeletting,selectedItems)}
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
          functionTOCall={()=>handleAllDelete(listId,selectedItems,data,setSelectedItems,setIsDeletting)}
          canConfirm={true}
        >
          {GetListOfSelectedItems(selectedItems, data)}
        </CustomModal>
      )}
    </div>
  );
};

export default ItemBox;
