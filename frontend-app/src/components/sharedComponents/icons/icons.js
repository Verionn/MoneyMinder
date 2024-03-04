import React, { useState, useRef, useEffect, useCallback } from "react";
import { Player } from "@lordicon/react";
import {
  getIconState,
  getIconType,
  IconStyle,
  iconStateMap,
} from "./iconHelpers";


export const CustomIcon = ({
  iconName,
  iconColor = "#3c3c3c",
  size = "50px",
  contourSize = "60px",
  isActive = false,
  collapse = false,
}) => {
  const [state, setState] = useState(null);
  const playerRef = useRef(null);
  const playAnimation = useCallback(() => {
    if (state !== null) {
      playerRef.current?.play();
    }
  }, [state]);

  const handleClicked = () => {
    setState(iconStateMap[iconName].clicked);
  };

  const handleHover = (type) => {
    setState(getIconState(iconName, type, iconStateMap));
  };

  const icontype = getIconType(iconName);

  useEffect(() => {
    playAnimation();
  }, [state, playAnimation]);

  return (
    <div
      onClick={handleClicked}
      onMouseEnter={() => handleHover("enter")}
      onMouseLeave={() => handleHover("leave")}
      style={{ ...IconStyle }}
    >
      <Player
        ref={playerRef}
        state={state}
        size={parseInt(size) + 50}
        icon={icontype}
        colorize={iconColor}
      />
    </div>
  );
};
