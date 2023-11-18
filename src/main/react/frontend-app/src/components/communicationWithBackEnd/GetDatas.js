// DataFetcher.js
import  { useState, useEffect } from 'react';

const fetchDataFromApi = async (url) => {
  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    const result = await response.json();
    return result;
  } catch (error) {
    throw new Error(`Error fetching data: ${error.message}`);
  }
};

const GetDatas = ({ apiUrl }) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const result = await fetchDataFromApi(apiUrl);
        setData(result);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [apiUrl]);

  
  return { data, loading, error };
};

export default GetDatas;
