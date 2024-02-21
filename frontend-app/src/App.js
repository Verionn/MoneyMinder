import "./App.css";
import Body from "./components/Body/Body";
import { BrowserRouter as Router } from "react-router-dom";
function App() {
  return (
    <div className="App">
      <Router>
      <Body />
      </Router>
    </div>
  );
}

export default App;
