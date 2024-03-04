export const Styles = ({ darkMode, windowWidth }) => ({
  root: {
    backgroundColor: darkMode
      ? "var(--dark-theme-background)"
      : "var(--light-theme-background)",
    color: darkMode ? "white" : "black",
  },
  sideBar: {
    display: windowWidth > 768 ? "block" : "none",
  },
});
