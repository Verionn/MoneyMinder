import React, { useState } from "react";
import Col from "react-bootstrap/Col";
import Nav from "react-bootstrap/Nav";
import Row from "react-bootstrap/Row";
import Tab from "react-bootstrap/Tab";
import "./newMainContainer.css";
import { useDarkMode } from "../../components/DarkModeContext/DarkModeContext";

function NewMainContainer() {
  const { darkMode, toggleDarkMode } = useDarkMode();

  const [activeTab, setActiveTab] = useState("first");
  console.log(activeTab);
  const handleTabClick = (tab) => {
    setActiveTab(tab);
  };

  return (
    <div className="newMaincontainer">
      <Tab.Container
        id="left-tabs-example"
        defaultActiveKey="first"
        className="TabContainer"
      >
        <Row className="MainContainerRows">
          <Col sm={3} className="MainContainerNavBars">
            <Nav variant="pills" className="flex-column MainContainersideBar">
              <a href={"https://github.com/Verionn/MoneyMinder"}>
                <h1 className="logo">MoneyMinder</h1>
              </a>
              <button>
                <p>Log in &gt; </p>
                <p>Access your list from any device</p>
              </button>
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
                      name="cog"
                      color={
                        darkMode
                          ? "#fff"
                          : activeTab === "fourth"
                          ? "#fff"
                          : "#1c1c1c"
                      }
                    ></box-icon>
                    <p
                      className={
                        darkMode
                          ? ""
                          : activeTab === "fourth"
                          ? ""
                          : "NavLinkTitleDark"
                      }
                    >
                      Settings
                    </p>
                  </div>
                </Nav.Link>
              </Nav.Item>

              <div className="horizontal-line"></div>
              <div className="links">
                <box-icon
                  type="logo"
                  size="md"
                  name="github"
                  color="white"
                ></box-icon>
                <box-icon
                  type="logo"
                  size="md"
                  name="facebook-circle"
                  color="white"
                ></box-icon>
              </div>

              <div className="terms">
                <p>Privacy Policy</p>
                <p>Terms of service</p>
                <div className="horizontal-line"></div>

                <p>
                  {" "}
                  <box-icon name="copyright"></box-icon>2023 MoneyMinder{" "}
                  <br></br> All rights reserved
                </p>
              </div>
            </Nav>
          </Col>
          <Col sm={9} className="NavBarsContents">
            <Tab.Content>
              <Tab.Pane eventKey="first">
                <button className="generalLoginButton">
                  <p>Log in &gt; </p>
                  <p>Access your list from any device</p>
                </button>
                <div className="ButtonDarkMode" onClick={toggleDarkMode}>
                  <box-icon
                    type="solid"
                    name={darkMode ? "toggle-right" : "toggle-left"}
                    className={darkMode ? "DarkMode" : "LightMode"}
                    size="lg"
                    color={darkMode ? "#002a4e" : "#1c1c1c"}
                  ></box-icon>
                  <div className={darkMode ? "textDarkMode" : ""}>
                    {darkMode ? "Dark Mode " : "Light Mode"}
                  </div>
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
