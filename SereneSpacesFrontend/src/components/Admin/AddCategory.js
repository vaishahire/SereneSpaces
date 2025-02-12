import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const AddCategory = () => {
  const [categoryName, setCategoryName] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setCategoryName(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
    if (!categoryName) {
      setError("Category name is required.");
      return;
    }

    // Get JWT token from localStorage
    const token = localStorage.getItem("token");
    const headers = { Authorization: `Bearer ${token}` };

    // Prepare category object
    const newCategory = {
      categoryName,
    };

    // Send POST request to add the category with authorization header
    axios
      .post("http://localhost:8080/category/addcategory", newCategory, { headers })
      .then((response) => {
        alert("Category added successfully!");
        navigate("/manage-category"); // Redirect to ManageCategory after successful addition
      })
      .catch((error) => {
        console.error("Error adding category:", error);
        setError("Error adding category. Please try again.");
      });
  };

  return (
    <div className="container mt-4">
      <h2>Add Category</h2>
      {error && <div className="alert alert-danger">{error}</div>}
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Category Name</label>
          <input
            type="text"
            className="form-control"
            value={categoryName}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Add Category
        </button>
      </form>
    </div>
  );
};

export default AddCategory;
