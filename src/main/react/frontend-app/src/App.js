import logo from './logo.svg';
import './App.css';
import 'boxicons'
import SideBarContainer from "./pages/sideBarContainer";
import MainContainer from "./pages/MainContainer";

function App() {
  return (
    <div className="App">
        <SideBarContainer></SideBarContainer>
        <div className="verticalLine"></div>
        <MainContainer></MainContainer>
    </div>
  );
}

export default App;
