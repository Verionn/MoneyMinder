import React, { useState, useEffect } from "react";
import "../css/mainContainer.css";

const YourComponent = () => {
  // Declare 'lists' and 'setLists' using the useState hook
  const [lists, setLists] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await fetch("http://localhost:8080/lists");
      const result = await response.json();
      setLists(result.lists);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  return (
    <div className="test">
      <h2>List of Lists on</h2>
      {console.log("list "+lists)} {/* Correct way to log in JSX */}
      <ul>
        {lists.map((list) => (
          <li key={list.listId}>
            {list.name} - Full Price: {list.fullPrice}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default YourComponent;
