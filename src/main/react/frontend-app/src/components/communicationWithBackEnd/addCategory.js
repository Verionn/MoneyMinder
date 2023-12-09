

const AddCategory = ({ categoriName }) => {
 



  const handleCreateNewCategory = async (categoriName) => {
    try {
      // Send a request to create a new list
        const response = await fetch("http://localhost:8080/categories", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ name: categoriName }),
        });

        if (!response.ok) {
          throw new Error("Failed to create a new list");
        }

        // Close the modal after creating the new list
      
        // Reload the page to fetch the updated data
        window.location.reload(true);
     

      // Optionally, you can perform additional actions after creating the list
      // ...
    } catch (error) {
      console.error("Error creating a new list:", error);
    }
  };

  handleCreateNewCategory(categoriName);
};

 
export default AddCategory;
