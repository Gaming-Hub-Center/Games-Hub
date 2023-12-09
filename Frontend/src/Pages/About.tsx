import { Col, Container, Row } from "react-bootstrap";

export function About() {
  return (
    <Container>
      <Row>
        <Col md={12}>
          <h1>About Page</h1>
          <p>Welcome to the about page. Here you can find out more about us.</p>
        </Col>
      </Row>
    </Container>
  );
}
