import { useParams } from "react-router-dom";
import { ListSelectionNavigator } from "./ItemListHelper";
import { Styles } from "./styles";
import { useContextElements } from "../../utils/hooks/customHooks";
import ItemBox from "./itemBox";
import AddNewItem from "./addNewItem";
const ItemsList = () => {
  const { listId } = useParams();

  const { windowWidth, isDarkMode } = useContextElements();
  const styles = Styles({ darkMode: isDarkMode, windowWidth });
  return (
    <div className="ItemContainer" style={{ ...styles.ItemsContainer }}>
      <ListSelectionNavigator listId={listId} />
      <div style={{ ...styles.ItemBoxContainer }}>
        <ItemBox listId={listId} />
        <AddNewItem listId={listId} />
      </div>
    </div>
  );
};

export default ItemsList;
