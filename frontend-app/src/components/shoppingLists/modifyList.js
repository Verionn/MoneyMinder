import React, { useState } from "react";
import { modifyList } from "../../utils/functions/modifyDatasInApi";
import { useContextElements } from "../../utils/hooks/customHooks";
const ModifyList = ({
  modifying,
  listID,
  listName,
  description,
  setDefault,
}) => {
  const [editableListName, setEditableListName] = useState(listName);
  const [editableDescription, setEditableDescription] = useState(description);
  const { modifyListArray } = useContextElements();
  const handleChange = (e) => {
    if (modifying === "description") setEditableDescription(e.target.value);
    if (modifying === "name") setEditableListName(e.target.value);
  };

  const saveListName = async () => {
    if (
      editableListName.length > 0 &&
      editableListName.length < 50 &&
      editableDescription.length < 300
    ) {
      const success = await modifyList(
        listID,
        {
          name: editableListName,
          description: editableDescription,
        },
        modifyListArray
      );
      if (success) {
      } else alert("Error while modifying the list name");
    } else
      alert(
        "Error: Please ensure the name is 1-49 characters and the description is less than 300 characters."
      );
    setDefault(-1);
  };
  const cancel = () => {
    setDefault(-1);
  };

  const styles = {
    inputName: {
      height: "2rem",
      borderRadius: "8px",
    },
    description: {
      height: "60px",
      width: "250px",
      borderRadius: "8px",
      resize: "none",
    },
    box: {
      display: "flex",
      gap: "10px",
      flexWarap: "wrap",
      justifyContent: "center",
      alignItems: "center",
    },
    saveButton: {
      backgroundColor: "var(--secondary-color)",
      color: "white",
      borderRadius: "8px",
      padding: "5px",
    },
    cancelButton: {
      backgroundColor: "red",
      color: "white",
      borderRadius: "8px",
      padding: "5px",
    },
  };

  return (
    <div style={{ ...styles.box }}>
      {modifying === "name" ? (
        <input
          style={{ ...styles.inputName }}
          value={editableListName}
          onChange={handleChange}
          type="text"
        />
      ) : null}
      {modifying === "description" ? (
        <textarea
          style={{ ...styles.description }}
          value={editableDescription}
          onChange={handleChange}
          type="text"
        />
      ) : null}
      <button style={{ ...styles.saveButton }} onClick={saveListName}>
        Save
      </button>
      <button style={{ ...styles.cancelButton }} onClick={cancel}>
        Cancel
      </button>
    </div>
  );
};

export default ModifyList;
