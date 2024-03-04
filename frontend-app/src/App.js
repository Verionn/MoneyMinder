import "./App.css";
import Body from "./components/Body/Body";
import { BrowserRouter as Router } from "react-router-dom";
import { ContextProvider } from "./utils/Context/Context";
function App() {
  return (
    <div className="App">
      <Router>
        <ContextProvider>
          <Body />
        </ContextProvider>
      </Router>
    </div>
  );
}

export default App;
