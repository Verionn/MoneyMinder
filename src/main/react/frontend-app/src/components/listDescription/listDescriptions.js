import Accordion from 'react-bootstrap/Accordion';

function ListDescription({Description}) {
    return (
        <Accordion className='AccordionDescription'>
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