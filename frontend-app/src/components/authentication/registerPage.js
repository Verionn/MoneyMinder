import DarkModeButton from "../sharedComponents/darkModeButton/darkModeButton";
import { MoonIcon } from "../sharedComponents/icons/svgIcons";
import { SunIcon } from "../sharedComponents/icons/svgIcons";
import { handleAuthentication } from "../../utils/functions/authentication";
import React, { useState } from "react";
import { appInfo } from "../../utils/data/appInfo";
import {
  EnvelopeIcon,
  LockIcon,
  UserIcon,
  ArrowBackIcon,
} from "../sharedComponents/icons/svgIcons";
import "./auth.css";
import { Styles } from "./styles";
import { useContextElements } from "../../utils/hooks/customHooks";

const RegisterPage = () => {
  const { isDarkMode, updateLoginType } = useContextElements();
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    await handleAuthentication(
      { name, email, password },
      "http://localhost:8080/users/register"
    );
  };
  const style = Styles({ darkMode: isDarkMode });

  const handleGoBack = () => {
    updateLoginType("/login");
  };
  return (
    <div className="RegisterContainer">
      <div className="darkmode">
        {" "}
        <DarkModeButton IconDark={MoonIcon} IconLight={SunIcon} />
      </div>
      <p
        onClick={handleGoBack}
        style={{ cursor: "pointer" }}
        className="GoBack"
      >
        {" "}
        <ArrowBackIcon  style={{ ...style.leftArrow }}  /> Go back to Login Page
      </p>
      <h1>{appInfo.name} Register Page</h1>

      <form onSubmit={handleSubmit} className="registerForm">
        <div className="inputFieldContainer">
          <div className="labelField">
            <UserIcon style={{ ...style.iconFill }} />
          </div>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            className="inputField"
            placeholder="name"
          />
        </div>
        <div className="inputFieldContainer">
          <div className="labelField">
            <EnvelopeIcon style={{ ...style.iconFill }} />
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
            <LockIcon style={{ ...style.iconFill }} />
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
          Register
        </button>
      </form>
    </div>
  );
};

export default RegisterPage;
