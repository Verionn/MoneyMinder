import "./App.css";
import "boxicons";

import { DarkModeProvider } from "./components/DarkModeContext/DarkModeContext";
import NewMainContainer from "./pages/NewMainContainer/NewMainContainer";
function App() {
  return (
    <div className="App">
      <DarkModeProvider>
       {/* <SideBarContainer></SideBarContainer>
        <div className="verticalLine"></div>
        <MainContainer></MainContainer>*/} 
        <NewMainContainer></NewMainContainer>
      </DarkModeProvider>

    </div>
  );
}

export default App;
