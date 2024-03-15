import DarkModeButton from "../sharedComponents/darkModeButton/darkModeButton";
import { MoonIcon } from "../sharedComponents/icons/svgIcons";
import { SunIcon } from "../sharedComponents/icons/svgIcons";
import { handleAuthentication } from "../../utils/functions/authentication";
import React, {  useState } from "react";
import { appInfo } from "../../utils/datas/appInfo";
import { EnvelopeIcon, LockIcon } from "../sharedComponents/icons/svgIcons";
import "./auth.css";
import { Styles } from "./styles";
import { useContextElements } from "../../utils/hooks/customHooks";
const LoginPage = () => {
  const { updateLoginType,isDarkMode } = useContextElements();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(true);
  const handleSubmit = async (e) => {
    e.preventDefault();
    const check = await handleAuthentication(
      { email, password },
      "http://localhost:8080/users/login"
    );
    setError(check);
  };
  const style = Styles({ darkMode: isDarkMode });

  return (
    <div className="RegisterContainer">
      <div className="darkmode">
        {" "}
        <DarkModeButton IconDark={MoonIcon} IconLight={SunIcon} />
      </div>
      <h1>{appInfo.name} Login Page</h1>

      <form onSubmit={handleSubmit} className="registerForm">
        <div className="inputFieldContainer">
          <div className="labelField">
            <EnvelopeIcon style={{...style.iconFill}} />
          </div>
          <input
            type="email"
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="inputField"
            placeholder="email"
            autoComplete="email"
          />
        </div>
        <div className="inputFieldContainer">
          <div className="labelField">
            <LockIcon
              style={{...style.iconFill }}
            />
          </div>{" "}
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="inputField"
            placeholder="password"
          />
        </div>
        {!error ? (
          <p className="error">
            You have entered an invalid username or password
          </p>
        ) : null}
        <button type="submit" className="RegisterButton">
          Log in
        </button>
        <p className="noAccount">
          Don't have an account yet ?{" "}
          <div
            onClick={() => updateLoginType("/signup")}
            className="linkButton"
          >
            Register here
          </div>
        </p>
      </form>
    </div>
  );
};

export default LoginPage;
