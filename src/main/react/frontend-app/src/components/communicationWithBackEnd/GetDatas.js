import { useState, useEffect } from "react";

const fetchData = async (url, setData) => {
  try {
    const response = await fetch(url);
    const result = await response.json();
    setData(result);
    console.log('list : '+ result.items);
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};

const GetDatas = ({ link }) => {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetchData(link, setData);
  }, [link]); 

  return data;
};

export default GetDatas;
