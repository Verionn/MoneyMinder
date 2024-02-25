export const Styles = ({ darkMode, windowWidth }) => ({
  ItemsContainer: {
    flexDirection: windowWidth > 900 ? "row" : "column",
    alignItems: windowWidth > 900 ? "flex-start" : "center",
  },
});
