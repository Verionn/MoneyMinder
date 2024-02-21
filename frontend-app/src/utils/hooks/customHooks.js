import { useContext } from "react";
import { ContextElements } from "../Context/Context";

export const useContextElements = () => {
    const context = useContext(ContextElements);
    if (!context) {
      throw new Error("useContextElements must be used within a ContextProvider");
    }
    return context;
  };