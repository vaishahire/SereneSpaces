import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

function Product() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategories, setSelectedCategories] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [sortOrder, setSortOrder] = useState(''); // Sorting order
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [favorites, setFavorites] = useState([]);

  const userId = localStorage.getItem('userId');
  const token = localStorage.getItem('token');

  const headers = { Authorization: `Bearer ${token}` };

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await axios.get('http://localhost:8080/category/getcategories', { headers });
        setCategories(response.data);
      } catch (err) {
        console.error('Error fetching categories:', err);
      }
    };

    fetchCategories();
  }, []);

  useEffect(() => {
    const fetchProducts = async () => {
      setLoading(true);
      try {
        let response;
        if (selectedCategories.length === 0) {
          response = await axios.get('http://localhost:8080/products/getallproduct', { headers });
        } else {
          const categoryQuery = selectedCategories.join(',');
          response = await axios.get(`http://localhost:8080/products/getproductsbycategory?categoryIds=${categoryQuery}`, { headers });
        }
        setProducts(response.data);
      } catch (err) {
        setError('Failed to load products');
        console.error('Error fetching products:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, [selectedCategories]);

  useEffect(() => {
    const fetchFavorites = async () => {
      if (userId && token) {
        try {
          const response = await axios.get(`http://localhost:8080/favourites/getUserFavorites/${userId}`, { headers });
          setFavorites(response.data.map(fav => fav.products.prodId));
        } catch (err) {
          console.error('Error fetching favorites:', err);
        }
      }
    };

    fetchFavorites();
  }, [userId, token]);

  const toggleFavorite = async (productId) => {
    if (!userId || !token) {
      alert('You must be logged in to favorite a product.');
      return;
    }

    const isFavorite = favorites.includes(productId);
    const updatedFavorites = isFavorite
      ? favorites.filter(fav => fav !== productId)
      : [...favorites, productId];

    setFavorites(updatedFavorites);

    try {
      await axios.post('http://localhost:8080/favourites/togglefavourite', null, {
        params: { productId, userId },
        headers,
      });
    } catch (err) {
      console.error('Error toggling favorite:', err);
      alert('Failed to update favorites. Try again later.');
      setFavorites(favorites); // Rollback on failure
    }
  };

  const handleCategoryChange = (categoryId) => {
    setSelectedCategories((prevSelected) =>
      prevSelected.includes(categoryId)
        ? prevSelected.filter((id) => id !== categoryId)
        : [...prevSelected, categoryId]
    );
  };

  const handleSortChange = (e) => {
    setSortOrder(e.target.value);
  };

  const filteredProducts = products
    .filter((product) =>
      product.prodName.toLowerCase().includes(searchTerm.toLowerCase())
    )
    .sort((a, b) => {
      if (sortOrder === 'lowToHigh') return a.prodPrice - b.prodPrice;
      if (sortOrder === 'highToLow') return b.prodPrice - a.prodPrice;
      return 0;
    });

  if (loading) return <p className="text-center">Loading products...</p>;
  if (error) return <p className="text-danger text-center">{error}</p>;

  return (
    <section className="py-5">
      <div className="container">
        <h2 className="text-center mb-4">Our Products</h2>

        <div className="row">
          {/* Left Sidebar: Filters */}
          <div className="col-md-3">
            <div className="border p-3 rounded shadow-sm">
              {/* Search Bar */}
              <div className="mb-4">
                <h5>Search Products</h5>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search..."
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                />
              </div>

              {/* Category Filter */}
              <div className="mb-4">
                <h5>Filter by Category</h5>
                {categories.map((category) => (
                  <div key={category.categoryId} className="form-check">
                    <input
                      type="checkbox"
                      id={`category-${category.categoryId}`}
                      className="form-check-input"
                      checked={selectedCategories.includes(category.categoryId)}
                      onChange={() => handleCategoryChange(category.categoryId)}
                    />
                    <label className="form-check-label" htmlFor={`category-${category.categoryId}`}>
                      {category.categoryName}
                    </label>
                  </div>
                ))}
              </div>

              {/* Sort by Price */}
              <div>
                <h5>Sort by Price</h5>
                <select className="form-select" value={sortOrder} onChange={handleSortChange}>
                  <option value="">Default</option>
                  <option value="lowToHigh">Low to High</option>
                  <option value="highToLow">High to Low</option>
                </select>
              </div>
            </div>
          </div>

          {/* Right: Product Listings */}
          <div className="col-md-9">
            <div className="row">
              {filteredProducts.length > 0 ? (
                filteredProducts.map((product) => (
                  <div className="col-sm-12 col-md-6 col-lg-4 mb-4" key={product.prodId}>
                    <div className="card shadow-sm h-100">
                      <img 
                        className="card-img-top" 
                        src={`data:image/jpeg;base64,${product.prodImage}`} 
                        alt={product.prodName} 
                        style={{ height: '200px', objectFit: 'cover' }} 
                      />
                      <div className="card-body d-flex flex-column">
                        <h5 className="card-title">{product.prodName}</h5>
                        <p className="card-text flex-grow-1">{product.prodDescp}</p>
                        <p className="card-text text-primary font-weight-bold">Price: Rs.{product.prodPrice}</p>
                        
                        <div className="d-flex justify-content-between align-items-center mt-auto">
                          <Link to={`/productdetails/${product.prodId}`} className="btn btn-primary">
                            View Product
                          </Link>
                          <button 
                            className="btn btn-link p-0" 
                            onClick={() => toggleFavorite(product.prodId)}
                          >
                            {favorites.includes(product.prodId) ? (
                              <i className="bi bi-heart-fill text-danger" style={{ fontSize: '1.5rem'}} />
                            ) : (
                              <i className="bi bi-heart" style={{ fontSize: '1.5rem'}} />
                            )}
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                ))
              ) : (
                <p className="text-center text-muted">No products found.</p>
              )}
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

export default Product;
