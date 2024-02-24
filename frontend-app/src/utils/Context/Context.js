import React, { createContext, useState, useCallback } from "react";
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

  const [listArray, setListArray] = useState({ lists: [] });

  const updateActiveSection = (sectionId) => {
    setActiveSection(sectionId);
  };

  const updateDarkMode = () => {
    setIsDarkMode((prevDarkMode) => !prevDarkMode);
  };
  
  const updateListArray = useCallback((datas) => {
    setListArray(datas);
  }, []);
  const modifyListArray = useCallback((listId,updatedData) => {
    setListArray((prevState) => {
      const newListArray = prevState.lists.map((list) => {
        if (list.listId === listId) {
         
          return { ...list, ...updatedData };
        }
        return list; 
      });
      return {
        ...prevState,
        lists: newListArray,
      };
    });
  }, []);

  const appendNewElement = useCallback((newElement) => {
    setListArray((prevState) => ({
      ...prevState,
      lists: [...prevState.lists, newElement],
    }));
  }, []);

  const handleDelete = (listId) => {
    setListArray((prevState) => ({
      ...prevState,
      lists: prevState.lists.filter((list) => list.listId !== listId)
    }));
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
        appendNewElement,
        handleDelete,
        modifyListArray,
      }}
    >
      {children}
    </ContextElements.Provider>
  );
};
