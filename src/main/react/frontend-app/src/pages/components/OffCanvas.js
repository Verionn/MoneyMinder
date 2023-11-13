import { useState } from "react";
import Button from "react-bootstrap/Button";
import Offcanvas from "react-bootstrap/Offcanvas";
import "boxicons";
import "../../css/sideBarContainer.css";
import { useDarkMode } from "./DarkModeContext";

function OffCanvas({ name, content, ...props }) {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [isHovered, setIsHovered] = useState(false);
  const { darkMode, toggleDarkMode } = useDarkMode();
  console.log("darkMode "+ darkMode);
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
    paddingleft: "1px",
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
      <Offcanvas show={show} onHide={handleClose} {...props}>
        <Offcanvas.Header closeButton>
          <Offcanvas.Title>
            {content} {name}
          </Offcanvas.Title>
        </Offcanvas.Header>

        <Offcanvas.Body>
          <div className="ButtonDarkMode" onClick={toggleDarkMode}>
            <box-icon
              type="solid"
              name={darkMode ? "toggle-left" : "toggle-right"}
              className={darkMode ? "DarkMode" : "LightMode"}
              size='lg'
            ></box-icon>
            <div>{darkMode ? "Dark Mode " : "Light Mode"}</div>
          </div>
        </Offcanvas.Body>
      </Offcanvas>
    </>
  );
}

function Example() {
  return (
    <>
      {["start", "end", "top", "bottom"].map((placement, idx) => (
        <OffCanvas key={idx} placement={placement} name={placement} />
      ))}
    </>
  );
}

export default OffCanvas;
