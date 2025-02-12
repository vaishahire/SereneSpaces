import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const ManageProducts = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const token = localStorage.getItem("token");

  useEffect(() => {
    if (!token) {
      setError("Unauthorized access. Please log in.");
      setLoading(false);
      return;
    }

    axios.get("http://localhost:8080/products/getallproduct", {
      headers: { Authorization: `Bearer ${token}` }, 
    })
    .then((response) => {
      setProducts(response.data);
      setLoading(false);
    })
    .catch((error) => {
      console.error("Error fetching products:", error);
      setError("Failed to fetch products. Please try again later.");
      setLoading(false);
    });
  }, [token]);

  const handleDelete = (prodId) => {
    if (!token) {
      alert("Unauthorized! Please log in.");
      return;
    }

    if (window.confirm("Are you sure you want to delete this product?")) {
      axios.delete(`http://localhost:8080/products/deleteproduct/${prodId}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(() => {
        setProducts(products.filter((product) => product.prodId !== prodId));
      })
      .catch((error) => console.error("Error deleting product:", error));
    }
  };

  if (loading) return <p className="text-center">Loading products...</p>;
  if (error) return <p className="alert alert-danger text-center">{error}</p>;

  return (
    <section className="py-5">
      <div className="container">
        <h2 className="text-center mb-4">Manage Products</h2>

        {products.length === 0 ? (
          <p className="text-center">
            No products found. <Link to="/add-product">Add a new product</Link>
          </p>
        ) : (
          <div>
            <div className="d-flex justify-content-end mb-3">
              <Link to="/add-product" className="btn btn-success">+ Add Product</Link>
            </div>

            <div className="table-responsive">
              <table className="table table-bordered">
                <thead className="table-dark">
                  <tr>
                    <th>Sr. No</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {products.map((product, index) => (
                    <tr key={product.prodId}>
                      <td>{index + 1}</td>
                      <td>{product.prodName}</td>
                      <td>Rs. {product.prodPrice}</td>
                      <td>{product.prodQty}</td>
                      <td>{product.prodDescp}</td>
                      <td>{product.category.categoryName}</td>
                      <td>
                        <Link to={`/edit-product/${product.prodId}`} className="btn btn-warning btn-sm me-2">
                          Edit
                        </Link>
                        <button className="btn btn-danger btn-sm" onClick={() => handleDelete(product.prodId)}>
                          Delete
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        )}
      </div>
    </section>
  );
};

export default ManageProducts;
