import DarkModeButton from "../sharedComponents/darkModeButton/darkModeButton";
import { MoonIcon } from "../sharedComponents/icons/svgIcons";
import { SunIcon } from "../sharedComponents/icons/svgIcons";

const Authentication = () => {
  return (
    <div>
      <h1>Login Page</h1>
        <DarkModeButton IconDark={MoonIcon} IconLight={SunIcon} />
    </div>
  );
};

export default Authentication;
