
export const Styles = ({ darkMode }) => ({
    
    root: {
      backgroundColor: darkMode ? 'var(--dark-theme-background)' : 'var(--light-theme-background)',
        color: darkMode ? 'white' : 'black',
    }
  });
  