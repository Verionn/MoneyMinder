import "./sideBar.css";
import { CustomIcon } from "../sharedComponents/icons/icons";
import Typography from "../sharedComponents/typography";
import { Row, Col } from "react-bootstrap";
import { useContextElements } from "../../utils/hooks/customHooks";
import { getSectionStyle } from "./sideBarHelpers";
import { useNavigate } from "react-router";
export const RenderSideBarSection = ({ sections, isCollapsed }) => {
  const { updateActiveSection, activeSection, isDarkMode } =
    useContextElements();

  const navigate = useNavigate();
  return sections.map((section, index) => (
    <Row className="custom-row" key={`sidebar-${section.id}`}>
      <div
        className="sideBarBtn"
        onClick={() => {
          updateActiveSection(section.id);
          navigate(section.path);
        }}
        style={{
          background: getSectionStyle(
            section.id,
            activeSection,
            "background",
            isDarkMode
          ),
        }}
      >
        <Col>
          <CustomIcon
            iconName={section.icon}
            size="5px"
            iconColor={getSectionStyle(
              section.id,
              activeSection,
              "color",
              isDarkMode
            )}
          />
        </Col>
        <Col>
          {isCollapsed ? null : (
            <Typography
              tag="h3"
              style={{
                color: getSectionStyle(
                  section.id,
                  activeSection,
                  "color",
                  isDarkMode
                ),
              }}
            >
              {section.title}
            </Typography>
          )}
        </Col>
      </div>
    </Row>
  ));
};
