import React, { useState, useEffect } from "react";

import { Navbar, Container, Col } from "react-bootstrap";

const Footer = () => {
  const [fullYear, setFullYear] = useState();

  useEffect(() => {
    setFullYear(new Date().getFullYear());
  }, [fullYear]);

  return (
    <Navbar fixed="bottom" bg="dark" variant="dark">
      <Container>
        <Col lg={12} className="text-center text-muted">
          <div>
            {fullYear}-{fullYear + 1}, Все права и лицензии, а также знания были получены в ИТМО
          </div>
        </Col>
      </Container>
    </Navbar>
  );
};

export default Footer;
