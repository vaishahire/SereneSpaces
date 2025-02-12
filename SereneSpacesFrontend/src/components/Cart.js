import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Cart() {
  const [cartItems, setCartItems] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const userId = localStorage.getItem('userId'); // Get userId from localStorage
  const token = localStorage.getItem('token'); // Get token from localStorage

  useEffect(() => {
    if (!userId || !token) {
      navigate('/login');
      return;
    }

    axios.get(`http://localhost:8080/cart/Cartproducts/${userId}`, {
      headers: { Authorization: `Bearer ${token}` }
    })
    .then((response) => {
      setCartItems(response.data);
      calculateTotalPrice(response.data);
      setLoading(false);
    })
    .catch(() => {
      setError('Error fetching cart items');
      setLoading(false);
    });

  }, [userId, token, navigate]);

  const calculateTotalPrice = (items) => {
    const total = items.reduce((sum, item) => sum + item.productPrice * item.selectedQty, 0);
    setTotalPrice(total);
  };

  const handleRemoveItem = (productId) => {
    axios.delete(`http://localhost:8080/cart/removeCart/${userId}/${productId}`, {
      headers: { Authorization: `Bearer ${token}` }
    })
    .then(() => {
      const updatedCart = cartItems.filter(item => item.productId !== productId);
      setCartItems(updatedCart);
      calculateTotalPrice(updatedCart);
    })
    .catch((error) => {
      console.error('Error removing item from cart:', error);
    });
  };

  // const handleCheckout = () => {
  //   const orderDetails = cartItems.map(item => ({
  //     prodName: item.productName,
  //     img: `data:image/jpeg;base64,${item.prodImage}`,
  //     description: item.descp,
  //     price: item.productPrice,
  //     quantity: item.selectedQty,
  //     userId: userId,
  //   }));

  //   axios.post('http://localhost:8080/orders/addOrder', orderDetails, {
  //     headers: { Authorization: `Bearer ${token}` }
  //   })
  //   .then(() => {
  //     navigate('/payment', { state: { orderDetails, totalPrice } });
  //   })
  //   .catch((error) => {
  //     console.error('Error creating order:', error);
  //   });
  // };

  const handleCheckout = () => {
    const orderDetails = cartItems.map(item => ({
      prodName: item.productName,
      img: `data:image/jpeg;base64,${item.prodImage}`,
      description: item.descp,
      price: item.productPrice,
      quantity: item.selectedQty,
      userId: userId,
    }));
  
    axios.post('http://localhost:8080/orders/addOrder', orderDetails, {
      headers: { Authorization: `Bearer ${token}` }
    })
    .then((response) => {
      const orderId = response.data; // Extract orderId from the backend response
      navigate('/payment', { state: { orderDetails, totalPrice, orderId } });
    })
    .catch((error) => {
      console.error('Error creating order:', error);
    });
  };
  

  if (loading) return <p>Loading cart items...</p>;
  if (error) return <p>{error}</p>;

  return (
    <section className="py-5">
      <div className="container">
        <h2 className="text-center mb-4">My Cart</h2>

        {cartItems.length === 0 ? (
          <p>Your cart is empty. <a href="/products">Browse Products</a></p>
        ) : (
          <div>
            <div className="table-responsive">
              <table className="table table-bordered">
                <thead>
                  <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  {cartItems.map((item) => (
                    <tr key={item.cartId}>
                      <td>
                        <div className="d-flex align-items-center">
                          <img 
                            src={`data:image/jpeg;base64,${item.prodImage}`} 
                            alt={item.productName} 
                            style={{ width: '60px', height: '60px', objectFit: 'cover', marginRight: '10px' }}
                          />
                          {item.productName}
                        </div>
                      </td>
                      <td>{item.selectedQty}</td>
                      <td>Rs. {item.productPrice}</td>
                      <td>Rs. {item.productPrice * item.selectedQty}</td>
                      <td>
                        <button className="btn btn-danger btn-sm" onClick={() => handleRemoveItem(item.productId)}>
                          Remove
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>

            <div className="d-flex justify-content-between align-items-center mt-4">
              <h4>Total Price: Rs. {totalPrice}</h4>
              <button className="btn btn-primary btn-lg" onClick={handleCheckout}>
                Proceed to Checkout
              </button>
            </div>
          </div>
        )}
      </div>
    </section>
  );
}

export default Cart;
