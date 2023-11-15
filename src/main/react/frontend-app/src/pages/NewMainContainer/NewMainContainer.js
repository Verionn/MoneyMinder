import React, { useState } from "react";
import Col from "react-bootstrap/Col";
import Nav from "react-bootstrap/Nav";
import Row from "react-bootstrap/Row";
import Tab from "react-bootstrap/Tab";
import "./newMainContainer.css";
import { useDarkMode } from "../../components/DarkModeContext/DarkModeContext";
import Logo from "../../ressources/logo.png";
import HeaderMainContainer from "../HeaderMainContainer/HeaderMainContainer";
import SettingsCanvas from "../SettingsCanvas/SettingsCanvas";
import ListsDisplay from "./lists/ListsDisplay";
function NewMainContainer() {
  const { darkMode, toggleDarkMode } = useDarkMode();

  const [activeTab, setActiveTab] = useState("first");
  console.log(activeTab);
  const handleTabClick = (tab) => {
    setActiveTab(tab);
  };

  return (
    <div className={darkMode ? "newMaincontainer dark" : "newMaincontainer"}>
      <Tab.Container
        id="left-tabs-example"
        defaultActiveKey="first"
        className="TabContainer"
      >
        <Row className="MainContainerRows">
          <Col sm={3} className="MainContainerNavBars">
            <Nav variant="pills" className="flex-column MainContainersideBar">
              <a
                href={"https://github.com/Verionn/MoneyMinder"}
                className="brandLink"
              >
                <img
                  src={Logo}
                  alt="MoneyMinder Logo"
                  className="brandLogo"
                ></img>
                <h1 className="brandName">MoneyMinder</h1>
              </a>

              <button className="loginButton">
                <p>Log in &gt; </p>
                <p>Access your list from any device</p>
              </button>

              <Nav.Item className="MainContainerNavItem">
                <Nav.Link
                  eventKey="first"
                  className={
                    darkMode
                      ? `NavLinks DarkLink ${
                          activeTab === "first" ? "active" : ""
                        }`
                      : `NavLinks ${activeTab === "first" ? "active" : ""}`
                  }
                  onClick={() => handleTabClick("first")}
                >
                  <div className="NavLinkTitle">
                    <box-icon
                      type="solid"
                      name="basket"
                      color={
                        darkMode
                          ? "#fff"
                          : activeTab === "first"
                          ? "#fff"
                          : "#1c1c1c"
                      }
                    ></box-icon>
                    <p
                      className={
                        darkMode
                          ? ""
                          : activeTab === "first"
                          ? ""
                          : "NavLinkTitleDark"
                      }
                    >
                      Shopping lists
                    </p>
                  </div>
                </Nav.Link>
              </Nav.Item>

              <Nav.Item className="MainContainerNavItem">
                <Nav.Link
                  eventKey="second"
                  className={
                    darkMode
                      ? `NavLinks DarkLink ${
                          activeTab === "second" ? "active" : ""
                        }`
                      : `NavLinks ${activeTab === "second" ? "active" : ""}`
                  }
                  onClick={() => handleTabClick("second")}
                >
                  <div className="NavLinkTitle">
                    <box-icon
                      type="solid"
                      name="trash"
                      color={
                        darkMode
                          ? "#fff"
                          : activeTab === "second"
                          ? "#fff"
                          : "#1c1c1c"
                      }
                    ></box-icon>
                    <p
                      className={
                        darkMode
                          ? ""
                          : activeTab === "second"
                          ? ""
                          : "NavLinkTitleDark"
                      }
                    >
                      Trash
                    </p>
                  </div>
                </Nav.Link>
              </Nav.Item>

              <Nav.Item className="MainContainerNavItem">
                <Nav.Link
                  eventKey="third"
                  className={
                    darkMode
                      ? `NavLinks DarkLink ${
                          activeTab === "third" ? "active" : ""
                        }`
                      : `NavLinks ${activeTab === "third" ? "active" : ""}`
                  }
                  onClick={() => handleTabClick("third")}
                >
                  <div className="NavLinkTitle">
                    <box-icon
                      name="question-mark"
                      color={
                        darkMode
                          ? "#fff"
                          : activeTab === "third"
                          ? "#fff"
                          : "#1c1c1c"
                      }
                    ></box-icon>
                    <p
                      className={
                        darkMode
                          ? ""
                          : activeTab === "third"
                          ? ""
                          : "NavLinkTitleDark"
                      }
                    >
                      help
                    </p>
                  </div>
                </Nav.Link>
              </Nav.Item>

              <SettingsCanvas
                name={
                  <p className={darkMode ? "NavLinkTitleDark" : ""}>Settings</p>
                }
                placement="end"
                content={
                  <box-icon
                    name="cog"
                    color={darkMode ? "#fff" : "#1c1c1c"}
                  ></box-icon>
                }
              ></SettingsCanvas>
              <div className="horizontal-line"></div>
              <div className="links">
                <box-icon
                  type="logo"
                  size="md"
                  name="github"
                  color="black"
                ></box-icon>
              </div>

              <div className="termsAndConditions">
                <p>Privacy Policy</p>
                <p>Terms of service</p>
                <div className="horizontal-line"></div>

                <p className="copyRights">
                  {" "}
                  <box-icon name="copyright"></box-icon>2023 MoneyMinder{" "}
                  <br></br> All rights reserved
                </p>
              </div>
            </Nav>
          </Col>
          <Col sm={9} className="mainContainerNavBarsContents">
            <Tab.Content>
              <Tab.Pane eventKey="first">
                <HeaderMainContainer></HeaderMainContainer>
                <div className="mainContainerBody">
                  <ListsDisplay></ListsDisplay>
                </div>
              </Tab.Pane>
              <Tab.Pane eventKey="second">Second tab content</Tab.Pane>
              <Tab.Pane eventKey="third">third tab content</Tab.Pane>
              <Tab.Pane eventKey="fourth">fourth tab content</Tab.Pane>
            </Tab.Content>
          </Col>
        </Row>
      </Tab.Container>
    </div>
  );
}

export default NewMainContainer;
