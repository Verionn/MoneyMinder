import { useContextElements } from "../../../utils/hooks/customHooks";

const DarkModeButton = ({ IconDark, IconLight }) => {
  const { isDarkMode, updateDarkMode } = useContextElements();

  return (
    <div>
      {isDarkMode ? (
        <IconDark onClick={updateDarkMode} style={{fill:'white'}}  />
      ) : (
        <IconLight onClick={updateDarkMode} />
      )}
    </div>
  );
};

export default DarkModeButton;
