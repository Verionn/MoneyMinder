import { CustomIcon } from "../icons/icons";
import { useState } from "react";
import "./emptyArray.css";
const EmptyArray = ({ Icon, title, recommendation, buttonText,ButtonAction,size,displayButton=true }) => {
  const [show, setShow] = useState(false);
  const handleShow = () => {
    setShow(true);
  };
  return (
    <div className="EmptyArray">
      <CustomIcon iconName={Icon} size={size} iconColor='' />
      <h1>{title}</h1>
      <p>{recommendation}</p>
      {displayButton && <button className="ButtonEmptyArray" onClick={handleShow}>{buttonText}</button>}
      
      {show && <ButtonAction setShow={setShow} show={show} />}
    </div>
  );
};

export default EmptyArray;
