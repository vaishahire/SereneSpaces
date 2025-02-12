import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Favourite() {
  const [favouriteItems, setFavouriteItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const userId = localStorage.getItem('userId'); // Get userId from localStorage
  const token = localStorage.getItem('token'); // Get token from localStorage

  useEffect(() => {
    if (!userId || !token) {
      navigate('/login'); // Redirect to login if userId or token is missing
      return;
    }

    axios.get(`http://localhost:8080/favourites/getUserFavorites/${userId}`, {
      headers: { Authorization: `Bearer ${token}` },
    })
      .then((response) => {
        setFavouriteItems(response.data);
        setLoading(false);
      })
      .catch((error) => {
        setError('Error fetching favourite items');
        setLoading(false);
      });
  }, [userId, token, navigate]);

  const handleRemoveFavourite = (favId) => {
    axios.delete(`http://localhost:8080/favourites/deletefavourite/${favId}`, {
      headers: { Authorization: `Bearer ${token}` },
    })
      .then(() => {
        setFavouriteItems(favouriteItems.filter(item => item.favId !== favId));
      })
      .catch((error) => {
        console.error('Error removing item from favourites:', error);
      });
  };

  const handleViewProduct = (productId) => {
    navigate(`/ProductDetails/${productId}`);
  };

  if (loading) return <p>Loading favourite items...</p>;
  if (error) return <p>{error}</p>;

  return (
    <section className="py-5">
      <div className="container">
        <h2 className="text-center mb-4">My Favourites</h2>

        {favouriteItems.length === 0 ? (
          <p>Your favourites list is empty. <a href="/products">Browse Products</a></p>
        ) : (
          <div>
            <div className="table-responsive">
              <table className="table table-bordered">
                <thead>
                  <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  {favouriteItems.map((item) => (
                    <tr key={item.favId}>
                      <td>
                        <div className="d-flex align-items-center">
                          <img 
                            src={`data:image/jpeg;base64,${item.products.prodImage}`} 
                            alt={item.products.prodName} 
                            style={{ width: '60px', height: '60px', objectFit: 'cover', marginRight: '10px' }}
                          />
                          {item.products.prodName}
                        </div>
                      </td>
                      <td>Rs. {item.products.prodPrice}</td>
                      <td>
                        <button className="btn btn-info btn-sm" onClick={() => handleViewProduct(item.products.prodId)}>
                          View Product
                        </button>&nbsp;
                        <button className="btn btn-danger btn-sm ml-2" onClick={() => handleRemoveFavourite(item.favId)}>
                          Remove
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
}

export default Favourite;
