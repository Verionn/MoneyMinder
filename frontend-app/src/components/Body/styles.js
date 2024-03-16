export const Styles = ({ darkMode, windowWidth }) => ({
  root: {
    backgroundColor: darkMode
      ? "var(--dark-theme-background)"
      : "var(--light-theme-app-bar)",
    color: darkMode ? "white" : "black",
  },
  sideBar: {
    display: windowWidth > 768 ? "block" : "none",
  },
});
