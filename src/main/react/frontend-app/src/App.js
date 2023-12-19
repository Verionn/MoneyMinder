import "./App.css";
import "boxicons";
import lottie from "lottie-web";
import { defineElement } from "@lordicon/element";
import { DarkModeProviderCOntext } from "./components/DarkModeContext/DarkModeContext";
import NewMainContainer from "./pages/NewMainContainer/NewMainContainer";
function App() {
  return (
    <div className="App">
      <DarkModeProviderCOntext>
        <NewMainContainer></NewMainContainer>
      </DarkModeProviderCOntext>

    </div>
  );
}

export default App;
