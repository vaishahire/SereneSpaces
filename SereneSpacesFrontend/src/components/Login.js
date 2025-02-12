import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Login() {
  // State management for email, password, and error message
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    // Reset error message
    setErrorMessage('');

    // Perform form validation
    if (!email || !password) {
      setErrorMessage('Both fields are required.');
      return;
    }

    // Send login request to backend
    try {
      const response = await fetch('http://localhost:8080/authusers/signin', {  
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
      });

      const data = await response.json();
     
      if (response.ok) {
        // Store the token & userId properly in localStorage
        localStorage.setItem('token', data.jwt);  // ✅ Fixed: Store token as 'token'
        localStorage.setItem('userId', data.userId);  // Store the userId correctly
        console.log("Login successful!"); 
        navigate('/'); // Redirect to home page
      } else {
        // Handle error (e.g., invalid credentials)
        setErrorMessage(data.message || 'Login failed. Please try again.');
      }
    } catch (error) {
      console.error('Error logging in:', error);
      setErrorMessage('An error occurred. Please try again later.');
    }
  };

  return (
    <section className="py-5" style={{ backgroundColor: '#f8f9fa' }}>
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-6">
            <div className="card shadow-sm">
              <div className="card-body">
                <h2 className="text-center mb-4">Login</h2>

                {/* Show error message if any */}
                {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}

                <form onSubmit={handleSubmit}>
                  {/* Email Field */}
                  <div className="form-group mb-3">
                    <label htmlFor="formEmail">Email address</label>
                    <input
                      type="email"
                      className="form-control"
                      id="formEmail"
                      placeholder="Enter email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      required
                    />
                  </div>

                  {/* Password Field */}
                  <div className="form-group mb-3">
                    <label htmlFor="formPassword">Password</label>
                    <input
                      type="password"
                      className="form-control"
                      id="formPassword"
                      placeholder="Enter password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      required
                    />
                  </div>

                  {/* Submit Button */}
                  <button type="submit" className="btn btn-primary btn-block">
                    Login
                  </button>

                  {/* Registration Link */}
                  <div className="mt-3 text-center">
                    <p>
                      Don't have an account?{' '}
                      <Link to="/register">Register here</Link>
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
}

export default Login;
