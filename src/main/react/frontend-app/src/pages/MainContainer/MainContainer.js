import React from "react";
import "./mainContainer.css";
import "boxicons";

import HeaderMainContainer from "../HeaderMainContainer/HeaderMainContainer";
import "bootstrap/dist/css/bootstrap.min.css";
import Test from "../testjs";
import { useDarkMode } from "../../components/DarkModeContext/DarkModeContext";

const MainContainer = () => {
  const { darkMode } = useDarkMode();

  return (
    <div
      className="mainContainer"
      style={{ backgroundColor: darkMode ? "#1c1c1d" : "#fff" }}
    >
      <HeaderMainContainer></HeaderMainContainer>

      <div className="bodyContainer">
        <Test></Test>
      </div>
    </div>
  );
};

export default MainContainer;
