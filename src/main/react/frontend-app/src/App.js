import "./App.css";
import "boxicons";
import lottie from "lottie-web";
import { defineElement } from "@lordicon/element";
import {
  DarkModeProviderCOntext,
  ListArrayProviderContext,
  ViewListProviderContext,
} from "./components/Context/Contexts";
import NewMainContainer from "./pages/NewMainContainer/NewMainContainer";
import { NotificationContainer } from "react-notifications";
import "react-notifications/lib/notifications.css";
function App() {
  return (
    <div className="App">
      <DarkModeProviderCOntext>
        <ViewListProviderContext>
        <ListArrayProviderContext>
          <NewMainContainer></NewMainContainer>
        </ListArrayProviderContext>
        </ViewListProviderContext>
        <NotificationContainer />
      </DarkModeProviderCOntext>
    </div>
  );
}

export default App;
