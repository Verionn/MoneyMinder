import "./App.css";
import "boxicons";
import lottie from "lottie-web";
import { defineElement } from "@lordicon/element";
import { DarkModeProviderCOntext } from "./components/DarkModeContext/DarkModeContext";
import NewMainContainer from "./pages/NewMainContainer/NewMainContainer";
import DataProvider from "../src/components/context/DataProvider"
function App() {
  return (
    <div className="App">
      <DarkModeProviderCOntext>
        <DataProvider>
        <NewMainContainer></NewMainContainer>
        </DataProvider>
      </DarkModeProviderCOntext>

    </div>
  );
}

export default App;
