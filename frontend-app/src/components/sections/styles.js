export const Styles = ({ darkMode }) => ({
    
    iconFill: {
        fill:'white',
        cursor: "pointer",  
    },

    headerBackground: {
        backgroundColor: darkMode ? "var(--dark-theme-app-bar)" : "var(--light-theme-app-bar)",
      
    },

  });