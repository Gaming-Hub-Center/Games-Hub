import { Col, Container, Row } from "react-bootstrap";

export function Welcome() {
  return (
    <Container
      fluid
      style={{ height: "100vh", overflow: "hidden", padding: 0 }}
    >
      <video
        autoPlay
        loop
        muted
        style={{
          position: "absolute",
          width: "100%",
          height: "100%",
          objectFit: "fill",
          zIndex: -1,
        }}
      >
        <source
          src="/src/data/The Lion King (Arabic) Hakuna Matata.mp4"
          type="video/mp4"
        />
      </video>
      <Row style={{ height: "100%", margin: 0 }}>
        <Col
          md={6}
          className="d-flex align-items-center justify-content-center"
          style={{
            height: "100%",
            backgroundColor: "rgba(240, 240, 240, 0.8)",
          }}
        >
          <div style={{ border: "1px solid #ccc", padding: "20px" }}>
            First Column Content
          </div>
        </Col>
        <Col
          md={6}
          className="d-flex align-items-center justify-content-center"
          style={{
            height: "100%",
            backgroundColor: "rgba(224, 224, 224, 0.8)",
          }}
        >
          <div style={{ border: "1px solid #ccc", padding: "20px" }}>
            Second Column Content
          </div>
        </Col>
      </Row>
    </Container>
  );
}
