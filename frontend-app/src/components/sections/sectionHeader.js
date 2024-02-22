import "./section.css";
import React, { useState } from "react";
import { useContextElements } from "../../utils/hooks/customHooks";
import { getActiveTitle } from "../../utils/functions/function";
import { pageSections } from "../../utils/datas/appInfo";
import { ReactComponent as PlusIcon } from "../../utils/assets/SVG/bx-plus.svg";
import { ReactComponent as MenuIcon } from "../../utils/assets/SVG/bx-menu.svg";
import { ReactComponent as ContentIcon } from "../../utils/assets/SVG/bxs-book-content.svg";
import { ReactComponent as ChevronDownSquareIcon } from "../../utils/assets/SVG/bxs-chevron-down-square.svg";
import { Styles } from "./styles";
const SectionHeder = () => {
  const { activeSection, isDarkMode } = useContextElements();
  const [contentDisplay, setContentDisplay] = useState("content");
  const title = getActiveTitle(activeSection, pageSections);
  const handleContentDisplay = () => {
    setContentDisplay(contentDisplay === "content" ? "menu" : "content");
  };
  const style = Styles({ darkMode: isDarkMode });
  return (
    <div className="sectionHeader">
      <h1>{title}</h1>
      <div className="sectionHeaderRight">
        <button className="addListContainer">
          <PlusIcon style={{ ...style.iconFill }} />
          <p>new list</p>
        </button>
        <button className="contentDisplay" onClick={handleContentDisplay}>
          {contentDisplay === "content" ? (
            <ContentIcon style={{ ...style.iconFill }} />
          ) : (
            <MenuIcon style={{ ...style.iconFill }} />
          )}
          <p>View</p>
          <ChevronDownSquareIcon style={{ ...style.iconFill }} />
        </button>
      </div>
    </div>
  );
};

export default SectionHeder;
