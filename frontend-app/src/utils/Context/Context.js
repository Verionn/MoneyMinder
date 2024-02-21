import React, { createContext } from "react";
import { useLocalStorageState } from "../functions/function";

//Create a context
export const ContextElements = createContext();

//Create a provider
export const ContextProvider = ({ children }) => {
  const [darkMode, setDarkMode] = useLocalStorageState("darkModeMoneyMinder", false);

  const updateDarkMode = () => {
    setDarkMode((prevDarkMode) => !prevDarkMode);
  };

  return (
    <ContextElements.Provider value={{ darkMode, updateDarkMode }}>
      {children}
    </ContextElements.Provider>
  );
};





