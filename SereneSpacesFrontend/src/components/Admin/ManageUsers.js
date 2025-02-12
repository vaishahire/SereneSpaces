import { useState, useEffect } from "react";
import axios from "axios";

const ManageUsers = () => {
  const [users, setUsers] = useState([]);

  // Get JWT token from localStorage
  const token = localStorage.getItem("token");
  const headers = { Authorization: `Bearer ${token}` };

  useEffect(() => {
    axios
      .get("http://localhost:8080/user/getusers", { headers }) // Include headers
      .then((response) => {
        console.log("Fetched Users:", response.data); // Debugging
        setUsers(response.data);
      })
      .catch((error) => console.error("Error fetching users:", error));
  }, []);

  const handleDelete = (id) => {
    if (window.confirm("Are you sure you want to delete this user?")) {
      axios
        .delete(`http://localhost:8080/user/deleteuser/${id}`, { headers }) // Include headers
        .then(() => setUsers(users.filter((user) => user.userId !== id))) // Ensure matching field
        .catch((error) => console.error("Error deleting user:", error));
    }
  };

  return (
    <div className="container mt-4">
      <h2>Manage Users</h2>
      <table className="table table-bordered">
        <thead className="table-dark">
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Address</th>
            <th>Mobile Number</th>
            {/* <th>Actions</th> */}
          </tr>
        </thead>
        <tbody>
          {users.length > 0 ? (
            users.map((user) => (
              <tr key={user.userId}> {/* Ensure correct key */}
                <td>{user.userId}</td>
                <td>{user.userName}</td> {/* Ensure correct field */}
                <td>{user.address}</td>
                <td>{user.mobileNumber}</td>
                {/* <td>
                  <button className="btn btn-danger btn-sm" onClick={() => handleDelete(user.userId)}>
                    Delete
                  </button>
                </td> */}
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5" className="text-center">No users found</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ManageUsers;
