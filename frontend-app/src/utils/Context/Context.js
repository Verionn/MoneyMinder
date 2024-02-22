import React, { createContext, useState } from "react";
import { useLocalStorageState } from "../functions/function";

//Create a context
export const ContextElements = createContext();

//Create a provider
export const ContextProvider = ({ children }) => {
  const [isDarkMode, setIsDarkMode] = useLocalStorageState(
    "darkModeMoneyMinder",
    false
  );
  const [activeSection, setActiveSection] = useLocalStorageState(
    "activeSectionMoneyMinder",
    "home"
  );

  const [listArray, setListArray] = useState([]);

  const updateActiveSection = (sectionId) => {
    setActiveSection(sectionId);
  };

  const updateDarkMode = () => {
    setIsDarkMode((prevDarkMode) => !prevDarkMode);
  };

  const updateListArray = (newListArray) => {
    setListArray(newListArray);
  };

  return (
    <ContextElements.Provider
      value={{
        isDarkMode,
        activeSection,
        listArray,
        updateDarkMode,
        updateActiveSection,
        updateListArray,
      }}
    >
      {children}
    </ContextElements.Provider>
  );
};
