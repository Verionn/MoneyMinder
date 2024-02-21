export const appInfo = {
  name: "MoneyMinder",
  logo: require("../../ressources/logo.png"),
};

let someText = "This is a text";

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
    icon: "shopping-cart",
    path: "/shopping-list",
    element: someText,
  },
  {
    title: "Trash",
    id: "trash",
    icon: "trash",
    path: "/trash",
    element: someText,
  },
  {
    title:"Help",
    id:"help",
    icon:"info",
    path:"/Help",
    element:someText
    
},
  {
    title: "Settings",
    id: "settings",
    icon: "settings",
    path: "/settings",
    element: someText,
  },
];
