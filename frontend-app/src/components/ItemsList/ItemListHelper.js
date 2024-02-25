import { useNavigate } from "react-router-dom";
import { useContextElements } from "../../utils/hooks/customHooks";
import { GetDatasFromApi } from "../../utils/functions/getDatasFromApi";
import { endpoint } from "../../utils/datas/serverInfo";
import { useEffect, useState } from "react";
import "./ItemList.css";
export const NavigateToSelectedList = ({ listId }) => {
  const { listArray, updateListArray } = useContextElements();
  const apiURL = `${endpoint}/lists`;
  const { data, loading, error } = GetDatasFromApi({ apiUrl: apiURL });
  const [selectedValue, setSelectedValue] = useState("");
  const navigate = useNavigate();
  useEffect(() => {
    if (data && data.lists) {
      updateListArray(data);
    }
  }, [data, updateListArray]);

  useEffect(() => {
    if (listArray.lists) {
      const currentList = listArray.lists.find(
        (list) => list.listId.toString() === listId
      );
      if (currentList) {
        setSelectedValue(currentList.name);
      }
    }
  }, [listArray, listId]);

  const handleChange = (event) => {
    const newValue = event.target.value;
    setSelectedValue(newValue);
    navigate(`/shopping-list/${newValue}`);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;
  return (
    <div className="SelectList">
      <select value={selectedValue} onChange={handleChange} className="dropdownMenu">
        {listArray.lists &&
          listArray.lists.map((list, index) => (
            <option key={index} value={list.listId}>
              {list.name}
            </option>
          ))}
      </select>
    </div>
  );
};
