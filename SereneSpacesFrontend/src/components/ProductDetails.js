// import React, { useEffect, useState } from 'react';
// import axios from 'axios';
// import { useParams, useNavigate } from 'react-router-dom';

// function ProductDetails() {
//   const { prodId } = useParams();
//   const [product, setProduct] = useState(null);
//   const [loading, setLoading] = useState(true);
//   const [error, setError] = useState(null);
//   const [quantity, setQuantity] = useState(1);
  
//   const userId = localStorage.getItem('userId');
//   const token = localStorage.getItem('token');  // ✅ Ensured correct token key
//   const navigate = useNavigate();

//   useEffect(() => {
//     const fetchProduct = async () => {
//       if (!prodId) {
//         setError('Invalid product ID.');
//         setLoading(false);
//         return;
//       }

//       try {
//         const response = await axios.get(`http://localhost:8080/products/getproductbyid/${prodId}`, {
//           headers: { Authorization: `Bearer ${token}` },
//         });
//         setProduct(response.data);
//       } catch (err) {
//         setError('Error fetching product details. Please try again later.');
//         console.error('Error fetching product details:', err);
//       } finally {
//         setLoading(false);
//       }
//     };

//     fetchProduct();
//   }, [prodId, token]);

//   if (loading) return <p>Loading product details...</p>;
//   if (error) return <p className="text-danger">{error}</p>;

//   // Handle quantity change
//   const handleQuantityChange = (e) => {
//     const value = Math.max(1, Number(e.target.value)); // Ensure value is at least 1
//     setQuantity(value);
//   };

//   // Handle Add to Cart
//   const handleAddToCart = async () => {
//     if (!userId || !token) {
//       alert('Please log in to add products to the cart.');
//       navigate('/login');
//       return;
//     }

//     const cartData = {
//       userId,
//       productId: product.prodId,
//       quantity,
//     };

//     try {
//       await axios.post('http://localhost:8080/cart/addCart', cartData, {
//         headers: { Authorization: `Bearer ${token}` },
//       });
//       alert('Product added to cart successfully!');
//       navigate('/cart');
//     } catch (err) {
//       console.error('Error adding to cart:', err);
//       alert('Failed to add product to cart. Please try again.');
//     }
//   };

//   return (
//     <section className="py-5">
//       <div className="container">
//         <h2 className="text-center mb-4">Product Details</h2>
//         {product && (
//           <div className="row">
//             <div className="col-md-6">
//               <img
//                 src={`data:image/jpeg;base64,${product.prodImage}`}
//                 alt={product.prodName}
//                 className="img-fluid rounded shadow-sm mb-4"
//                 style={{ objectFit: 'cover', maxHeight: '600px', width: '100%' }}
//               />
//             </div>

//             <div className="col-md-6">
//               <h3>{product.prodName}</h3>
//               <p><strong>Description:</strong> {product.prodDescp}</p>
//               <p><strong>Price:</strong> Rs.{product.prodPrice}</p>
//               <p><strong>Category:</strong> {product.category?.categoryName || 'N/A'}</p>

//               <div className="d-flex align-items-center mb-3">
//                 <label htmlFor="quantity" className="me-3">Quantity:</label>
//                 <input
//                   type="number"
//                   id="quantity"
//                   value={quantity}
//                   onChange={handleQuantityChange}
//                   min="1"
//                   className="form-control w-25"
//                 />
//               </div>

//               <button onClick={handleAddToCart} className="btn btn-primary btn-lg w-100">
//                 Add to Cart
//               </button>
//             </div>
//           </div>
//         )}
//       </div>
//     </section>
//   );
// }

// export default ProductDetails;


import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

function ProductDetails() {
  const { prodId } = useParams();
  const [product, setProduct] = useState(null);
  const [reviews, setReviews] = useState([]);  // ✅ State for reviews
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [quantity, setQuantity] = useState(1);

  const userId = localStorage.getItem('userId');
  const token = localStorage.getItem('token');  
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProduct = async () => {
      if (!prodId) {
        setError('Invalid product ID.');
        setLoading(false);
        return;
      }

      try {
        // Fetch product details
        const productResponse = await axios.get(`http://localhost:8080/products/getproductbyid/${prodId}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setProduct(productResponse.data);

        // Fetch reviews for the product
        const reviewsResponse = await axios.get(`http://localhost:8080/reviews/getreviewbyproduct/${prodId}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setReviews(reviewsResponse.data);

      } catch (err) {
        setError('Error fetching product details or reviews. Please try again later.');
        console.error('Error fetching data:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchProduct();
  }, [prodId, token]);

  if (loading) return <p>Loading product details...</p>;
  if (error) return <p className="text-danger">{error}</p>;

  // Handle quantity change
  const handleQuantityChange = (e) => {
    const value = Math.max(1, Number(e.target.value)); 
    setQuantity(value);
  };

  // Handle Add to Cart
  const handleAddToCart = async () => {
    if (!userId || !token) {
      alert('Please log in to add products to the cart.');
      navigate('/login');
      return;
    }

    const cartData = {
      userId,
      productId: product.prodId,
      quantity,
    };

    try {
      await axios.post('http://localhost:8080/cart/addCart', cartData, {
        headers: { Authorization: `Bearer ${token}` },
      });
      alert('Product added to cart successfully!');
      navigate('/cart');
    } catch (err) {
      console.error('Error adding to cart:', err);
      alert('Failed to add product to cart. Please try again.');
    }
  };

  return (
    <section className="py-5">
      <div className="container">
        <h2 className="text-center mb-4">Product Details</h2>
        {product && (
          <div className="row">
            <div className="col-md-6">
              <img
                src={`data:image/jpeg;base64,${product.prodImage}`}
                alt={product.prodName}
                className="img-fluid rounded shadow-sm mb-4"
                style={{ objectFit: 'cover', maxHeight: '600px', width: '100%' }}
              />
            </div>

            <div className="col-md-6">
              <h3>{product.prodName}</h3>
              <p><strong>Description:</strong> {product.prodDescp}</p>
              <p><strong>Price:</strong> Rs.{product.prodPrice}</p>
              <p><strong>Category:</strong> {product.category?.categoryName || 'N/A'}</p>

              <div className="d-flex align-items-center mb-3">
                <label htmlFor="quantity" className="me-3">Quantity:</label>
                <input
                  type="number"
                  id="quantity"
                  value={quantity}
                  onChange={handleQuantityChange}
                  min="1"
                  className="form-control w-25"
                />
              </div>

              <button onClick={handleAddToCart} className="btn btn-primary btn-lg w-100">
                Add to Cart
              </button>
            </div>
          </div>
        )}

        {/* Reviews Section */}
        <div className="mt-5">
          <h3>Customer Reviews</h3>
          {reviews.length > 0 ? (
            <ul className="list-group">
              {reviews.map((review) => (
                <li key={review.reviewId} className="list-group-item">
                  <strong>{review.user.userName}</strong> 
                  <span className="ms-2 text-warning">⭐ {review.count}/5</span>
                  <p>{review.comment}</p>
                </li>
              ))}
            </ul>
          ) : (
            <p>No reviews yet. Be the first to review this product!</p>
          )}
        </div>
      </div>
    </section>
  );
}

export default ProductDetails;
