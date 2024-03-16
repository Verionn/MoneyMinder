// LISTS and ITEMS
import { useState, useEffect, useMemo } from "react";

export const GetDataFromApi = ({ apiUrl, options = {} }) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchOptions = useMemo(() => {
    const token = localStorage.getItem("token");
    const headers = {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
      ...options.headers,
    };
    return { ...options, headers };
  }, [options]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(apiUrl, fetchOptions);
        
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const result = await response.json();
        setData(result);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [apiUrl,]);

  return { data, loading, error };
};
