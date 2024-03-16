import React, { useState } from 'react';

const AddNewItemForm = ({ onSave, onCancel }) => {
  const [name, setName] = useState('');
  const [price, setPrice] = useState('');
  const [amount, setAmount] = useState(1);
  const [categoryId, setCategoryId] = useState('');
  const [weight, setWeight] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave({
      name,
      price: parseFloat(price),
      amount: parseInt(amount, 10),
      categoryId: categoryId,
      weight: parseInt(weight, 10),
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={name}
        onChange={(e) => setName(e.target.value)}
        placeholder="Item name"
        required
      />
      <input
        type="number"
        value={price}
        onChange={(e) => setPrice(e.target.value)}
        placeholder="Price"
        min="0.01"
        step="0.01"
        required
      />
      <input
        type="number"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
        placeholder="Amount"
        min="1"
        required
      />
      <input
        type="string"
        value={categoryId}
        onChange={(e) => setCategoryId(e.target.value)}
        placeholder="Category name"
        required
      />
      <input
        type="number"
        value={weight}
        onChange={(e) => setWeight(e.target.value)}
        placeholder="Weight (grams)"
      />
      <div className="form-actions">
        <button type="button" onClick={onCancel}>Cancel</button>
        <button type="submit">Save</button>
      </div>
    </form>
  );
};

export default AddNewItemForm;
