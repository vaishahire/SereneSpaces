// src/components/Admin.js
import React from 'react';
import { Link } from 'react-router-dom';

const Admin = () => {
  return (
    <div className="container">
      <h1 className="mt-4">Admin Panel</h1>
      <p>Welcome to the Admin Panel! Here you can manage all aspects of the site.</p>

      <div className="list-group">
        <Link to="/admin/manage-products" className="list-group-item list-group-item-action">
          Manage Products
        </Link>
        <Link to="/admin/manage-category" className="list-group-item list-group-item-action">
          Manage Categories
        </Link>
        <Link to="/admin/manage-users" className="list-group-item list-group-item-action">
          Manage Users
        </Link>
        <Link to="/admin/view-payments" className="list-group-item list-group-item-action">
          View Payments
        </Link>
        <Link to="/admin/view-orders" className="list-group-item list-group-item-action">
          View Orders
        </Link>
      </div>
    </div>
  );
};

export default Admin;
