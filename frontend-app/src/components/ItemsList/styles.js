export const Styles = ({ darkMode, windowWidth }) => ({
  ItemsContainer: {
    flexDirection: windowWidth > 900 ? "row" : "column",
    alignItems: windowWidth > 900 ? "flex-start" : "center",
    border: "1px solid yellow",
    height: '100%',
    overflowY: "scroll",
    paddingBottom: 10,
  },

  ItemBox: {
    padding: 10,
  },
});
