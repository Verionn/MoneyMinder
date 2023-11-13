import React, { useState } from "react";
import "../css/mainContainer.css";
import "boxicons";
import Dropdown from "react-bootstrap/Dropdown";
import HeaderMainContainer from "./HeaderMainContainer";
import "bootstrap/dist/css/bootstrap.min.css";
import Test from "./testjs";
import { useDarkMode } from "./components/DarkModeContext";

const MainContainer = () => {
  const [showDropdown, setShowDropdown] = useState(false);
  const { darkMode } = useDarkMode();

  const handleToggle = (isOpen, event, metadata) => {
    setShowDropdown(isOpen);
  };

  return (
    <div className="mainContainer"  
    style={{ backgroundColor: darkMode ? '#333' : '#fff'}}>
      <HeaderMainContainer></HeaderMainContainer>
      

      <div className="bodyContainer">
        <Test></Test>
      </div>
    </div>
  );
};

export default MainContainer;
