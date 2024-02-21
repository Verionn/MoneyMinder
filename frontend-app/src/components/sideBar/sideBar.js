import React, { useState } from "react";
import "./sideBar.css";
import { Container, Row } from "react-bootstrap";
import Typography from "../sharedComponents/typography";
import { pageSections } from "../../utils/datas/appInfo";
import { RenderSideBarSection } from "./renderSideBarSection";
import { Styles } from "./styles";
import { useContextElements } from "../../utils/hooks/customHooks";
import { ReactComponent as ChevronLeft } from "../../utils/assets/SVG/bx-chevron-left.svg";
import { ReactComponent as ChevronRight } from "../../utils/assets/SVG/bx-chevron-right.svg";
const SideBar = ({ appInfo, login, Credit }) => {
  const { isDarkMode, updateDarkMode } = useContextElements();
  const [isCollapsed, setIsCollapsed] = useState(false);
  const [isHovered, setIsHovered] = useState(false);

  const handleHover = (state) => {
    setIsHovered(state);
  };
  const handleCollapse = () => {
    setIsCollapsed(!isCollapsed);
    updateDarkMode();
  };

  const styles = Styles({ Collapsed: isCollapsed, darkMode: isDarkMode });

  return (
    <div className="sideBar" style={{ ...styles.root }}>
      <Container style={{ ...styles.container }} className="sideBarContainer">
        <Row className="custom-Row appInfo">
          <img src={appInfo.logo} alt="logo" className="logo" />
          {isCollapsed ? null : (
            <Typography tag="h5" className="appName" style={styles.appInfo}>
              {appInfo.name}
            </Typography>
          )}
        </Row>
        <Row className="custom-row login" style={styles.customRow}>
          {!isCollapsed ? <button className="loginBtn">{login}</button> : null}
        </Row>

        <RenderSideBarSection
          sections={pageSections}
          isCollapsed={isCollapsed}
        />
        <Row className="sectionSeparator">
          <div className="horizontal-Line"></div>
        </Row>
        <Row className="custom-row">
          privacy Policy
          <br /><br />
          Terms of Service{/*Implement later*/}
        </Row>
        <Row className="sectionSeparator">
          <div className="horizontal-Line"></div>
        </Row>
        <Row className="custom-row">{!isCollapsed ? Credit : null}</Row>
      </Container>
      <div className="sideBarChevron">
        <div
          className="sideBarChevronIcon"
          onClick={handleCollapse}
          onMouseEnter={() => handleHover(true)}
          onMouseLeave={() => handleHover(false)}
        >
          {isHovered ? (
            isCollapsed ? (
              <ChevronRight style={{ ...styles.sideBarChevron }} />
            ) : (
              <ChevronLeft style={{ ...styles.sideBarChevron }} />
            )
          ) : (
            <div
              style={{ ...styles.sideBarChevronIcon }}
              className="sideBarVerticaleLine"
            ></div>
          )}
        </div>
      </div>
    </div>
  );
};

export default SideBar;
