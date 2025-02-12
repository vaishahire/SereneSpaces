import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';

function About() {
  return (
    <section className="py-5">
      <Container>
        <Row>
          <Col>
            <h2>About Us</h2>
            <p>
              We provide premium home decor items to enhance the beauty of your home. Our mission is to create stunning interiors that reflect your style and comfort.
            </p>
          </Col>
        </Row>
      </Container>
    </section>
  );
}

export default About;
