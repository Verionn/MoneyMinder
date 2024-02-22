import { useState, useEffect } from "react";

export function setFonSize(tag, isGoodTag) {
  if (!isGoodTag) return "inherit";

  switch (tag) {
    case "h1":
      return "2.5rem";
    case "h2":
      return "2rem";
    case "h3":
      return "1.75rem";
    case "h4":
      return "1.5rem";
    case "h5":
      return "1.25rem";
    case "h6":
      return "1rem";
    case "p":
      return "1rem";
    case "span":
      return "1rem";
    case "div":
      return "1rem";
    default:
      return "inherit";
  }
}

export const useLocalStorageState = (key, initialValue) => {
  const storedValue = JSON.parse(localStorage.getItem(key)) || initialValue;
  const [value, setValue] = useState(storedValue);

  useEffect(() => {
    const storedItem = localStorage.getItem(key);
    if (storedItem !== null) {
      setValue(JSON.parse(storedItem));
    }
  }, [key]);

  useEffect(() => {
    localStorage.setItem(key, JSON.stringify(value));
  }, [key, value]);

  return [value, setValue];
};

export function getActiveTitle(activeSection, sectionList) {
  let title = "No Title";
 sectionList.forEach((section) => {
 
    if (section.id === activeSection) {
      title =  section.title;
    }
  });
  
  return title;

}
