import React, { createContext, useContext, useState } from "react";
import { useLocalStorageState } from "../functions/functions";
import {
  addList,
  removeItemFromList,
  addItemToList,
  removeList,
  arraysAreEqual,
} from "./ContextsFunctions";
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
  itemsArray: [],
  allListAndItesm: { lists: [] },
  addElement: () => {},
  removeElement: () => {},
});

export const ListArrayProviderContext = ({ children }) => {
  const [listArray, setListArray] = useState([]);
  const [itemsArray, setItemsArray] = useState([]);
 const [allListAndItesm, setallListAndItesm] = useState({ lists: [] });


  const initializeArray = async (array, type) => {
    if (type === "list") {
      if (!arraysAreEqual(listArray, array)) {
        for (let i = 0; i < array.length; i++) {
          const updatedList = addList(allListAndItesm, array[i]);
          setallListAndItesm(updatedList);
        }
        setListArray(array);
      }
    } else if (type === "items") {
      if (!arraysAreEqual(itemsArray, array)) {
        const ItemListID = array[0].listId;
        for (let i = 0; i < array.length; i++) {
          const updatedList = addItemToList(allListAndItesm, ItemListID, array);
          setallListAndItesm(updatedList);
        }
        setItemsArray(array);
      }
    }
  };
  const addElement = async (element, type) => {
    if (type === "list") {
      for (let i = 0; i < element.length; i++) {
        const updatedList = addList(allListAndItesm, element);
        setallListAndItesm(updatedList);
      }
      setListArray((prevlistArray) => [...prevlistArray, element]);
    } else if (type === "items") {
      const ItemListID = element[0].listId;
      for (let i = 0; i < element.length; i++) {
        const updatedList = addItemToList(allListAndItesm, ItemListID, element);
        setallListAndItesm(updatedList);
      }
      setItemsArray((prevItemsArray) => [...prevItemsArray, element]);
    }
  };

  const removeElement = (element, type) => {
    if (type === "list") {
      setListArray((prevlistArray) =>
        prevlistArray.filter((item) => item !== element)
      );
    } else if (type === "items") {
      setItemsArray((prevlistArray) =>
        prevlistArray.filter((item) => item !== element)
      );
    }
  };

  return (
    <ListArrayContext.Provider
      value={{
        listArray,
        itemsArray,
        allListAndItesm,
        addElement,
        removeElement,
        initializeArray,
      }}
    >
      {children}
    </ListArrayContext.Provider>
  );
};

export const UseListArray = () => {
  const context = useContext(ListArrayContext);
  if (!context) {
    throw new Error("useMyArray must be used within a MyArrayProvider");
  }
  return context;
};


/*-------------ViewList----------------*/
const viewListContext = createContext({
  view:[],
  handleViewList: () => {},
  handleViewContent: () => {},
});

export const ViewListProviderContext = ({ children }) => {
  const [view, setView] = useLocalStorageState("view", []);

 const handleView = (view) => {
    setView(view);
  };

 
  return (
    <viewListContext.Provider
      value={{
        view,
        handleView,
      }}
    >
      {children}
    </viewListContext.Provider>
  );
};

export const UseViewList = () => {
  const context = useContext(viewListContext);
  if (!context) {
    throw new Error("useMyArray must be used within a MyArrayProvider");
  }
  return context;
};

