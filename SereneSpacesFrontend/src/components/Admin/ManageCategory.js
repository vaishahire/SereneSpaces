import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";

const ManageCategory = () => {
  const [categories, setCategories] = useState([]);
  const token = localStorage.getItem("token"); // Get token from localStorage

  useEffect(() => {
    if (!token) {
      console.error("Unauthorized access! Token not found.");
      return;
    }

    axios
      .get("http://localhost:8080/category/getcategories", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        console.log("Categories fetched:", response.data);
        setCategories(response.data);
      })
      .catch((error) => {
        console.error("Error fetching categories:", error);
      });
  }, [token]);

  const handleDelete = (categoryId) => {
    if (!token) {
      alert("Unauthorized! Please log in.");
      return;
    }

    if (window.confirm("Are you sure you want to delete this category?")) {
      axios
        .delete(`http://localhost:8080/category/deletecategory/${categoryId}`, {
          headers: { Authorization: `Bearer ${token}` },
        })
        .then(() => {
          setCategories(categories.filter((category) => category.categoryId !== categoryId));
        })
        .catch((error) => console.error("Error deleting category:", error));
    }
  };

  return (
    <div className="container mt-4">
      <h2>Manage Categories</h2>
      <Link to="/add-category" className="btn btn-success mb-3">+ Add Category</Link>
      <table className="table table-bordered">
        <thead className="table-dark">
          <tr>
            <th>ID</th>
            <th>Category Name</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {categories.length > 0 ? (
            categories.map((category) => (
              <tr key={category.categoryId}>
                <td>{category.categoryId}</td>
                <td>{category.categoryName}</td>
                <td>
                  <Link to={`/edit-category/${category.categoryId}`} className="btn btn-warning btn-sm me-2">
                    Edit
                  </Link>
                  <button className="btn btn-danger btn-sm" onClick={() => handleDelete(category.categoryId)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="3" className="text-center">No categories found</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ManageCategory;
