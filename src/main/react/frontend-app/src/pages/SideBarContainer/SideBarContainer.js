import React from "react";
import "./sideBarContainer.css";
import "boxicons";
import OffCanvasExample from "../../components/SettingsCanvas/SettingsCanvas";
import { useDarkMode } from "../../components/DarkModeContext/DarkModeContext";

const SideBarContainer = () => {
  const { darkMode } = useDarkMode();

  return (
    <div className="sideBar">
      <a href={"https://github.com/Verionn/MoneyMinder"}>
        <h1 className="logo">MoneyMinder</h1>
      </a>
      <button>
        <p>Log in &gt; </p>
        <p>Access your list from any device</p>
      </button>
      <ul>
        <li className="navigationTags">
          <box-icon
            type="solid"
            name="basket"
            color="#865400"
            className="boxIconNavigationTags"
          ></box-icon>
          <h3>Shopping lists</h3>
        </li>

        <li className="navigationTags">
          <box-icon type="solid" name="trash" color="#865400"></box-icon>
          <h3>Trash</h3>
        </li>

        <li className="navigationTags">
          <box-icon name="question-mark" color="#865400"></box-icon>
          <h3>Help</h3>
        </li>

        <li className="navigationTags moreMarginAndPadding">
          
          <OffCanvasExample className="navigationTags settingStyle"
            name={<h3>Settings</h3>}
            placement="end"
            content={<box-icon name="cog" color={ darkMode?"#fff":"#865400"}></box-icon>}
          ></OffCanvasExample>
        </li>
      </ul>

      <div className="horizontal-line"></div>
      <div className="links">
        <box-icon type="logo" size="md" name="github" color="white"></box-icon>
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
      </div>

      <div className="downloads">
        <div>
          <box-icon
            type="logo"
            name="play-store"
            color="gray"
            size="md"
          ></box-icon>
          <p>
            get it on <br /> <div className="boldText">Google Play</div>
          </p>
        </div>
        <div>
          <box-icon type="logo" name="apple" color="white" size="md"></box-icon>
          <p>
            get it on <br /> <div className="boldText">App Store</div>
          </p>
        </div>
      </div>

      <div className="horizontal-line"></div>

      <p>2023 MoneyMinder All rights reserved</p>
    </div>
  );
};

export default SideBarContainer;
