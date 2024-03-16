import DarkModeButton from "../sharedComponents/darkModeButton/darkModeButton";
import { MoonIcon,SunIcon } from "../sharedComponents/icons/svgIcons"

const Settings = () => {

    return (
        <DarkModeButton IconDark={MoonIcon} IconLight={SunIcon} />
    )
};

export default Settings;