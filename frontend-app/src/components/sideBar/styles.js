export const Styles = ({ Collapsed, darkMode = false }) => ({
  container: {
    width: Collapsed ? "100px" : "230px",
  },
  appInfo: {
    color: darkMode ? "white" : "black",
  },
  customRow: {
    width: Collapsed ? "70%" : "90%",
  },
  root: {
    backgroundColor: darkMode
      ? "var(--dark-theme-app-bar)"
      : "var(--light-theme-app-bar",
  },
  sideBarChevron: {
    height: "60px",
    width: "60px",
    fill: darkMode ? "white" : "black",
  },
  sideBarChevronIcon: {
    borderColor: darkMode ? "white" : "black",
    backgroundColor: darkMode ? "white" : "black",
  },
});
