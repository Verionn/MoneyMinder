import { Col, Row } from "react-bootstrap";
import {
  useGetInfosFromItemList,
  useGetInfosFromPurchasedItemsList,
} from "../../utils/hooks/customHooks";

const TotalPriceFooter = ({ listId }) => {
  const { result: TotalPriceItemsList } = useGetInfosFromItemList({
    listID: listId,
    operationType: "price",
  });
  const { result: TotalPricePurchasedItemsList } =
    useGetInfosFromPurchasedItemsList({
      listID: listId,
      operationType: "price",
    });

  let total =
    Number(TotalPriceItemsList) + Number(TotalPricePurchasedItemsList);

  return (
    <div>
      <Row className="TotalPrices">
        <Col className="RowPrices">Unchecked</Col>
        <Col className="RowPrices">checked</Col>
        <Col className="RowPrices RowTotalPrice">Total</Col>
      </Row>
      <Row className="TotalPrices">
        <Col className="RowPrices">{TotalPriceItemsList}</Col>
        <Col className="RowPrices">{TotalPricePurchasedItemsList}</Col>
        <Col className="RowTotalPrice RowPrices">{total}</Col>
      </Row>
    </div>
  );
};

export default TotalPriceFooter;
