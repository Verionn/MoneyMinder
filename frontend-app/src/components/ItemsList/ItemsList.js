import { useParams } from "react-router-dom";
import { NavigateToSelectedList } from "./ItemListHelper";
import { useContextElements } from "../../utils/hooks/customHooks";
import { Styles } from "./styles";
import { useEffect } from "react";
import { GetDatasFromApi } from "../../utils/functions/getDatasFromApi";
import { endpoint } from "../../utils/datas/serverInfo";
const ItemsList = () => {
  const { listId } = useParams();
  const { windowWidth, isDarkMode } = useContextElements();
  const styles = Styles({ darkMode: isDarkMode, windowWidth });
  const apiURL = `${endpoint}/lists/${listId}/items`;
  const { data, loading, error } = GetDatasFromApi({ apiUrl: apiURL });
  useEffect(() => {}, [windowWidth, isDarkMode]);

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error.message}</div>;
  return (
    <div className="ItemContainer" style={{ ...styles.ItemsContainer }}>
      <NavigateToSelectedList listId={listId} />
      <div>
        {data.items &&
          data.items.map((item, index) => (
            <div key={index} className="Item">
              <div>{item.name}</div>
              
            </div>
          ))}

      </div>
    </div>
  );
};

export default ItemsList;
