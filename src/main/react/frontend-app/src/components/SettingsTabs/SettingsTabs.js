import Col from 'react-bootstrap/Col';
import Nav from 'react-bootstrap/Nav';
import Row from 'react-bootstrap/Row';
import Tab from 'react-bootstrap/Tab';
import "./SettingsTabs.css";
import { useDarkMode } from "../DarkModeContext/DarkModeContext";
function SettingsTabs() {
    const { darkMode, toggleDarkMode } = useDarkMode();
  return (
    <Tab.Container id="left-tabs-example" defaultActiveKey="first" className="TabContainer">
      <Row className="TabContainerRow">
        <Col sm={3} className="NavBars">
          <Nav variant="pills" className="flex-column">
            <Nav.Item className='NavItem'>
              <Nav.Link eventKey="first" className='NavLink'>Tab 1</Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link eventKey="second">Tab 2</Nav.Link>
            </Nav.Item>
          </Nav>
        </Col>
        <Col sm={9}>
          <Tab.Content>
            <Tab.Pane eventKey="first">
              <div className="ButtonDarkMode" onClick={toggleDarkMode}>
                <box-icon
                    type="solid"
                    name={darkMode ? "toggle-left" : "toggle-right"}
                    className={darkMode ? "DarkMode" : "LightMode"}
                    size="lg"
                ></box-icon>
                <div>{darkMode ? "Dark Mode " : "Light Mode"}</div>
              </div>
            </Tab.Pane>
            <Tab.Pane eventKey="second">Second tab content</Tab.Pane>
          </Tab.Content>
        </Col>
      </Row>
    </Tab.Container>
  );
}

export default SettingsTabs;