import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Order() {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const userId = localStorage.getItem('userId'); 
  const token = localStorage.getItem('token'); 

  useEffect(() => {
    if (!userId || !token) {
      navigate('/login'); 
      return;
    }

    axios.get(`http://localhost:8080/orders/getOrders/${userId}`, {
      headers: { Authorization: `Bearer ${token}` },
    })
      .then((response) => {
        setOrders(response.data);
        setLoading(false);
      })
      .catch((err) => {
        setLoading(false);
        if (err.response) {
          if (err.response.status === 401) {
            setError('Unauthorized! Please login again.');
            localStorage.removeItem('token'); 
            navigate('/login');
          } else if (err.response.status === 403) {
            setError('Access Denied! You are not authorized to view this page.');
          } else if (err.response.status === 404) {
            setError('No orders found for this user.');
          } else {
            setError('Error fetching orders. Please try again later.');
          }
        } else {
          setError('Server is unreachable. Please try again later.');
        }
      });
  }, [userId, token, navigate]);

  if (loading) return <p>Loading orders...</p>;
  if (error) return <p className="text-danger">{error}</p>;

  return (
    <section className="py-5">
      <div className="container">
        <h2 className="text-center mb-4">My Orders</h2>

        {orders.length === 0 ? (
          <p>Your order history is empty. <a href="/products">Browse Products</a></p>
        ) : (
          <div className="table-responsive">
            <table className="table table-bordered">
              <thead>
                <tr>
                  <th>Order ID</th>
                  <th>Order Date</th>
                  <th>Product</th>
                  <th>Quantity</th>
                  <th>Price</th>
                  <th>Total</th>
                </tr>
              </thead>
              <tbody>
                {orders.map((order) => (
                  order.orderProducts?.map((item) => (
                    <tr key={item.id}>
                      <td>{order.orderId}</td>
                      <td>{new Date(order.orderDateTime).toLocaleString()}</td>
                      <td>
                        <div className="d-flex align-items-center">
                          <img
                            src={`data:image/jpeg;base64,${item.imgUrl}`} 
                            alt={item.prodName}
                            style={{ width: '60px', height: '60px', objectFit: 'cover', marginRight: '10px' }}
                          />
                          {item.prodName}
                        </div>
                      </td>
                      <td>{item.selectedQty}</td>
                      <td>Rs. {item.price}</td>
                      <td>Rs. {item.price * item.selectedQty}</td>
                    </tr>
                  ))
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </section>
  );
}

export default Order;
