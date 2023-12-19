import React, { useState } from 'react';
import DataContext from './DataContext';
const DataProvider = ({ children }) => {
    const [Items , setItems] = useState([]);
    const [Categories , setCategories] = useState([]);
    const updateItems = (newItems) => {
        setItems(newItems);
    }
    const updateCategories = (newCategories) => {
        console.log("Updating",newCategories);
        setCategories(newCategories);
    
    }

    return (
        <DataContext.Provider value={{ Items, updateItems,Categories , updateCategories }}>
            {children}
        </DataContext.Provider>
    );
}

export default DataProvider;