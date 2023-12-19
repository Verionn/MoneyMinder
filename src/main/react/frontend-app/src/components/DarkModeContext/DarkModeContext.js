import React, { createContext, useContext, useEffect } from "react";
import {useLocalStorageState} from "../functions/GetDatasFromItems";
const DarkModeContext = createContext();

export const DarkModeProviderCOntext = ({ children }) => {
  

  const [darkMode, setDarkMode] = useLocalStorageState('darkMode', false);



  // Save state to local storage whenever the state changes
  useEffect(() => {
    window.localStorage.setItem("darkMode", JSON.stringify(darkMode));
  }, [darkMode]);

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
