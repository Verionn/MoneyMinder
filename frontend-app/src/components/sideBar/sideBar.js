import "./sideBar.css";
import { Container, Row } from "react-bootstrap";
import Typography from "../sharedComponents/typography";
import { pageSections } from "../../utils/datas/appInfo";
import { RenderSideBarSection } from "./renderSideBarSection";
const SideBar = ({ appInfo, login }) => {
  return (
    <Container className="sideBarContainer">
      <Row className="custom-Row appInfo">
        <img src={appInfo.logo} alt="logo" className="logo" />
        <Typography tag="h6" className="appName">
          {appInfo.name}
        </Typography>
      </Row>
      <Row className="custom-row login">
        <button className="loginBtn">{login}</button>
      </Row>

      <RenderSideBarSection sections={pageSections} />
      <Row className="sectionSeparator">
        <div className="horizontal-Line"></div>
      </Row>
      <Row className="custom-Row">xx</Row>
      <Row className="sectionSeparator">
        <div className="horizontal-Line"></div>
      </Row>
    </Container>
  );
};

export default SideBar;
