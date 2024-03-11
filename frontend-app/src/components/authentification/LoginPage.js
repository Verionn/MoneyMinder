import DarkModeButton from "../sharedComponents/darkModeButton/darkModeButton";
import { MoonIcon } from "../sharedComponents/icons/svgIcons";
import { SunIcon } from "../sharedComponents/icons/svgIcons";
import { handleAuthentification } from "../../utils/functions/authentification";
import React, { useState } from "react";
import { appInfo } from "../../utils/datas/appInfo";
import { UserIcon } from "../sharedComponents/icons/svgIcons";
import { EnveloppeIcon } from "../sharedComponents/icons/svgIcons";
import "./auth.css";

import { useContextElements } from "../../utils/hooks/customHooks";
const LoginPage = () => {
  const { updateLoginType } = useContextElements();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    await handleAuthentification(
      { email, password },
      "http://localhost:8080/users/login"
    );
  };

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
            <EnveloppeIcon />
          </div>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="inputField"
            placeholder="email"
          />
        </div>
        <div className="inputFieldContainer">
          <div className="labelField">
            <UserIcon />
          </div>{" "}
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="inputField"
            placeholder="password"
          />
        </div>
        <button type="submit" className="RegisterButton">
          Log in
        </button>
        <p>
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
