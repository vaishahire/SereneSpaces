import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Register = () => {
  const [userName, setUserName] = useState('');
  const [address, setAddress] = useState('');
  const [mobileNumber, setMobileNumber] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();

    // Check if password and confirmPassword match
    if (password !== confirmPassword) {
      setErrorMessage('Password and Confirm Password do not match');
      return;
    }

    // Create the request body
    const userData = {
      userName: userName,
      address: address,
      mobileNumber: mobileNumber,
      authentication: {
        email: email,
        password: password,
        role: 'USER', // Default role as "USER"
      },
    };

    try {
      const response = await fetch('http://localhost:8080/user/signup', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
      });

      if (response.ok) {
        const data = await response.text;
        console.log('Registration successful:', data);
        navigate('/login');
      } else {
        const errorMessage = await response.text(); // Read the error message from response
      console.error('Registration failed:', errorMessage);
      setErrorMessage(errorMessage || 'Registration failed. Please try again.');
      }
    } catch (error) {
      console.error('Error during registration:', error);
      setErrorMessage('An error occurred. Please try again.');
    }
  };

  return (
    <section className="py-5" style={{ backgroundColor: '#f8f9fa' }}>
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-6">
            <div className="card shadow-sm">
              <div className="card-body">
                <h2 className="text-center mb-4">Register</h2>
                <form onSubmit={handleRegister}>
                  {/* Full Name */}
                  <div className="form-group mb-3">
                    <label htmlFor="userName">Full Name</label>
                    <input
                      type="text"
                      className="form-control"
                      id="userName"
                      value={userName}
                      onChange={(e) => setUserName(e.target.value)}
                      placeholder="Enter full name"
                      required
                    />
                  </div>

                  {/* Address */}
                  <div className="form-group mb-3">
                    <label htmlFor="address">Address</label>
                    <input
                      type="text"
                      className="form-control"
                      id="address"
                      value={address}
                      onChange={(e) => setAddress(e.target.value)}
                      placeholder="Enter address"
                      required
                    />
                  </div>

                  {/* Mobile Number */}
                  <div className="form-group mb-3">
                    <label htmlFor="mobileNumber">Mobile Number</label>
                    <input
                      type="text"
                      className="form-control"
                      id="mobileNumber"
                      value={mobileNumber}
                      onChange={(e) => setMobileNumber(e.target.value)}
                      placeholder="Enter mobile number"
                      required
                    />
                  </div>

                  {/* Email */}
                  <div className="form-group mb-3">
                    <label htmlFor="email">Email address</label>
                    <input
                      type="email"
                      className="form-control"
                      id="email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      placeholder="Enter email"
                      required
                    />
                  </div>

                  {/* Password */}
                  <div className="form-group mb-3">
                    <label htmlFor="password">Password</label>
                    <input
                      type="password"
                      className="form-control"
                      id="password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      placeholder="Enter password"
                      required
                    />
                  </div>

                  {/* Confirm Password */}
                  <div className="form-group mb-3">
                    <label htmlFor="confirmPassword">Confirm Password</label>
                    <input
                      type="password"
                      className="form-control"
                      id="confirmPassword"
                      value={confirmPassword}
                      onChange={(e) => setConfirmPassword(e.target.value)}
                      placeholder="Confirm password"
                      required
                    />
                  </div>

                  {/* Error Message */}
                  {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}

                  {/* Submit Button */}
                  <button type="submit" className="btn btn-primary btn-block">
                    Register
                  </button>

                  {/* Login Link */}
                  <div className="mt-3 text-center">
                    <p>
                      Already have an account? <a href="/login">Login here</a>
                    </p>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Register;
