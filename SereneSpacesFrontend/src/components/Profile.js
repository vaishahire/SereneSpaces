import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

const Profile = () => {
    const [user, setUser] = useState({
        userName: "",
        address: "",
        mobileNumber: "",
    });

    const userId = localStorage.getItem("userId"); // Get userId from localStorage
    const token = localStorage.getItem("token"); // Get JWT token from localStorage
    const navigate = useNavigate(); // Hook to navigate between pages

    useEffect(() => {
        console.log("Fetching user data..."); // Log the fetch operation
        axios
            .get(`http://localhost:8080/user/getuser/${userId}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            })
            .then((response) => {
                // console.log("User data fetched successfully:", response.data); // Log success
                setUser(response.data);
            })
            .catch((error) => {
                console.error("Error fetching user data:", error); // Log error if any
            });
    }, [userId, token]);

    const handleChange = (e) => {
        // console.log(`Field ${e.target.name} changed to: ${e.target.value}`); // Log field change
        setUser({ ...user, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        // console.log("Submitting profile update..."); // Log the submit action
        try {
            await axios.put(`http://localhost:8080/user/updateuser/${userId}`, user, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json",
                },
            });
            // console.log("Profile updated successfully!"); // Log success
            alert("Profile updated successfully!");
            navigate("/"); // Redirect to Home Page
        } catch (error) {
            console.error("Error updating profile:", error); // Log error if any
        }
    };

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card shadow-sm p-4">
                        <h2 className="text-center mb-4">Update Profile</h2>
                        <form onSubmit={handleSubmit}>
                            <div className="mb-3">
                                <label className="form-label">Name:</label>
                                <input
                                    type="text"
                                    name="userName"
                                    value={user.userName}
                                    onChange={handleChange}
                                    className="form-control"
                                    required
                                />
                            </div>
                            <div className="mb-3">
                                <label className="form-label">Address:</label>
                                <input
                                    type="text"
                                    name="address"
                                    value={user.address}
                                    onChange={handleChange}
                                    className="form-control"
                                    required
                                />
                            </div>
                            <div className="mb-3">
                                <label className="form-label">Mobile Number:</label>
                                <input
                                    type="text"
                                    name="mobileNumber"
                                    value={user.mobileNumber}
                                    onChange={handleChange}
                                    className="form-control"
                                    required
                                />
                            </div>
                            <button type="submit" className="btn btn-primary w-100">
                                Update Profile
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Profile;
