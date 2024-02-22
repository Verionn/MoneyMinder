import ShoppingLists from "../../components/shoppingLists/shoppingLists";

export const appInfo = {
  name: "MoneyMinder",
  logo: require("../../ressources/logo.png"),
};

let someText = "This is a text";

export const Credit = (
  <>
  2023 © {appInfo.name} - <br />All rights reserved
  </>
);

export const loginBtn = (
  <>
    Login {">"}
    <br />
    Access your lists from any device
  </>
);

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
    title:"Help",
    id:"help",
    icon:"helpIcon",
    path:"/Help",
    element:someText
    
},
  {
    title: "Settings",
    id: "settings",
    icon: "settingsIcon",
    path: "/settings",
    element: someText,
  },
];
