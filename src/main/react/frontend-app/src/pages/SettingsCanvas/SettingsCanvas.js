import { useState } from "react";
import Button from "react-bootstrap/Button";
import Offcanvas from "react-bootstrap/Offcanvas";
import "boxicons";
import "./SettingsCanvas.css";
import { useDarkMode } from "../../components/DarkModeContext/DarkModeContext";
import SettingsTabs from "./SettingsTabs/SettingsTabs";

function SettingsCanvas({ name, content, ...props }) {
  const [show, setShow] = useState(false);
  const { darkMode } = useDarkMode();
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [isHovered, setIsHovered] = useState(false);

  const buttonStyles = {
    backgroundColor: isHovered ? "#ededed" : "#002A4E",
    color: isHovered ? "#002A4E" : "#ffffff",
    display: "flex",
    fontSize: "10px",
    alignItems: "center",
    justifyContent: "flex-start",
    border: "none",
    transition: "background-color 0.3s, color 0.3s",
    width: "100%",
    paddingLeft: "1px",
  };

  return (
    <>
      <Button
        variant="primary"
        onClick={handleShow}
        style={buttonStyles}
        onMouseEnter={() => setIsHovered(true)}
        onMouseLeave={() => setIsHovered(false)}
        className={darkMode ? "settingsButton Darkmode" : "settingsButton"}
      >
        <span className="customNameStyle">
          {content} {name}
        </span>
      </Button>
      <Offcanvas
        show={show}
        onHide={handleClose}
        {...props}
        className="settingsBox"
        style={{ backgroundColor: darkMode ? "#161616" : "#fff" }}
      >
        <Offcanvas.Header closeButton className="SettingsHeader">
          <Offcanvas.Title
            className={
              darkMode ? "settingsTitle textDarkMode" : "settingsTitle"
            }
          >
            {content} {name}
          </Offcanvas.Title>
        </Offcanvas.Header>

        <Offcanvas.Body className="settingsBody">
          <SettingsTabs></SettingsTabs>
        </Offcanvas.Body>
      </Offcanvas>
    </>
  );
}


export default SettingsCanvas;
