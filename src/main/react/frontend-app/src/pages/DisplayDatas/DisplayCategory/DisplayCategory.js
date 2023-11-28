import React from 'react';
import GetCategories from '../../../components/communicationWithBackEnd/GetCotegories';
import "boxicons";


const GetDatasFromItems = ({ CategoryID,}) => {
  const apiUrl = `http://localhost:8080/categories`;


  let { categories, loading, error } = GetCategories({ apiUrl });
  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }

if(categories[CategoryID-1].name==="Food")return <p><box-icon type='solid' name='bowl-hot' color={"green"}></box-icon></p>
else if(categories[CategoryID-1].name==="Sweets")return <p><box-icon name='happy-beaming'></box-icon></p>
else return <p>{categories[CategoryID-1].name}</p>
  
  
};

export default GetDatasFromItems;

