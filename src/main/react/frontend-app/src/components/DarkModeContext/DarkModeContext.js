import React, { createContext, useContext } from "react";
import { useLocalStorageState } from "../functions/functions";
const DarkModeContext = createContext();

export const DarkModeProviderCOntext = ({ children }) => {
  const [darkMode, setDarkMode] = useLocalStorageState("darkMode", false);

  const toggleDarkMode = () => {
    setDarkMode((prevDarkMode) => !prevDarkMode);
  };

  return (
    <DarkModeContext.Provider value={{ darkMode, toggleDarkMode }}>
      {children}
    </DarkModeContext.Provider>
  );
};

export const useDarkMode = () => {
  const context = useContext(DarkModeContext);
  if (!context) {
    throw new Error("useDarkMode must be used within a DarkModeProvider");
  }
  return context;
};
