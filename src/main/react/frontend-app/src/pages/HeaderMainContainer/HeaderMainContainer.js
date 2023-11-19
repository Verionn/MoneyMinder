import React, { useState } from "react";
import "./HeaderMainContainer.css"
import "boxicons";
import Dropdown from "react-bootstrap/Dropdown";
import "bootstrap/dist/css/bootstrap.min.css";
import { useDarkMode } from "../../components/DarkModeContext/DarkModeContext";
import CreateNewList from "../../components/communicationWithBackEnd/createNewList";


const HeaderMainContainer = () => {
  const [showDropdown, setShowDropdown] = useState(false);
  const { darkMode } = useDarkMode();
  const handleToggle = (isOpen, event, metadata) => {
    setShowDropdown(isOpen);
  };

  return (
    <div className="header">
      <h3 style={{color : darkMode ? "#fff" : ""}}>Your Shopping lists</h3>
      <div className="headerRight">
        <button className="createListButton"><CreateNewList></CreateNewList></button>

        <Dropdown
          className="viewDisplay"
          show={showDropdown}
          onToggle={handleToggle}
        >
          <Dropdown.Toggle variant="success" id="dropdown-basic">
            <div className="listView">
              <box-icon name="list-ul" color="white"></box-icon>
            </div>
            <div className="contentView">
              <box-icon name="checkbox-square" color="white"></box-icon>
            </div>

            <p>View</p>
          </Dropdown.Toggle>

          <Dropdown.Menu>
            <Dropdown.Item href="#/action-1">
              <box-icon name="list-ul"></box-icon>
            </Dropdown.Item>

            <Dropdown.Item href="#/action-2">
              <box-icon name="checkbox-square"></box-icon>
            </Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </div>
    </div>
  );
};

export default HeaderMainContainer;
