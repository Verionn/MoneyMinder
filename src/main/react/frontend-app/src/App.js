
import "./App.css";
import "boxicons";
import SideBarContainer from "./pages/SideBarContainer/SideBarContainer";
import MainContainer from "./pages/MainContainer/MainContainer";
import { DarkModeProvider } from "./components/DarkModeContext/DarkModeContext";

function App() {
  return (
    <div className="App">
      <DarkModeProvider>
        <SideBarContainer></SideBarContainer>
        <div className="verticalLine"></div>
        <MainContainer></MainContainer>
      </DarkModeProvider>
    </div>
  );
}

export default App;
