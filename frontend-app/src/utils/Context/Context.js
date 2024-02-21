import React, { createContext } from "react";
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

  const updateActiveSection = (sectionId) => {
    setActiveSection(sectionId);
    
  };

  const updateDarkMode = () => {
    setIsDarkMode((prevDarkMode) => !prevDarkMode);
  };

  return (
    <ContextElements.Provider
      value={{ isDarkMode, activeSection, updateDarkMode, updateActiveSection }}
    >
      {children}
    </ContextElements.Provider>
  );
};
