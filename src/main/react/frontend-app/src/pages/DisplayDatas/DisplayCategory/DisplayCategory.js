import React from "react";
import GetCategories from "../../../components/communicationWithServer/GetCotegories";
import "boxicons";
import lottie from "lottie-web";
import { defineElement } from "@lordicon/element";
import AddNewItem from "../../../components/addNewItems/addNewItem";
import * as HandleDataRequest from "../../../components/communicationWithServer/HandleDataRequest"
// Define "lord-icon" custom element with default properties
defineElement(lottie.loadAnimation);

const GetDatasFromItems = ({ CategoryID }) => {
  const apiUrl = `http://localhost:8080/categories`;

  let { categories, loading, error } = GetCategories({ apiUrl });
  HandleDataRequest.DataRequest("categories", "GET", apiUrl, null);
  
  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }

  if (CategoryID === -1) return <AddNewItem categories={categories} />;
  if (categories[CategoryID - 1].name === "Food") {
    return (
      <p>
        <lord-icon
          src="https://cdn.lordicon.com/sxrnyajs.json"
          trigger="hover"
          style={{ width: "60px", height: "60px" }}
        ></lord-icon>
      </p>
    );
  } else if (categories[CategoryID - 1].name === "Sweets") {
    return (
      <p>
        <lord-icon
          src="https://cdn.lordicon.com/joucdxcj.json"
          trigger="hover"
          style={{ width: "50px", height: "50px" }}
        ></lord-icon>
      </p>
    );
  } else if (categories[CategoryID - 1].name === "") {
    return (
      <p>
        <lord-icon
          src="https://cdn.lordicon.com/eiekfffz.json"
          trigger="hover"
          colors="primary:#121331,secondary:#002a4e,tertiary:#865400"
          style={{ width: "50px", height: "50px" }}
        ></lord-icon>
      </p>
    );
  } else {
    return <p>{categories[CategoryID - 1].name}</p>;
  }
};

export default GetDatasFromItems;
