import "./section.css";
import React, { useState } from "react";
import { useContextElements } from "../../utils/hooks/customHooks";
import { getActiveTitle } from "../../utils/functions/function";
import { pageSections } from "../../utils/data/appInfo";
import { PlusIcon, MenuIcon, ContentIcon, ChevronDownSquareIcon, LogOutIcon } from "../sharedComponents/icons/svgIcons";
import { Styles } from "./styles";
import CreateList  from "../sharedComponents/CreateList/createList";
import { handleLogout } from "../../utils/functions/authentication";
const SectionHeder = () => {
  const [show, setShow] = useState(false);
  const { activeSection, isDarkMode } = useContextElements();
  const [contentDisplay, setContentDisplay] = useState("content");
  const title = getActiveTitle(activeSection, pageSections);
  const handleContentDisplay = () => {
    setContentDisplay(contentDisplay === "content" ? "menu" : "content");
  };
  const style = Styles({ darkMode: isDarkMode });
  const handleShow = () => {
    setShow(true);
  };
  return (
    <div className="sectionHeader">
      <h1>{title}</h1>
      <div className="sectionHeaderRight">
        <button className="addListContainer" onClick={handleShow}>
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
        <LogOutIcon style={{ ...style.iconFill }} onClick={handleLogout} />
      </div>
      {show && <CreateList setShow={setShow} show={show} />}
    </div>
  );
};

export default SectionHeder;
