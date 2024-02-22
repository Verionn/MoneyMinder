import "./createList.css";
import { CustomModal } from "../customModal/customModal";
import Typography from "../../sharedComponents/typography";
import { useContextElements } from "../../../utils/hooks/customHooks";
import { styles } from "./styles";
import { useEffect, useState } from "react";
import { PostNewList } from "../../../utils/functions/addDatasToApi";
const CreateList = ({ show, setShow }) => {
  const { isDarkMode } = useContextElements();
  const [listName, setListName] = useState("");
  const [description, setDescription] = useState("");
  const [canCreateList, setCanCreateList] = useState(false);
  const maxDescriptionLength = 300;
  const maxListNameLength = 50;

  const handleClose = () => {
    setShow((prevShow) => {
      console.log("show", !prevShow);
      return false;
    });
  };

  function checkIfCanCreateList(
    listName,
    description,
    maxDescriptionLength,
    maxListNameLength
  ) {
    if (
      listName.length > 0 &&
      listName.length <= maxListNameLength &&
      description.length <= maxDescriptionLength
    ) {
      setCanCreateList(true);
    } else {
      setCanCreateList(false);
    }
  }

  const style = styles({ darkMode: isDarkMode });
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    console.log("name", name);
    if (name === "listName") {
      setListName(value);
    } else if (name === "description") {
      setDescription(value);
    }
  };

  useEffect(() => {
    checkIfCanCreateList(
      listName,
      description,
      maxDescriptionLength,
      maxListNameLength
    );
  }, [listName, description]);

  const handleCreateNewList = async () => {
    if (PostNewList(listName, description)) {
      window.location.reload();
      handleClose();
    } else {
      alert("Error creating a new list");
    }
  };
  if (!show) return null;
  return (
    <CustomModal
      show={show}
      onClose={handleClose}
      ModalTitle="Create New List"
      ModalConfirmationButton="Create"
      functionTOCall={handleCreateNewList}
      canConfirm={canCreateList}
    >
      <div className="createList">
        <div className="CreatelistName">
          <Typography variant="h6">List Name</Typography>
          <textarea
            type="text"
            name="listName"
            placeholder="List Name"
            className="inputListName"
            style={{ ...style.inputBackground }}
            onChange={handleInputChange}
          />
        </div>
        <div className="counts">
          {" "}
          {listName.length > maxListNameLength && (
            <Typography tag="caption" style={{ color: "red" }}>
              List name should not exceed {`${maxListNameLength}`} characters
            </Typography>
          )}
          {`${listName.length}/${maxListNameLength}`}
        </div>

        <div className="CreatelistName">
          <Typography variant="h6">Description</Typography>
          <textarea
            type="text"
            name="description"
            placeholder="List Description"
            className="inputDescription"
            style={{ ...style.inputBackground }}
            onChange={handleInputChange}
          />
          <div className="counts">
            {description.length > maxDescriptionLength && (
              <Typography tag="caption" style={{ color: "red" }}>
                List name should not exceed {`${maxDescriptionLength}`} characters
              </Typography>
            )}
            {`${description.length}/${maxDescriptionLength}`}
          </div>
        </div>
      </div>
    </CustomModal>
  );
};

export default CreateList;
