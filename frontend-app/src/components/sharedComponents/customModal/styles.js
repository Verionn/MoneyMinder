

export const styles = ({darkMode})=>({
    header:{
        backgroundColor: darkMode ? "var(--dark-theme-app-bar)" : "var(--light-theme-app-bar)",
        
    },
    closeButton:{
        color: darkMode?"white":"black"
    },
    body:{
        backgroundColor: darkMode ? "var(--dark-theme-background)" : "var(--light-theme-background)",
    },
    footer:{
        backgroundColor: darkMode ? "var(--dark-theme-app-bar)" : "var(--light-theme-app-bar)",
    }
})