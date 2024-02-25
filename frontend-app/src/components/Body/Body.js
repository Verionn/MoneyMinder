import SideBar from "../sideBar/sideBar";
import "./Body.css";
import { appInfo, loginBtn, Credit } from "../../utils/datas/appInfo";
import { useContextElements } from "../../utils/hooks/customHooks";
import { Styles } from "./styles";
import Section from "../sections/section";
import { useEffect } from "react";
const Body = () => {
  const { isDarkMode,handleResize } = useContextElements();

  useEffect(() => {

    window.addEventListener("resize", handleResize);
    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, [handleResize]);

  const styles = Styles({ darkMode: isDarkMode });

  return (
    <div className="appBody" style={{ ...styles.root }}>
      <SideBar appInfo={appInfo} login={loginBtn} Credit={Credit} />
      <Section />
    </div>
  );
};

export default Body;
