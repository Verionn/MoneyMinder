import React, { createContext, useContext, useState } from "react";
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

/*------------MyArray----------------*/
const ListArrayContext = createContext({
  listArray: [],
  addElement: () => {},
  removeElement: () => {},
});

export const ListArrayProviderContext = ({ children }) => {
  const [listArray, setListArray] = useState([]);
  const arraysAreEqual = (arr1, arr2) =>
    JSON.stringify(arr1) === JSON.stringify(arr2);
  const initializeArray = (array) => {
    if (!arraysAreEqual(listArray, array)) {
      setListArray(array);
    }
  };
  const addElement = (element) => {
    setListArray((prevlistArray) => [...prevlistArray, element]);
  };

  const removeElement = (element) => {
    setListArray((prevlistArray) =>
      prevlistArray.filter((item) => item !== element)
    );
  };

  return (
    <ListArrayContext.Provider
      value={{ listArray, addElement, removeElement, initializeArray }}
    >
      {children}
    </ListArrayContext.Provider>
  );
};

export const useListArray = () => {
  const context = useContext(ListArrayContext);
  if (!context) {
    throw new Error("useMyArray must be used within a MyArrayProvider");
  }
  return context;
};
