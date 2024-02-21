export function checkIsSectionActive(sectionId, activeSection) {
  return sectionId === activeSection;
}

export function getSectionStyle(sectionId, activeSection, style, isDarkMode) {
  let flag = checkIsSectionActive(sectionId, activeSection);

  if (flag) {
    if (style === "background") {
      return "var(--primary-color)";
    }
    if (style === "color") {
      return "white";
    }
  } else {
    if (style === "background") {
      return isDarkMode
        ? "var( --dark-theme-app-bar)"
        : "var(--light-theme-app-bar)";
    }
    if (style === "color") {
      return isDarkMode ? "white" : "black";
    }
  }
  return null;
}
