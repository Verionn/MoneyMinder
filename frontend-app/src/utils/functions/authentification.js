export const handleLogout = () => {
  localStorage.removeItem("token");
  window.location.reload();
};

 export const handleAuthentification = async (credentials,url) => {
  try {
    const response = await fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(credentials),
    });
    const data = await response.json();
    console.log(data);
    if (data.token) {
      localStorage.setItem("token", data.token);
        window.location.reload(); 
    }
  } catch (error) {
    console.error("Login failed:", error);
    return false;
  }
  return true;
};
