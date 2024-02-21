import './sideBar.css';

import Typography from "../sharedComponents/typography";
import { Row, Col } from "react-bootstrap";
export const RenderSideBarSection = ({ sections }) => {
  return sections.map((section, index) => (
    <Row className="custom-row" key={`sidebar-${section.id}`}>
      <button className="sideBarBtn">
        <Col>{section.icon}</Col>
        <Col>
          <Typography>{section.title}</Typography>
        </Col>
      </button>
    </Row>
  ));
};
