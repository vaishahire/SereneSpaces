import React from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate for navigation
import backgroundImage from '../assests/images/background.jpg'; // Import the image from assets

function Main() {
  const navigate = useNavigate(); // Hook to navigate to different pages

  const handleExploreClick = () => {
    navigate('/products'); // Navigate to the products page
  };

  return (
    <section 
      className="main-section text-white text-center py-5" 
      style={{ 
        backgroundColor: '#343a40', 
        backgroundImage: `url(${backgroundImage})`, // Use the imported image
        backgroundSize: 'cover', 
        backgroundPosition: 'center', 
        height: '100vh', // Ensure the section takes full viewport height
        display: 'flex', 
        justifyContent: 'center', 
        alignItems: 'center', 
        textAlign: 'center' 
      }}
    >
      <div className="quote" style={{ maxWidth: '80%', padding: '20px', backgroundColor: 'rgba(0, 0, 0, 0.6)', borderRadius: '10px' }}>
        <h1 style={{ fontSize: '2.5rem', fontWeight: 'bold' }}>
          "Transform your space with elegance and style."
        </h1>
        <button 
          onClick={handleExploreClick} // Call handleExploreClick when button is clicked
          style={{
            marginTop: '20px',
            padding: '10px 20px',
            fontSize: '1.2rem',
            backgroundColor: '#f8a80b',
            color: '#fff',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer',
            transition: 'background-color 0.3s'
          }}
          onMouseEnter={(e) => e.target.style.backgroundColor = '#d58e0c'} // Change color on hover
          onMouseLeave={(e) => e.target.style.backgroundColor = '#f8a80b'} // Reset color when hover ends
        >
          Explore Our Products
        </button>
      </div>
    </section>
  );
}

export default Main;
