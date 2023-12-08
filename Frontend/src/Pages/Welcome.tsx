import { Col, Container, Row, Image } from "react-bootstrap";

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
          height: "100vh",
          objectFit: "fill",
          zIndex: -1,
        }}
      >
        <source src="/src/data/Welcome.mp4" type="video/mp4" />
      </video>
      <Row style={{ height: "10vh", margin: 0 }}>
        <Col
          md={12}
          style={{
            backgroundColor: "rgb(0, 0, 0)",
            display: "flex",
            justifyContent: "center",
            maxHeight: "100%",
          }}
        >
          <Image
            src="/src/data/featured-s.png"
            alt="Logo"
            style={{ maxWidth: "100%", maxHeight: "100%" }}
          />
        </Col>
      </Row>
      <Row style={{ height: "90vh", margin: 0 }}>
        <Col
          md={6}
          className="d-flex align-items-center justify-content-center"
          style={{
            height: "100vh",
            backgroundColor: "rgba(240, 240, 240, 0.1)",
            transition: "background-color 0.3s",
          }}
          onMouseEnter={(e) =>
            (e.currentTarget.style.backgroundColor = "rgba(0, 0, 0, 0.6)")
          }
          onMouseLeave={(e) =>
            (e.currentTarget.style.backgroundColor = "rgba(240, 240, 240, 0.1)")
          }
        >
          <div
            style={{
              padding: "20px",
              width: "80%",
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              textAlign: "center",
            }}
          >
            <p
              style={{
                fontSize: "1.3em",
                color: "white",
                textShadow: "2px 2px 2px rgba(0, 0, 0, 1)",
              }}
            >
              Welcome to gaming paradise! Dive into a world where stories are
              made, and adventures await at every click. At{" "}
              <strong>Games Hub</strong>, we've got the games that turn moments
              into memories. Ready to level up? Let the fun begin!
            </p>
            <button
              style={{
                padding: "10px 20px",
                fontSize: "1.2em",
                marginTop: "20px",
                backgroundColor: "black",
                color: "#f0f0f0",
                border: "none",
                borderRadius: "5px",
                cursor: "pointer",
                transition: "background-color 0.3s", // Adding a smooth transition
              }}
              onMouseEnter={(e) =>
                (e.currentTarget.style.backgroundColor = "rgba(0, 0, 0, 0.5)")
              }
              onMouseLeave={(e) =>
                (e.currentTarget.style.backgroundColor = "rgb(0, 0, 0)")
              }
            >
              Games Store
            </button>
          </div>
        </Col>
        <Col
          md={6}
          className="d-flex align-items-center justify-content-center"
          style={{
            height: "100vh",
            backgroundColor: "rgba(240, 240, 240, 0.1)",
            transition: "background-color 0.3s",
          }}
          onMouseEnter={(e) =>
            (e.currentTarget.style.backgroundColor = "rgba(0, 0, 0, 0.6)")
          }
          onMouseLeave={(e) =>
            (e.currentTarget.style.backgroundColor = "rgba(240, 240, 240, 0.1)")
          }
        >
          <div
            style={{
              padding: "20px",
              width: "80%",
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              textAlign: "center",
            }}
          >
            <p
              style={{
                fontSize: "1.3em",
                color: "white",
                textShadow: "2px 2px 2px rgba(0, 0, 0, 1)",
              }}
            >
              Unlock your gaming potential with <strong>Games Hub</strong> your
              ultimate destination for top-tier gaming accessories! Elevate your
              play with our gear that transforms every click into a victory. We
              know your needs. Level up with us!
            </p>
            <button
              style={{
                padding: "10px 20px",
                fontSize: "1.2em",
                marginTop: "20px",
                backgroundColor: "black",
                color: "#f0f0f0",
                border: "none",
                borderRadius: "5px",
                cursor: "pointer",
                transition: "background-color 0.3s", // Adding a smooth transition
              }}
              onMouseEnter={(e) =>
                (e.currentTarget.style.backgroundColor = "rgba(0, 0, 0, 0.5)")
              }
              onMouseLeave={(e) =>
                (e.currentTarget.style.backgroundColor = "rgb(0, 0, 0)")
              }
            >
              Gaming Accessories Store
            </button>
          </div>
        </Col>
      </Row>
    </Container>
  );
}
