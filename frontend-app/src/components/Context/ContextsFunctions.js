export const addList = (listsData, newList) => {
    // Check if a list with the same listId already exists
    const existingListIndex = listsData.lists.findIndex(
      (list) => list.listId === newList.listId
    );
  
    if (existingListIndex !== -1) {
      // If the list exists, update its properties
      listsData.lists[existingListIndex] = {
        ...listsData.lists[existingListIndex],
        ...newList,
      };
  
      // Check if "items" array exists, if not, create an empty array
      if (!listsData.lists[existingListIndex].items) {
        listsData.lists[existingListIndex].items = [];
      }
    } else {
      // If the list doesn't exist, add a new one
      const newListWithItems = {
        ...newList,
        items: [], // Create an empty "items" array
      };
  
      listsData.lists.push(newListWithItems);
    }
  
    return listsData;
  };
  

export const removeList = (listsData, listIdToRemove) => {
  // Find the index of the list to be removed
  const listIndexToRemove = listsData.lists.findIndex(
    (list) => list.listId === listIdToRemove
  );

  if (listIndexToRemove !== -1) {
    // If the list is found, remove it from the array
    listsData.lists.splice(listIndexToRemove, 1);
  }

  return listsData;
};

export const addItemToList = (listsData, listId, newItem) => {
  // Find the index of the list
  const listIndex = listsData.lists.findIndex((list) => list.listId === listId);


  if (listIndex !== -1) {
    // If the list exists, check if the item with the same itemId already exists
    const itemIndex = listsData.lists[listIndex].items.findIndex(
      (item) => item.itemId === newItem.itemId
    );

    if (itemIndex !== -1) {
      // If the item already exists, update it
      listsData.lists[listIndex].items[itemIndex] = newItem;
    } else {
      // If the item doesn't exist, add it to the items array
      listsData.lists[listIndex].items.push(newItem);
    }
  }

  return listsData;
};

export const removeItemFromList = (listsData, listId, itemIdToRemove) => {
  // Find the index of the list
  const listIndex = listsData.lists.findIndex((list) => list.listId === listId);

  if (listIndex !== -1) {
    // If the list exists, filter out the item with the specified itemId
    listsData.lists[listIndex].items = listsData.lists[listIndex].items.filter(
      (item) => item.itemId !== itemIdToRemove
    );
  }

  return listsData;
};

export const arraysAreEqual = (arr1, arr2) =>
  JSON.stringify(arr1) === JSON.stringify(arr2);
