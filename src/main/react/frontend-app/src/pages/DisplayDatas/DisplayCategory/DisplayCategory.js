import React from "react";
import GetCategories from "../../../components/communicationWithBackEnd/GetCotegories";
import "boxicons";
import lottie from "lottie-web";
import { defineElement } from "@lordicon/element";

// Define "lord-icon" custom element with default properties
defineElement(lottie.loadAnimation);

const GetDatasFromItems = ({ CategoryID }) => {
  const apiUrl = `http://localhost:8080/categories`;

  let { categories, loading, error } = GetCategories({ apiUrl });

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }

  if (categories[CategoryID - 1].name === "Food") {
    return (
      <p>
        <lord-icon
          src="https://cdn.lordicon.com/sxrnyajs.json"
          trigger="hover"
          style={{ width: "40px", height: "40px" }}
        ></lord-icon>
      </p>
    );
  } else if (categories[CategoryID - 1].name === "Sweets") {
    return (
      <p>
        <lord-icon
          src="https://cdn.lordicon.com/joucdxcj.json"
          trigger="hover"
          style={{ width: "40px", height: "40px" }}
        ></lord-icon>
      </p>
    );
  } else {
    return <p>{categories[CategoryID - 1].name}</p>;
  }
};

export default GetDatasFromItems;
