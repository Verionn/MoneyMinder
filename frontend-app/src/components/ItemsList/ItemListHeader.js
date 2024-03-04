import { Row, Col } from "react-bootstrap";
import SearchBox from "../sharedComponents/searchBox/searchBox";
import { GetInfosFromList } from "../../utils/functions/function";

const ItemListHeader = ({ listId, handleCloseItemLists }) => {
  return (
    <Row className="itemsFirstHeader">
      <Col className="Title">
        <GetInfosFromList listID={listId} operationType="name" />
      </Col>
      <Col>
        <SearchBox />
      </Col>
      <Col className="closeIcon" onClick={handleCloseItemLists}>
        &times;
      </Col>
    </Row>
  );
};

export default ItemListHeader;
