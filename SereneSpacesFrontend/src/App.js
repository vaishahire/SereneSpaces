import React, { useState, useEffect } from "react";
import { Route, Routes, Navigate, useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";

import "./App.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import Main from "./components/Main";
import Product from "./components/Product";
import About from "./components/AboutUs";
import Contact from "./components/Contact";
import Login from "./components/Login";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Register from "./components/Register";
import UserHeader from "./components/UserHeader";
import Cart from "./components/Cart";
import Favorites from "./components/Favourite";
import Profile from "./components/Profile";
import ProductDetails from "./components/ProductDetails";
import Payment from "./components/Payment";
import Order from "./components/Order";
import Logout from "./components/Logout";

// Admin Components
import ManageProducts from "./components/Admin/ManageProducts";
import ManageCategory from "./components/Admin/ManageCategory";
import ManageUsers from "./components/Admin/ManageUsers";
import ViewOrders from "./components/Admin/ViewOrders";
import ViewPayments from "./components/Admin/ViewPayments";
import AddProduct from "./components/Admin/AddProduct";
import EditProduct from "./components/Admin/EditProduct";
import AdminDashboard from "./components/Admin/Home";
import AdminHeader from "./components/Admin/AdminHeader";
import AddCategory from "./components/Admin/AddCategory";
import EditCategory from "./components/Admin/EditCategory";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [role, setRole] = useState(null);
  const [initialRedirectDone, setInitialRedirectDone] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (token) {
      try {
        const decoded = jwtDecode(token);
        const userRole = decoded.authorities;
        setIsLoggedIn(true);
        setRole(userRole);

        // Redirect admin only on first login
        if (userRole === "ROLE_ADMIN" && !initialRedirectDone) {
          navigate("/admin-dashboard");
          setInitialRedirectDone(true); // Prevent future redirects
        }
      } catch (error) {
        console.error("Invalid token:", error);
        localStorage.removeItem("token");
        localStorage.removeItem("userId");
        setIsLoggedIn(false);
        setRole(null);
      }
    }
  }, [navigate, initialRedirectDone]);

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    setIsLoggedIn(false);
    setRole(null);
    setInitialRedirectDone(false); // Reset redirect state on logout
    navigate("/login");
  };

  return (
    <div className="app-container">
      {role === "ROLE_ADMIN" ? (
        <AdminHeader onLogout={handleLogout} />
      ) : isLoggedIn ? (
        <UserHeader onLogout={handleLogout} />
      ) : (
        <Header />
      )}

      <div className="content">
        <Routes>
          {/* Admin Routes */}
          {role === "ROLE_ADMIN" ? (
            <>
              <Route path="/admin-dashboard" element={<AdminDashboard />} />
              <Route path="/manage-products" element={<ManageProducts />} />
              <Route path="/manage-category" element={<ManageCategory />} />
              <Route path="/manage-users" element={<ManageUsers />} />
              <Route path="/view-orders" element={<ViewOrders />} />
              <Route path="/view-payments" element={<ViewPayments />} />
              <Route path="/add-product" element={<AddProduct />} />
              <Route path="/edit-product/:id" element={<EditProduct />} />
              <Route path="/add-category" element={<AddCategory />} />
              <Route path="/edit-category/:id" element={<EditCategory />} />
              <Route path="*" element={<Navigate to="/admin-dashboard" />} />
            </>
          ) : (
            <>
              {/* User Routes */}
              <Route path="/" element={<Main />} />
              <Route path="/products" element={<Product />} />
              <Route path="/about" element={<About />} />
              <Route path="/contact" element={<Contact />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route path="/cart" element={isLoggedIn ? <Cart /> : <Navigate to="/login" />} />
              <Route path="/favorites" element={isLoggedIn ? <Favorites /> : <Navigate to="/login" />} />
              <Route path="/profile" element={isLoggedIn ? <Profile /> : <Navigate to="/login" />} />
              <Route path="/payment" element={<Payment />} />
              <Route path="/order" element={<Order />} />
              <Route path="/logout" element={<Logout />} />
              <Route path="/productdetails/:prodId" element={<ProductDetails />} />
              <Route path="*" element={<Navigate to="/" />} />
            </>
          )}
        </Routes>
      </div>

      <Footer />
    </div>
  );
}

export default App;
