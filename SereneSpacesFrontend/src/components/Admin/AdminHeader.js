import { Link, useNavigate } from "react-router-dom";

const AdminHeader = ({ onLogout }) => {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  const handleLogout = () => {
    localStorage.removeItem("token");
    onLogout(); 
    navigate("/login");
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/admin-dashboard">Admin Panel</Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ms-auto">
            <li className="nav-item">
              <Link className="nav-link" to="/manage-products">Manage Products</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/manage-category">Manage Category</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/manage-users">Manage Users</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/view-payments">View Payments</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/view-orders">View Orders</Link>
            </li>
            <li className="nav-item">
              <button className="nav-link btn btn-danger" onClick={handleLogout}>Logout</button>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default AdminHeader;
