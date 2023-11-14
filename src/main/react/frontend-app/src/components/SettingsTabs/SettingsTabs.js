import React, { useState } from "react";
import Col from "react-bootstrap/Col";
import Nav from "react-bootstrap/Nav";
import Row from "react-bootstrap/Row";
import Tab from "react-bootstrap/Tab";
import "./SettingsTabs.css";
import { useDarkMode } from "../DarkModeContext/DarkModeContext";
function SettingsTabs() {
  const { darkMode, toggleDarkMode } = useDarkMode();

  const [activeTab, setActiveTab] = useState("first");
  console.log(activeTab);
  const handleTabClick = (tab) => {
    setActiveTab(tab);
  };

  return (
    <Tab.Container
      id="left-tabs-example"
      defaultActiveKey="first"
      className="TabContainer"
    >
      <Row className="TabContainerRow">
        <Col sm={3} className="NavBars">
          <Nav variant="pills" className="flex-column NavBarDisplay">
            <Nav.Item className="NavItem">
              <Nav.Link
                eventKey="first"
                className={
                  darkMode
                    ? `NavLinkDark ${activeTab === "first" ? "active" : ""}`
                    : `NavLink ${activeTab === "first" ? "active" : ""}`
                }
                onClick={() => handleTabClick("first")}
              >
                <div className="NavLinkTitle">
                  <box-icon
                    name="cog"
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
                    General
                  </p>
                </div>
              </Nav.Link>
            </Nav.Item>

            <Nav.Item className="NavItem">
              <Nav.Link
                eventKey="second"
                className={
                  darkMode
                    ? `NavLinkDark ${activeTab === "second" ? "active" : ""}`
                    : `NavLink ${activeTab === "second" ? "active" : ""}`
                }
                onClick={() => handleTabClick("second")}
              >
                <div className="NavLinkTitle">
                  <box-icon
                    name="list-ol"
                    color={
                      darkMode
                        ? "#fff"
                        : activeTab === "second"
                        ? "#fff"
                        : "#1c1c1c"
                    }
                  ></box-icon>
                  <p
                    className={ darkMode ?"":activeTab === "second" ? "" : "NavLinkTitleDark"}
                  >
                    List
                  </p>
                </div>
              </Nav.Link>
            </Nav.Item>

            <Nav.Item className="NavItem">
              <Nav.Link
                eventKey="third"
                className={
                  darkMode
                    ? `NavLinkDark ${activeTab === "third" ? "active" : ""}`
                    : `NavLink ${activeTab === "third" ? "active" : ""}`
                }
                onClick={() => handleTabClick("third")}
              >
                <div className="NavLinkTitle">
                  <box-icon
                    name="user-account"
                    type="solid"
                    color={
                      darkMode
                        ? "#fff"
                        : activeTab === "third"
                        ? "#fff"
                        : "#1c1c1c"
                    }
                  ></box-icon>
                  <p
                    className={darkMode ?"":activeTab === "third" ? "" : "NavLinkTitleDark"}
                  >
                    Account
                  </p>
                </div>
              </Nav.Link>
            </Nav.Item>

            <Nav.Item className="NavItem">
              <Nav.Link
                eventKey="fourth"
                className={
                  darkMode
                    ? `NavLinkDark ${activeTab === "fourth" ? "active" : ""}`
                    : `NavLink ${activeTab === "fourth" ? "active" : ""}`
                }
                onClick={() => handleTabClick("fourth")}
              >
                <div className="NavLinkTitle">
                  <box-icon
                    name="support"
                    color={
                      darkMode
                        ? "#fff"
                        : activeTab === "fourth"
                        ? "#fff"
                        : "#1c1c1c"
                    }
                  ></box-icon>
                  <p
                    className={darkMode ?"":activeTab === "fourth" ? "" : "NavLinkTitleDark"}
                  >
                    Support
                  </p>
                </div>
              </Nav.Link>
            </Nav.Item>
          </Nav>
        </Col>
        <Col sm={9} className="NavBarsContents">
          <Tab.Content>
            <Tab.Pane eventKey="first">
              <div className="ButtonDarkMode" onClick={toggleDarkMode}>
                <box-icon
                  type="solid"
                  name={darkMode ? "toggle-left" : "toggle-right"}
                  className={darkMode ? "DarkMode" : "LightMode"}
                  size="lg"
                ></box-icon>
                <div>{darkMode ? "Dark Mode " : "Light Mode"}</div>
              </div>
            </Tab.Pane>
            <Tab.Pane eventKey="second">Second tab content</Tab.Pane>
            <Tab.Pane eventKey="third">third tab content</Tab.Pane>
            <Tab.Pane eventKey="fourth">fourth tab content</Tab.Pane>
          </Tab.Content>
        </Col>
      </Row>
    </Tab.Container>
  );
}

export default SettingsTabs;
