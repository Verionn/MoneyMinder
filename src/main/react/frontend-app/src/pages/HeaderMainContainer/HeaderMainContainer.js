import React, { useState } from "react";
import "./HeaderMainContainer.css";
import "boxicons";
import Dropdown from "react-bootstrap/Dropdown";
import "bootstrap/dist/css/bootstrap.min.css";
import { useDarkMode,UseViewList } from "../../components/Context/Contexts";
import CreateNewList from "../../components/CreateNewList/createNewList";
import { useLocalStorageState } from "../../components/functions/functions";

const HeaderMainContainer = () => {
  const [showDropdown, setShowDropdown] = useState(false);
  const [showListView, setShowListView] = useLocalStorageState(
    "showListView",
    true
  );
  const [showContentView, setShowContentView] = useLocalStorageState(
    "showContentView",
    false
  );
  const{handleView} = UseViewList();
  const { darkMode } = useDarkMode();
  const handleToggle = (isOpen, event, metadata) => {
    setShowDropdown(isOpen);
  };

  const handleViews = (type) => {
    if (type === "list") {
      setShowListView(true);
      setShowContentView(false);
      handleView(type);
    }
    if (type === "content") {
      setShowListView(false);
      setShowContentView(true);
      handleView(type);
    }
  };

  return (
    <div className="headerMainContainerBody">
      <h3 style={{ color: darkMode ? "#fff" : "" }}>Your Shopping lists</h3>
      <div className="headerRight">
        <CreateNewList></CreateNewList>
        <Dropdown
          className="viewDisplay"
          show={showDropdown}
          onToggle={handleToggle}
        >
          <Dropdown.Toggle variant="success" id="dropdown-basic">
            {showListView ? (
              <div className="listView">
                <box-icon name="list-ul" color="white"></box-icon>
              </div>
            ) : (
              <div className="contentView">
                <box-icon name="checkbox-square" color="white"></box-icon>
              </div>
            )}
            {/*{showContentView ? (
              <div className="contentView">
                <box-icon name="checkbox-square" color="white"></box-icon>
              </div>
            ) : null}*/}
            <p>View</p>
          </Dropdown.Toggle>

          <Dropdown.Menu>
            <Dropdown.Item
              href="#/action-1"
              onClick={() => handleViews("list")}
            >
              <box-icon name="list-ul"></box-icon>
            </Dropdown.Item>

            <Dropdown.Item
              href="#/action-2"
              onClick={() => handleViews("content")}
            >
              <box-icon name="checkbox-square"></box-icon>
            </Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </div>
    </div>
  );
};

export default HeaderMainContainer;
