import React from "react";
import "./customModal.css";
import { styles } from "./styles";
import {useContextElements} from "../../../utils/hooks/customHooks";
export const CustomModal = ({
  show,
  onClose,
  children,
  ModalTitle,
  ModalConfirmationButton,
  functionTOCall,
  canConfirm,
  confirmButtonColor="var(--primary-color)"
}) => {
    
    const {isDarkMode} = useContextElements();
  if (!show) {
    return null;
  }
  const style = styles({ darkMode: isDarkMode });
  return (
    <div className="custom-modal-backdrop">
      <div className="custom-modal">
        <div className="custom-modal-header" style={{...style.header}}>
          <h2>{ModalTitle}</h2>
          <button onClick={onClose} className="custom-modal-close-button" style={{...style.closeButton}}>
            &times;
          </button>
        </div>
        <div className="custom-modal-body" style={{...style.body}}>{children}</div>
        <div className="custom-modal-footer"  style={{...style.footer}}>
          <button onClick={onClose} className="custom-modal-footer-button">
            Close
          </button>
          <button
            onClick={functionTOCall}
            style={{ backgroundColor: confirmButtonColor }}
            className="custom-modal-footer-button"
            disabled={!canConfirm}
          >
            {ModalConfirmationButton}
          </button>
        </div>
      </div>
    </div>
  );
};

export default CustomModal;
