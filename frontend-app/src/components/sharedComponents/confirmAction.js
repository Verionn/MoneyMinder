import { CustomModal } from "./customModal/customModal";
import React from "react";
import { deleteList } from "../../utils/functions/deleteItemFromApi";
import { useContextElements } from "../../utils/hooks/customHooks";
export const ConfirmAction = ({ show, listName, setIsDeletingList,listId }) => {
  const { handleDelete } = useContextElements();
  const handleClose = () => {
    setIsDeletingList(-1);
  };
  const handleDeleteList = () => {
    if(deleteList(listId,handleDelete)){
      
    }else alert("Failed to delete the list");
    setIsDeletingList(-1);
  };
  return (
    <CustomModal
      show={show}
      onClose={handleClose}
      ModalTitle={`Do you want to delete the list ${listName} ?`}
      confirmButtonColor="red"
      ModalConfirmationButton="Delete"
      functionTOCall={handleDeleteList}
      canConfirm={true}
    ></CustomModal>
  );
};
