
import "./createList.css";
import {CustomModal} from "../customModal/customModal";

const CreateList = ({ show, setShow, children }) => {
  if (!show) return null;
  const handleClose = () => {
    setShow((prevShow) => {
      console.log("show", !prevShow);
      return false;
    });
    
  };

  return (
    <CustomModal
      show={show}
      onClose={handleClose}
      ModalTitle="Create New List"
      ModalConfirmationButton="Create"
      functionTOCall={handleClose}
    >
      <div className="createList">
        <input type="text" placeholder="List Name" />
        <input type="text" placeholder="List Description" />
       
      </div>
    </CustomModal>
  );
};

export default CreateList;
