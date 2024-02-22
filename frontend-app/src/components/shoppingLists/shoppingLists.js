import { useContextElements } from "../../utils/hooks/customHooks";
import { GetListsData } from "../../utils/functions/getDatasFromBackEnd";
import { endpoint } from "../../utils/datas/serverInfo";
import { useEffect } from "react";
const ShoppingLists = () => {
  const { listArray, updateListArray } = useContextElements();
  const apiURL = `${endpoint}/lists`;
  const { data, loading, error } = GetListsData({ apiUrl: apiURL });
    useEffect(() => {
    if (data) {
      updateListArray(data);
    }
    }, [data,updateListArray]);
    console.log("data", listArray);
    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error.message}</p>;
 
  return (
    <div>
    {listArray?.lists?.map((list) => (
    <div key={list.listId}>
      <h2>{list.name}</h2>
      <p>{list.description || 'No description provided.'}</p>
    </div>
  ))}
    </div>
  );
};

export default ShoppingLists;
