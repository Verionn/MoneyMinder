export const Styles = ({ darMokde }) => ({
  singleList: {
    backgroundColor: darMokde
      ? "var(--dark-theme-surface)"
      : "var(--light-theme-gray-scale)",
    border: `1px solid ${darMokde ? "black" : "#e0e0e0"}`,
  },
  icons: {
    fill: "var(--secondary-color)",
  },
  listExpanded: {
    backgroundColor: darMokde
      ? "var(--dark-theme-hover)"
      : "var(--light-theme-gray-scale)",
  },
});
