import { useState } from "react";
import { PlusIcon } from "../sharedComponents/icons/svgIcons";
import "./ItemList.css";
import CustomModal from "../sharedComponents/customModal/customModal";
import AddNewItemForm from "./addNewItemForm";
import { useContextElements } from "../../utils/hooks/customHooks";
import {
  PostNewCategory,
  PostNewItem,
} from "../../utils/functions/addDataToApi";
import { GetDataFromApi } from "../../utils/functions/getDataFromApi";
import { categoriesUrl } from "../../utils/data/serverInfo";
import {
  findIdCategoryByName,
  isElementInListCategory,
} from "../../utils/functions/function";
const AddNewItem = ({ listId }) => {
  const [isAdding, setIsAdding] = useState(false);
  const { handleAddItem } = useContextElements();
  const { data: CategoryData } = GetDataFromApi({ apiUrl: categoriesUrl });

  const handleSaveNewItem = async (newItem) => {
    console.log(newItem);
    if (!isElementInListCategory(CategoryData.categories, newItem.categoryId)) {
      const check = await PostNewCategory(newItem.categoryId);
      if (!check) {
        alert("Category not found and not created");
        return;
      }
    }
    const categoryId = findIdCategoryByName(
      CategoryData.categories,
      newItem.categoryId
    );
    if (categoryId !== null) newItem.categoryId = categoryId;
    newItem.listId = Number(listId);
    if (isNaN(newItem.weight)) newItem.weight = 0;

    PostNewItem(
      newItem,
      `http://localhost:8080/lists/${listId}/items`,
      handleAddItem,
      "items"
    );
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
          <AddNewItemForm
            onSave={handleSaveNewItem}
            onCancel={() => setIsAdding(false)}
          />
        </CustomModal>
      )}
    </div>
  );
};

export default AddNewItem;
