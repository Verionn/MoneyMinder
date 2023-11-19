import Accordion from 'react-bootstrap/Accordion';
import React from "react";

const handleDescriptionClick = (e) => {
  // Stop event propagation to parent elements
  e.stopPropagation();
  // Call the provided onClick handler

};
function ListDescription({Description, onClick }) {
    return (
        <Accordion className='AccordionDescription' onClick={handleDescriptionClick}>
          <Accordion.Item eventKey="0">
            <Accordion.Header className='descriptionHeader'>Description  </Accordion.Header>
            <Accordion.Body>
              {Description}
            </Accordion.Body>
          </Accordion.Item>
        </Accordion>
      );
}

export default ListDescription;