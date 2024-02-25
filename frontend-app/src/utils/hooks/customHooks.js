import { useContext } from "react";
import { ContextElements } from "../Context/Context";
import { useState, useEffect } from "react";
import { endpoint } from "../datas/serverInfo";
export const useContextElements = () => {
    const context = useContext(ContextElements);
    if (!context) {
      throw new Error("useContextElements must be used within a ContextProvider");
    }
    return context;
  };


  export const useGetInfosFromItemList = ({ listID, operationType }) => {
    const [result, setResult] = useState(operationType === "price" ? '0.00' : 0);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
  
    useEffect(() => {
      const apiUrl = `${endpoint}/lists/${listID}/items`;
      const fetchData = async () => {
        setLoading(true);
        try {
          const response = await fetch(apiUrl);
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          const data = await response.json();
  
          if (operationType === "price") {
            let price = data?.items?.reduce((acc, item) => acc + item.price, 0) || 0;
            setResult(price.toFixed(2));
          } else if (operationType === "len") {
            setResult(data?.items?.length || 0);
            console.log(data?.items?.length);
          }
  
          setLoading(false);
        } catch (err) {
          setError(err);
          setLoading(false);
        }
      };
  
      fetchData();
    }, [listID, operationType]);
  
    return { result, loading, error };
  };


  
  export const useGetInfosFromPurchasedItemsList = ({ listID, operationType }) => {
    const [result, setResult] = useState(operationType === "price" ? '0.00' : 0);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
  
    useEffect(() => {
      const apiUrl = `${endpoint}/purchasedItems/lists/${listID}`;
      const fetchData = async () => {
        setLoading(true);
        try {
          const response = await fetch(apiUrl);
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          const data = await response.json();
  
          if (operationType === "price") {
            let price = data?.purchasedItems?.reduce((acc, item) => acc + item.price, 0) || 0;
            setResult(price.toFixed(2));
          } else if (operationType === "len") {
            setResult(data?.purchasedItems?.length || 0);
          }
  
          setLoading(false);
        } catch (err) {
          setError(err);
          setLoading(false);
        }
      };
  
      fetchData();
    }, [listID, operationType]);
  
    return { result, loading, error };
  };