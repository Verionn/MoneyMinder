import { useState, useEffect } from "react";


const GetList = () => {
  
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
  console.log("datas ======> "+lists);
  return lists;
};

export default GetList;