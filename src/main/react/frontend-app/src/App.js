
import "./App.css";
import "boxicons";
import SideBarContainer from "./pages/SideBarContainer";
import MainContainer from "./pages/MainContainer";
import { DarkModeProvider } from "./pages/components/DarkModeContext";

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
