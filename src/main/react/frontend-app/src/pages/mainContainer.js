import React, { useState } from "react";
import "../css/mainContainer.css";
import "boxicons";
import Dropdown from "react-bootstrap/Dropdown";
import HeaderMainContainer from "./HeaderMainContainer";
import "bootstrap/dist/css/bootstrap.min.css";
import Test from "./testjs";

const MainContainer = () => {
  const [showDropdown, setShowDropdown] = useState(false);

  const handleToggle = (isOpen, event, metadata) => {
    setShowDropdown(isOpen);
  };

  return (
    <div className="mainContainer">
      <HeaderMainContainer></HeaderMainContainer>
      

      <div className="bodyContainer">
        <Test></Test>
      </div>
    </div>
  );
};

export default MainContainer;
