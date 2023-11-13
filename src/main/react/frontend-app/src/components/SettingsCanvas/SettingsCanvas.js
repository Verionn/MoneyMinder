import { useState } from "react";
import Button from "react-bootstrap/Button";
import Offcanvas from "react-bootstrap/Offcanvas";
import "boxicons";
import "../../pages/SideBarContainer/sideBarContainer.css";
import "./SettingsCanvas.css";

import SettingsTabs from "../SettingsTabs/SettingsTabs";

function SettingsCanvas({ name, content, ...props }) {
  const [show, setShow] = useState(false);

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
      >
        <Offcanvas.Header closeButton className="SettingsHeader">
          <Offcanvas.Title>
            {content} {name}
          </Offcanvas.Title>
        </Offcanvas.Header>

        <Offcanvas.Body className="settingsBody  .offcanvas-body">
          <SettingsTabs></SettingsTabs>
        </Offcanvas.Body>
      </Offcanvas>
    </>
  );
}

function Example() {
  return (
    <>
      {["start", "end", "top", "bottom"].map((placement, idx) => (
        <SettingsCanvas key={idx} placement={placement} name={placement} />
      ))}
    </>
  );
}

export default SettingsCanvas;
