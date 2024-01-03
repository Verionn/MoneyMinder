import React from 'react';
import DropdownButton from 'react-bootstrap/DropdownButton';
import Dropdown from 'react-bootstrap/Dropdown';

const ListDropdown = ({ lists, onSelect }) => {
  return (
    <DropdownButton id="list-dropdown" title="Select a List" className={"listDropdownButton"}>
      {lists.map((list) => (
        <Dropdown.Item key={list.listId} onClick={() => onSelect(list.listId)}>
          {list.name}
        </Dropdown.Item>
      ))}
    </DropdownButton>
  );
};

export default ListDropdown;
