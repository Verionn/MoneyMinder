import SideBar from "../sideBar/sideBar";
import "./Body.css";
import { appInfo, loginBtn, Credit } from "../../utils/datas/appInfo";
import { useContextElements } from "../../utils/hooks/customHooks";
import { Styles } from "./styles";
import Section from "../sections/section";
import { useEffect } from "react";
import RenderRoutes from "../../utils/router/Routes";
import { LoginPage } from "../../utils/datas/appInfo";
import { useNavigate } from "react-router";
const Body = () => {
  const { isDarkMode, handleResize, windowWidth } = useContextElements();
  const Token = localStorage.getItem("token");

  const navigate = useNavigate();

  useEffect(() => {
    window.addEventListener("resize", handleResize);
    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, [handleResize]);

  useEffect(() => {
    if (!Token) {
      navigate("/");
    }
   
  }, [Token, navigate]);

  const styles = Styles({ darkMode: isDarkMode, windowWidth: windowWidth });

  return (
    <div className="appStates" style={{ ...styles.root }}>
      {Token ? (
        <div className="appBody">
          <SideBar appInfo={appInfo} login={loginBtn} Credit={Credit} />
          <Section />
        </div>
      ) : (
        <>
          <RenderRoutes routes={LoginPage} style={{ ...styles.root }} />
        </>
      )}
    </div>
  );
};

export default Body;
