// NavigationComponent.js
import React, { useEffect } from 'react';

const NavigationComponent = ({ children }) => {
  useEffect(() => {
    const handlePopstate = (event) => {
      // Handle the popstate event here
      console.log('Browser history changed:', event.state);
    };

    window.addEventListener('popstate', handlePopstate);

    return () => {
      window.removeEventListener('popstate', handlePopstate);
    };
  }, []);

  return <>{children}</>;
};

export default NavigationComponent;
