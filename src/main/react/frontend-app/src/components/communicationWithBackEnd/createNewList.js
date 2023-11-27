import React, { useState } from "react";
import { Button, Modal, Form } from "react-bootstrap";
import "boxicons";

const CreateNewList = ({ onClose }) => {
  const [show, setShow] = useState(false);
  const [listName, setListName] = useState("");
  const [description, setDescription] = useState("");
  const [descriptionCount, setDescriptionCount] = useState(0);
  const maxDescriptionLength = 3;
  const maxListNameLength = 5; // Adjust this according to your requirement
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    if (name === "listName") {
      setListName(value);
    } else if (name === "description") {
      setDescription(value);
      setDescriptionCount(value.length);
    }
  };

  const handleCreateNewList = async () => {
    try {
      // Send a request to create a new list
      if (listName.length > 0 && listName.length <= maxListNameLength) {
        const response = await fetch("http://localhost:8080/lists", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ name: listName, description: description }),
        });

        if (!response.ok) {
          throw new Error("Failed to create a new list");
        }

        // Close the modal after creating the new list
        handleClose();
        // Reload the page to fetch the updated data
        window.location.reload(true);
      } else {
        alert("Please enter a list name");
      }

      // Optionally, you can perform additional actions after creating the list
      // ...
    } catch (error) {
      console.error("Error creating a new list:", error);
    }
  };

  return (
    <div>
      <Button variant="primary" onClick={handleShow}>
        <box-icon name="plus" border="circle" color="white"></box-icon>
      </Button>

      <Modal show={show} onHide={handleClose} centered>
        <Modal.Header closeButton>
          <Modal.Title>Create a New List</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form.Group controlId="formListName">
            <Form.Label>List Name</Form.Label>
            <Form.Control
              type="text"
              name="listName"
              placeholder="Enter list name"
              value={listName}
              onChange={handleInputChange}
            />
            {(listName.length === 0 || listName.length > maxListNameLength) && (
              <div className="errorMessage">
                {listName.length === 0
                  ? "Please enter a name"
                  : `Name should be less than ${maxListNameLength} characters`}
              </div>
            )}
          </Form.Group>
          <Form.Group controlId="formListDescription">
            <Form.Label>Description</Form.Label>
            <Form.Control
              type="text"
              name="description"
              placeholder="Enter list description"
              value={description}
              onChange={handleInputChange}
              className="writeDescription"
            />
            <div className="characterCount">
              {descriptionCount}/{maxDescriptionLength}
            </div>
            {description.length > maxDescriptionLength && (
              <div className="errorMessage">
                Description should be less than {maxDescriptionLength} characters.
              </div>
            )}
          </Form.Group>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleCreateNewList} disabled={listName.length === 0 || listName.length > maxListNameLength}>
            Save changes
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default CreateNewList;
