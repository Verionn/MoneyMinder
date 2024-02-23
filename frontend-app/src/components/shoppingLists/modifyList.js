import React, { useState } from "react";
import { modifyList } from "../../utils/functions/modifyDatasInApi";

const ModifyList = ({
  modifying,
  listID,
  listName,
  description,
  setDefault,
}) => {
  const [editableListName, setEditableListName] = useState(listName);
  const [editableDescription, setEditableDescription] = useState(description);
  const handleChange = (e) => {
    if (modifying === "description") setEditableDescription(e.target.value);
    if (modifying === "name") setEditableListName(e.target.value);
  };

  const saveListName = () => {
    if (
      modifyList(
        listID,
        {
          name: editableListName,
          description: editableDescription,
        } &&
          editableListName.lenght > 0 &&
          editableDescription.lenght > 0 &&
          editableListName.lenght < 50 &&
          editableDescription.lenght < 250
      )
    ) {
      window.location.reload();
    } else alert("Error while modifying the list name");
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
      <button style={{ ...styles.saveButton }} onClick={saveListName}>Save</button>
      <button style={{...styles.cancelButton}} onClick={cancel}>Cancel</button>
    </div>
  );
};

export default ModifyList;
