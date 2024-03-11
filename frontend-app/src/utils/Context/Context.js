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

  const [windowWidth, setWindowWidth] = useState(window.innerWidth);
  const [LoginType, setLoginType] = useState("login");
  const [listArray, setListArray] = useState({ lists: [] });
  const [itemsArray, setItemsArray] = useState({ items: [] });
  const [purchasedItemsArray, setPurchasedItemsArray] = useState({purchasedItems: []});

  const updateActiveSection = (sectionId) => {
    setActiveSection(sectionId);
  };

  const updateLoginType = (type) => {
    console.log(type);
    setLoginType(type);
  };

  const handleResize = () => {
    setWindowWidth(window.innerWidth);
  };

  const updateDarkMode = () => {
    setIsDarkMode((prevDarkMode) => !prevDarkMode);
  };

  const updateListArray = useCallback((datas) => {
    setListArray(datas);
  }, []);

  const modifyListArray = useCallback((listId, updatedData) => {
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
      lists: prevState.lists.filter((list) => list.listId !== listId),
    }));
  };

  const handleDeleteItemsInItemsArray = async (listId, itemId) => {
    const updatedItems = itemsArray.items.filter(
      (item) =>
        !(
          Number(item.itemId) === Number(itemId) &&
          Number(item.listId) === Number(listId)
        )
    );
    setItemsArray({ items: updatedItems });
  };

  const updateItemsArray = useCallback((datas) => {
    setItemsArray(datas);
  }, []);

  const handleAddItem = useCallback((item) => {
    const { itemId, listId } = item;
    const existingItemIndex = itemsArray.items.findIndex(
      (existingItem) =>
        existingItem.itemId === itemId && existingItem.listId === Number(listId)
    );
    if (existingItemIndex !== -1) {
      const updatedItems = [...itemsArray.items];
      updatedItems[existingItemIndex] = item;
      setItemsArray({ items: updatedItems });
    } else {
      setItemsArray((prevState) => ({
        ...prevState,
        items: [...prevState.items, item],
      }));
    }
  }, [itemsArray.items]);


  //purchase items
    const handleDeleteItemsInPurchasedItems = async (listId, itemId) => {
    const updatedItems = itemsArray.items.filter(
      (item) =>
        !(
          Number(item.itemId) === Number(itemId) &&
          Number(item.listId) === Number(listId)
        )
    );
    setPurchasedItemsArray({ items: updatedItems });
  };

  const updatePurchasedItemsArray = useCallback((datas) => {
    setPurchasedItemsArray(datas);
  }, []);

  const handleAddPurchasedItem = useCallback((item) => {
    const { itemId, listId } = item;
    const existingItemIndex = purchasedItemsArray.purchasedItems.findIndex(
      (existingItem) =>
        existingItem.itemId === itemId && existingItem.listId === Number(listId)
    );
    if (existingItemIndex !== -1) {
      const updatedItems = [...purchasedItemsArray.purchasedItems];
      updatedItems[existingItemIndex] = item;
      setPurchasedItemsArray({ purchasedItems: updatedItems });
    } else {
      setPurchasedItemsArray((prevState) => ({
        ...prevState,
        purchasedItems: [...prevState.purchasedItems, item],
      }));
    }
  }, [purchasedItemsArray.purchasedItems]);


  return (
    <ContextElements.Provider
      value={{
        isDarkMode,
        activeSection,
        listArray,
        windowWidth,
        itemsArray,
        purchasedItemsArray,
        LoginType,
        updateDarkMode,
        updateActiveSection,
        updateListArray,
        appendNewElement,
        handleDelete,
        modifyListArray,
        handleResize,
        handleDeleteItemsInItemsArray,
        handleAddItem,
        updateItemsArray,
        handleDeleteItemsInPurchasedItems,
        handleAddPurchasedItem,
        updatePurchasedItemsArray,
        updateLoginType,
      }}
    >
      {children}
    </ContextElements.Provider>
  );
};
