import ShoppingLists from "../../components/shoppingLists/shoppingLists";
import RegisterPage from "../../components/authentification/registerPage";
import LoginPage from "../../components/authentification/LoginPage";

export const appInfo = {
  name: "MoneyMinder",
  logo: require("../../ressources/logo.png"),
};

let someText = "This is a text";

export const Credit = (
  <>
    2023 © {appInfo.name} - <br />
    All rights reserved
  </>
);

export const loginBtn = (
  <>
    Login {">"}
    <br />
    Access your lists from any device
  </>
);

export const AuthObject = [
  {
    title: "Login",
    id: "login",
    icon: "loginIcon",
    path: "/login",
    element: <LoginPage />,
  },
  {
    title: "Sign Up",
    id: "signup",
    icon: "signupIcon",
    path: "/signup",
    element: <RegisterPage />,
  },
];

export const pageSections = [
  {
    title: "Shopping List",
    id: "shopping-list",
    icon: "cartIcon",
    path: "/shopping-list",
    element: <ShoppingLists />,
  },
  {
    title: "Trash",
    id: "trash",
    icon: "trashIcon",
    path: "/trash",
    element: someText,
  },
  {
    title: "Help",
    id: "help",
    icon: "helpIcon",
    path: "/Help",
    element: someText,
  },
  {
    title: "Settings",
    id: "settings",
    icon: "settingsIcon",
    path: "/settings",
    element: someText,
  },
];
