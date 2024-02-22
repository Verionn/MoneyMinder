import { CustomModal } from "./customModal/customModal";
import React from "react";
import { deleteList } from "../../utils/functions/deleteItemFromApi";
export const ConfirmAction = ({ show, listName, setIsDeletingList,listId }) => {
  const handleClose = () => {
    setIsDeletingList(-1);
  };
  const handleDeleteList = () => {
    if(deleteList(listId)){
      window.location.reload();
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
