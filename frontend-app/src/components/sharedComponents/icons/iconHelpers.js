const iconCart = require("../../../utils/assets/jsonIcons/system-solid-6-shopping.json");
const iconTrash = require("../../../utils/assets/jsonIcons/system-solid-39-trash.json");
const iconHelp = require("../../../utils/assets/jsonIcons/system-solid-57-help-question.json");
const iconSettings = require("../../../utils/assets/jsonIcons/system-solid-63-settings-cog.json");
const iconPencil = require("../../../utils/assets/jsonIcons/wired-flat-35-edit.json");
//ALL STATES FOR EACH ICON

export const iconStateMap = {
  cartIcon: {
    clicked: "in-shopping",
    hover1: "hover-shopping",
  },
  trashIcon: {
    clicked: "in-trash-empty",
    hover1: "hover-trash-empty",
    hover2: "hover-trash-full",
  },
  helpIcon: {
    clicked: "in-help",
    hover1: "hover-help-3",
    hover2: "hover-help-4",
  },
  settingsIcon: {
    clicked: "in-cog",
    hover1: "hover-cog",
    hover2: "hover-cog-2",
  },
  pencilIcon: {
    clicked: "in-dynamic",
    hover1: "hover-circle",
    hover2: "hover-line",
  },
};

export const IconStyle = ({ size, color, boxsize, backgroundColor }) => {
  return {
    width: parseInt(boxsize) + 10 + "px",
    height: parseInt(boxsize) + 10 + "px",
    color: color,
    border: `solid 3px ${color}`,
    borderRadius: "50%",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    padding: "5px",
    backgroundColor: backgroundColor,
  };
};

export const getIconType = (iconName) => {
  switch (iconName) {
    case "cartIcon":
      return iconCart;
    case "trashIcon":
      return iconTrash;
    case "helpIcon":
      return iconHelp;
    case "settingsIcon":
      return iconSettings;
    case "pencilIcon":
      return iconPencil;
    default:
      return null;
  }
};

export const getIconState = (iconName, currentState, iconStateMap) => {
  if (!iconStateMap[iconName]) throw new Error("Icon not found");
  const iconState = iconStateMap[iconName];
  switch (iconName) {
    case "cartIcon":
    case "helpIcon":
    case "settingsIcon":
    case "pencilIcon":
      if (currentState === "clicked") {
        return iconState.clicked;
      } else if (currentState === "enter") {
        return iconState.hover1;
      }
      break;
    case "trashIcon":
      if (currentState === "clicked") {
        return iconState.clicked;
      } else if (currentState === "enter") {
        return iconState.hover2;
      }
      break;
    default:
      return null;
  }
};
