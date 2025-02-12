import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const Logout = () => {
  const navigate = useNavigate();

  useEffect(() => {
    // Directly navigate to login page after a short delay
    setTimeout(() => {
      navigate('/login');
    }, 1000);
  }, [navigate]);

  return (
    <div className="container">
      <h3>You have been logged out successfully.</h3>
      <p>Redirecting to login page...</p>
    </div>
  );
};

export default Logout;
