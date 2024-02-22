
import "./createList.css";

const CreateList = ({ show, setShow, children }) => {
  if (!show) return null;
  const handleClose = () => {
    setShow((prevShow) => {
      console.log("show", !prevShow);
      return false;
    });
    console.log("show", show);
  };

  return (
    <div className="custom-modal-backdrop">
      <div className="custom-modal">
        <div className="custom-modal-header">
          <button onClick={handleClose} className="custom-modal-close-button">
            &times;
          </button>
        </div>
        <div className="custom-modal-body">{children}</div>
        <div className="custom-modal-footer">
          <button onClick={handleClose} className="custom-modal-footer-button">
            Close
          </button>
        </div>
      </div>
    </div>
  );
};

export default CreateList;
