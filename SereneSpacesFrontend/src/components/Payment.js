import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";

function Payment() {
  const location = useLocation();
  const navigate = useNavigate();
  const { orderDetails, totalPrice, orderId } = location.state; // Ensure orderId is passed

  const [paymentType, setPaymentType] = useState("Credit Card");
  const userId = localStorage.getItem("userId"); // Fetch userId from localStorage
  const token = localStorage.getItem("token");

  const handlePayment = async () => {
    try {
      const paymentData = {
        payType: paymentType,
        payAmt: totalPrice,
        orderId: orderId,
        userId: userId, // Include userId
      };

      const config = {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      };

      await axios.post("http://localhost:8080/payments/addpayment", paymentData, config);
      alert("Payment successful!");

      navigate("/order-summary");
    } catch (error) {
      console.error("Payment failed", error);
      alert("Payment failed. Please try again.");
    }
  };

  return (
    <section className="py-5" style={{ backgroundColor: '#f8f9fa' }}>
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-8 col-lg-6">
            <div className="card shadow-sm">
              <div className="card-body">
                <h2 className="text-center mb-4">Payment Details</h2>

                {/* Order Details */}
                <div className="mb-4">
                  <h4>Order Summary</h4>
                  <ul className="list-group">
                    {orderDetails.map((item, index) => (
                      <li key={index} className="list-group-item d-flex align-items-center">
                        <img
                          src={item.img}
                          alt={item.prodName}
                          style={{ width: "50px", marginRight: "15px" }}
                        />
                        <div>
                          <p>{item.prodName} x{item.quantity}</p>
                          <p>Rs. {item.price * item.quantity}</p>
                        </div>
                      </li>
                    ))}
                  </ul>
                  <div className="mt-3">
                    <p><strong>Total Amount: Rs. {totalPrice}</strong></p>
                  </div>
                </div>

                {/* Payment Method Selection */}
                <div className="form-group mb-4">
                  <label>Select Payment Method:</label>
                  <select
                    className="form-control"
                    value={paymentType}
                    onChange={(e) => setPaymentType(e.target.value)}
                  >
                    <option value="Credit Card">Credit Card</option>
                    <option value="Debit Card">Debit Card</option>
                    <option value="Net Banking">Net Banking</option>
                    <option value="UPI">UPI</option>
                    <option value="Cash on Delivery">Cash on Delivery</option>
                  </select>
                </div>

                {/* Payment Button */}
                <button
                  className="btn btn-primary btn-block mt-3"
                  onClick={handlePayment}
                >
                  Pay Now
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

export default Payment;
