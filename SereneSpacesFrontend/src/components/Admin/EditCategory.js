import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

const EditCategory = () => {
  const { id } = useParams(); // Get category id from URL parameters
  const navigate = useNavigate(); // To navigate after successful update

  const [category, setCategory] = useState({
    categoryName: "", // Initialize with categoryName (lowercase)
  });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true); // Add loading state

  // Get JWT token from localStorage
  const token = localStorage.getItem("token");
  const headers = { Authorization: `Bearer ${token}` };

  // Fetch category details by ID
  useEffect(() => {
    axios
      .get(`http://localhost:8080/category/getcategory/${id}`, { headers })
      .then((response) => {
        console.log("Fetched Category Data:", response.data); // Verify the response
        setCategory({
          categoryName: response.data.categoryName || "", // Ensure categoryName is used here
        });
        setLoading(false); // Stop loading after data is fetched
      })
      .catch((error) => {
        console.error("Error fetching category details:", error);
        setError("Error fetching category details");
        setLoading(false); // Stop loading in case of error
      });
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCategory((prevCategory) => ({
      ...prevCategory,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .put(`http://localhost:8080/category/updatecategory/${id}`, category, { headers })
      .then((response) => {
        alert("Category updated successfully!");
        navigate("/manage-category");
      })
      .catch((error) => {
        console.error("Error updating category:", error);
        setError("Error updating category");
      });
  };

  // If loading, show a loading message
  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div className="container mt-4">
      <h2>Edit Category</h2>
      {error && <div className="alert alert-danger">{error}</div>}
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Category Name</label>
          <input
            type="text"
            className="form-control"
            name="categoryName" // Use categoryName here as well
            value={category.categoryName || ""} // Bind categoryName to value
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Save Changes
        </button>
      </form>
    </div>
  );
};

export default EditCategory;
