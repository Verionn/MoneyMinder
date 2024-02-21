import './sideBar.css';
import { CustomIcon } from '../sharedComponents/icons/icons';
import Typography from "../sharedComponents/typography";
import { Row, Col } from "react-bootstrap";
export const RenderSideBarSection = ({ sections }) => {
  return sections.map((section, index) => (
    <Row className="custom-row" key={`sidebar-${section.id}`}>
      <button className="sideBarBtn">
        <Col>
        <CustomIcon iconName={section.icon} size='5px' />
        </Col>
        <Col>
          <Typography>{section.title}</Typography>
        </Col>
      </button>
    </Row>
  ));
};
