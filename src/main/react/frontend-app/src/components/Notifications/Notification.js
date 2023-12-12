import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Toast from 'react-bootstrap/Toast';
import ToastContainer from 'react-bootstrap/ToastContainer';

function Notification(message) {
    let cont = 0;
  const [showNotification, setShowNotification] = useState(false);

  const handleShowNotification = () => {
    setShowNotification(true);
    setTimeout(() => setShowNotification(false), 3000); 
  };



  return (
    <div >
      <ToastContainer className="p-3" position="bottom-end" style={{ zIndex: 1 }}>
        <Toast show={showNotification} onClose={() => setShowNotification(false)}>
          <Toast.Body>{message}</Toast.Body>
        </Toast>
      </ToastContainer>
    </div>
  );
}

export default Notification;
