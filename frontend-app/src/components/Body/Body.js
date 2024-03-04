import SideBar from "../sideBar/sideBar";
import "./Body.css";
import { appInfo, loginBtn, Credit } from "../../utils/datas/appInfo";
import { useContextElements } from "../../utils/hooks/customHooks";
import { Styles } from "./styles";
import Section from "../sections/section";
import { useEffect, useState } from "react";
import RenderRoutes from "../../utils/router/Routes";
import { LoginPage } from "../../utils/datas/appInfo";
const Body = () => {
  const { isDarkMode, handleResize, windowWidth } = useContextElements();
  const [isLogin, setIsLogin] = useState(false);

  useEffect(() => {
    window.addEventListener("resize", handleResize);
    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, [handleResize]);

  const styles = Styles({ darkMode: isDarkMode, windowWidth: windowWidth });

  return (
    <div className="appStates">
      {isLogin ? (
        <div className="appBody" style={{ ...styles.root }}>
          <SideBar appInfo={appInfo} login={loginBtn} Credit={Credit} />
          <Section />
        </div>
      ) : (
        <RenderRoutes routes={LoginPage} />
      )}
    </div>
  );
};

export default Body;