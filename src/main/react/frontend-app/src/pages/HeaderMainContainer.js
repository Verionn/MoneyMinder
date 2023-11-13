import React, { useState } from "react";
import "../css/mainContainer.css";
import "boxicons";
import Dropdown from "react-bootstrap/Dropdown";
import "bootstrap/dist/css/bootstrap.min.css";
import Test from "./testjs";

const HeaderMainContainer = () => {
  const [showDropdown, setShowDropdown] = useState(false);

  const handleToggle = (isOpen, event, metadata) => {
    setShowDropdown(isOpen);
  };

  return (
    <div className="header">
      <h3>Your Shopping lists</h3>
      <div className="headerRight">
        <button className="createListButton">Create a new List</button>

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
