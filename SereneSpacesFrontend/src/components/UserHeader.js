import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

const UserHeader = ({ onLogout }) => {
  const navigate = useNavigate();

  // Logout function to clear token or user data from storage
  const handleLogout = () => {
    localStorage.removeItem('authToken');  // Remove token from localStorage
    onLogout();  // Callback function passed from the parent to update state in App.js
    navigate('/login');  // Redirect to login page after logout
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <a className="navbar-brand ms-3" href="/">SereneSpaces</a>
      <button 
        className="navbar-toggler" 
        type="button" 
        data-bs-toggle="collapse" 
        data-bs-target="#navbarNav" 
        aria-controls="navbarNav" 
        aria-expanded="false" 
        aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNav">
        <ul className="navbar-nav ms-auto">
          {/* Home, Products, Cart, Favorites, Profile, and Logout visible when logged in */}
          <li className="nav-item">
            <Link className="nav-link" to="/">Home</Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/products">Products</Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/cart">Cart</Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/favorites">Favorites</Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/profile">Update Profile</Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/order">Order</Link>
          </li>
          <li className="nav-item">
            <button className="btn btn-link nav-link" onClick={handleLogout}>
              Logout
            </button>
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default UserHeader;
