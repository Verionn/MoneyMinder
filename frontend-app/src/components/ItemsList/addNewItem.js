import { useState } from "react";
import { PlusIcon } from "../sharedComponents/icons/svgIcons";
import "./ItemList.css";
import CustomModal from "../sharedComponents/customModal/customModal";
import AddNewItemForm from "./addNewItemForm";
import { useContextElements } from "../../utils/hooks/customHooks"; 
import { PostNewItem } from "../../utils/functions/addDatasToApi";

const AddNewItem = ({listId}) => {
  const [isAdding, setIsAdding] = useState(false);
  const {handleAddItem}= useContextElements();
  const handleSaveNewItem = (newItem) => {
    console.log('New Item Added:', newItem);
    newItem.listId = Number(listId);
    PostNewItem(newItem, `http://localhost:8080/lists/${listId}/items`, handleAddItem);
    setIsAdding(false);
  };

  return (
    <div>
      <button className="AddNewItemBtn" onClick={() => setIsAdding(true)}>
        <PlusIcon />
        
      </button>
      {isAdding && (
        <CustomModal
          show={isAdding}
          onClose={() => setIsAdding(false)}
          ModalTitle="Add New Item"
          ModalConfirmationButton="Save"
          functionTOCall={handleSaveNewItem}
          canConfirm={true}
        >
          <AddNewItemForm onSave={handleSaveNewItem} onCancel={() => setIsAdding(false)} />
        </CustomModal>
      )}
    </div>
  );
};

export default AddNewItem;
