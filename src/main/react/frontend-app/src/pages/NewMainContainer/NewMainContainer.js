import React from "react";
import Col from "react-bootstrap/Col";
import Nav from "react-bootstrap/Nav";
import Row from "react-bootstrap/Row";
import Tab from "react-bootstrap/Tab";
import "./newMainContainer.css";
import { useDarkMode } from "../../components/Context/Contexts";
import Logo from "../../ressources/logo.png";
import HeaderMainContainer from "../HeaderMainContainer/HeaderMainContainer";
import SettingsCanvas from "../SettingsCanvas/SettingsCanvas";
import DisplayAllLists from "../DisplayDatas/DisplayAllLists/DisplayAllLists";
import { useLocalStorageState } from "../../components/functions/functions";
function NewMainContainer() {
  const { darkMode } = useDarkMode();
  const [ItemsID, setItemsID] = useLocalStorageState("ItemsID", -1);
  const [activeTab, setActiveTab] = useLocalStorageState("activeTab", "first");
  

  const handleTabClick = (tab) => {
    setActiveTab(tab);
   
  };
  const handleListClick = (listId) => {
    setItemsID(listId);
  };
  const handleCloseItemsList = () => {
    setItemsID(-1);
  };

  const determineColorIcon = (DarkMode, activeTab, tab) => {
    if (DarkMode) {
      if (activeTab === tab) return "#fff";
      else return "#fff";
    } else return "#fff";
  };

  const determineClassNameTabs = (activeTab, currentTab) => {
    if (darkMode) {
      return `NavLinks DarkLink ${activeTab === currentTab ? "active" : ""}`;
    } else {
      return `NavLinks ${activeTab === currentTab ? "active" : ""}`;
    }
  };
  return (
    <div className={darkMode ? "newMaincontainer dark" : "newMaincontainer"}>
      <Tab.Container
        id="left-tabs-example"
        defaultActiveKey={activeTab}
        className="TabContainer"
      >
        <Row className="MainContainerRows">
          <Col
            sm={3}
            className={
              darkMode
                ? "MainContainerNavBars DarkSideBar"
                : "MainContainerNavBars"
            }
          >
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
                <span>Access your list from any device</span>
              </button>

              <Nav.Item className="MainContainerNavItem">
                <Nav.Link
                  eventKey="first"
                  className={determineClassNameTabs(activeTab, "first")}
                  onClick={() => handleTabClick("first")}
                >
                  <div className="NavLinkTitle">
                    <box-icon
                      type="solid"
                      name="basket"
                      color={determineColorIcon(darkMode, activeTab, "first")}
                    ></box-icon>
                    <span
                      className={
                        darkMode
                          ? ""
                          : activeTab === "first"
                          ? ""
                          : "NavLinkTitleDark"
                      }
                    >
                      Shopping lists
                    </span>
                  </div>
                </Nav.Link>
              </Nav.Item>

              <Nav.Item className="MainContainerNavItem">
                <Nav.Link
                  eventKey="second"
                  className={determineClassNameTabs(activeTab, "second")}
                  onClick={() => handleTabClick("second")}
                >
                  <div className="NavLinkTitle">
                    <box-icon
                      type="solid"
                      name="trash"
                      color={determineColorIcon(darkMode, activeTab, "second")}
                    ></box-icon>
                    <span
                      className={
                        darkMode
                          ? ""
                          : activeTab === "second"
                          ? ""
                          : "NavLinkTitleDark"
                      }
                    >
                      Trash
                    </span>
                  </div>
                </Nav.Link>
              </Nav.Item>

              <Nav.Item className="MainContainerNavItem">
                <Nav.Link
                  eventKey="third"
                  className={determineClassNameTabs(activeTab, "third")}
                  onClick={() => handleTabClick("third")}
                >
                  <div className="NavLinkTitle">
                    <box-icon
                      name="question-mark"
                      color={determineColorIcon(darkMode, activeTab, "third")}
                    ></box-icon>
                    <span
                      className={
                        darkMode
                          ? ""
                          : activeTab === "third"
                          ? ""
                          : "NavLinkTitleDark"
                      }
                    >
                      help
                    </span>
                  </div>
                </Nav.Link>
              </Nav.Item>

              <SettingsCanvas
                name={
                  <p className={darkMode ? "NavLinkTitleDark" : ""}>Settings</p>
                }
                placement="end"
                content={<box-icon name="cog" color={"#fff"}></box-icon>}
              ></SettingsCanvas>
              <div
                className={
                  darkMode ? "horizontal-line-Darkmode" : "horizontal-line"
                }
              ></div>
              <div className="links">
                <box-icon
                  type="logo"
                  size="md"
                  name="github"
                  color="#fff"
                ></box-icon>
              </div>

              <div className="termsAndConditions">
                <p>Privacy Policy</p>
                <p>Terms of service</p>
                <div
                  className={
                    darkMode ? "horizontal-line-Darkmode" : "horizontal-line"
                  }
                ></div>

                <p className="copyRights">
                  {" "}
                  <box-icon name="copyright" color="#fff"></box-icon>2023
                  MoneyMinder <br></br> All rights reserved
                </p>
              </div>
            </Nav>
          </Col>
          <Col sm={9} className="mainContainerNavBarsContents">
            <Tab.Content>
              <Tab.Pane eventKey="first">
                {ItemsID === -1 ? <HeaderMainContainer /> : null}
                <div className="mainContainerBody">
                  <DisplayAllLists
                    onClickList={handleListClick}
                    onCloseList={handleCloseItemsList}
                    ItemsID={ItemsID}
                  ></DisplayAllLists>
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
