import SideBar from "../sideBar/sideBar";
import "./Body.css";
import { appInfo, loginBtn } from "../../utils/datas/appInfo";
import { useContextElements } from "../../utils/hooks/customHooks";
import { Styles } from "./styles";
const Body = () => {
  const { darkMode } = useContextElements();
  console.log(darkMode);
  const styles = Styles({ darkMode });

  return (
    <div className="appBody" style={{ ...styles.root }}>
      <SideBar appInfo={appInfo} login={loginBtn} />
      <h1>Body</h1>
    </div>
  );
};

export default Body;
