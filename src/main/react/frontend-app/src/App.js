import "./App.css";
import "boxicons";
import lottie from "lottie-web";
import { defineElement } from "@lordicon/element";
import { DarkModeProviderCOntext } from "./components/DarkModeContext/DarkModeContext";
import NewMainContainer from "./pages/NewMainContainer/NewMainContainer";
import DataProvider from "../src/components/context/DataProvider";
import { NotificationContainer } from "react-notifications";
import "react-notifications/lib/notifications.css";
function App() {
  return (
    <div className="App">
      <DarkModeProviderCOntext>
        <NewMainContainer></NewMainContainer>
        <NotificationContainer />
      </DarkModeProviderCOntext>
    </div>
  );
}

export default App;
