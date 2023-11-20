import React, { useState } from "react";
import { Button, Modal, Form } from "react-bootstrap";
import "boxicons";

const CreateNewList = ({ onClose }) => {
  const [show, setShow] = useState(false);
  const [listName, setListName] = useState("");
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const handleInputChange = (e) => {
    setListName(e.target.value);
  };
  const handleCreateNewList = async () => {
    try {
      // Send a request to create a new list
      if (listName.length > 0) {
        const response = await fetch("http://localhost:8080/lists", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ name: listName }),
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
              placeholder="Enter list name"
              value={listName}
              onChange={handleInputChange}
            />
          </Form.Group>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleCreateNewList}>
            Save changes
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default CreateNewList;
