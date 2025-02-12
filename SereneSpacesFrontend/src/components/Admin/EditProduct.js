import { useState, useEffect } from "react";  // Import React hooks
import axios from "axios";  // Import axios for HTTP requests
import { useParams, useNavigate } from "react-router-dom";  // Import hooks for routing

const EditProduct = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [product, setProduct] = useState({
    prodName: "",
    prodPrice: "",
    prodQty: "",
    prodDescp: "",
    categoryId: "", // Update to store categoryId instead of category object
  });
  const [categories, setCategories] = useState([]);
  const [error, setError] = useState("");

  const token = localStorage.getItem("token");  // Get JWT token from localStorage
  const headers = { Authorization: `Bearer ${token}` }; // Authorization headers

  // Fetch product details by ID
  useEffect(() => {
    axios
      .get(`http://localhost:8080/products/getproductbyid/${id}`, { headers })
      .then((response) => {
        setProduct({
          ...response.data,
          categoryId: response.data.category.categoryId, // Set categoryId from the product response
        });
      })
      .catch((error) => {
        console.error("Error fetching product details:", error);
        setError("Error fetching product details");
      });
  }, [id]);

  // Fetch categories list
  useEffect(() => {
    axios
      .get("http://localhost:8080/category/getcategories", { headers })
      .then((response) => {
        setCategories(response.data);
      })
      .catch((error) => {
        console.error("Error fetching categories:", error);
        setError("Error fetching categories");
      });
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct((prevProduct) => ({
      ...prevProduct,
      [name]: value,
    }));
  };

  const handleCategoryChange = (e) => {
    const selectedCategoryId = e.target.value;
    setProduct((prevProduct) => ({
      ...prevProduct,
      categoryId: selectedCategoryId, // Store categoryId instead of category object
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Product data being sent:", product);

    axios
      .put(`http://localhost:8080/products/updateproduct/${id}`, product, { headers })
      .then((response) => {
        alert("Product updated successfully!");
        navigate("/manage-products");
      })
      .catch((error) => {
        console.error("Error updating product:", error);
        setError("Error updating product");
      });
  };

  return (
    <div className="container mt-4">
      <h2>Edit Product</h2>
      {error && <div className="alert alert-danger">{error}</div>}
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Product Name</label>
          <input
            type="text"
            className="form-control"
            name="prodName"
            value={product.prodName}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Price</label>
          <input
            type="number"
            className="form-control"
            name="prodPrice"
            value={product.prodPrice}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Quantity</label>
          <input
            type="number"
            className="form-control"
            name="prodQty"
            value={product.prodQty}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Description</label>
          <textarea
            className="form-control"
            name="prodDescp"
            value={product.prodDescp}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Category</label>
          <select
            className="form-select"
            name="categoryId"
            value={product.categoryId}
            onChange={handleCategoryChange}
            required
          >
            <option value="">Select Category</option>
            {categories.length > 0 ? (
              categories.map((category) => (
                <option key={category.categoryId} value={category.categoryId}>
                  {category.categoryName}
                </option>
              ))
            ) : (
              <option disabled>No categories available</option>
            )}
          </select>
        </div>
        <button type="submit" className="btn btn-primary">
          Save Changes
        </button>
      </form>
    </div>
  );
};

export default EditProduct;
