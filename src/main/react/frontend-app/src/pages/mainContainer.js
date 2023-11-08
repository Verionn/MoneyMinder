import React from "react";
import "../css/mainContainer.css";
import "boxicons";
import Dropdown from "react-bootstrap/Dropdown";

const mainContainer = () => {
  return (
    <div className="mainContainer">
      <div className="header">
        <h3>Your Shopping lists</h3>
        <div className="headerLeft">
          <button className="createListButton">Create a new List</button>
          <Dropdown>
            <Dropdown.Toggle variant="success" id="dropdown-basic">
              <box-icon name="list-ul"></box-icon>
              <box-icon name="checkbox-square"></box-icon>
              <p>view</p>
              <box-icon name="chevron-down" type="solid"></box-icon>
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

      <div className="bodyContainer"></div>
    </div>
  );
};



export default mainContainer;
