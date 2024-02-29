export const Styles = ({ darkMode, windowWidth }) => ({
  ItemsContainer: {
    flexDirection: windowWidth > 900 ? "row" : "column",
    alignItems: windowWidth > 900 ? "flex-start" : "center",
    height: '100%',
    overflowY: "scroll",
    paddingBottom: 10,
  },

  ItemBox: {
    padding: 10,
  },

  ItemBoxContainer: {
    display: "flex",
    width: "100%",
    border: "1px solid #e0e0e0",
    flexDirection: windowWidth > 1000 ? "row" : "column",
    alignItems: windowWidth > 1000 ? "flex-start" :"center",
    justifyContent: "space-between",
    gap : 20,
    padding: 10,
  },
});
