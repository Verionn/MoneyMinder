import React from "react";
import "./mainContainer.css";
import "boxicons";
import HeaderMainContainer from "../HeaderMainContainer/HeaderMainContainer";
import "bootstrap/dist/css/bootstrap.min.css"
import { useDarkMode } from "../../components/DarkModeContext/DarkModeContext";
import ListsDisplay from "./lists/ListsDisplay";

const MainContainer = () => {
  const { darkMode } = useDarkMode();

  return (
    <div
      className="mainContainer"
      style={{ backgroundColor: darkMode ? "#1c1c1d" : "#fff" }}
    >
      <HeaderMainContainer></HeaderMainContainer>

      <div className="bodyContainer">
        <ListsDisplay></ListsDisplay>
      </div>
    </div>
  );
};

export default MainContainer;
