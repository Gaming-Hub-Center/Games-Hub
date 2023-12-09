import { NavLink } from "react-router-dom";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import { Container, Image } from "react-bootstrap";

export function NavbarC() {
  return (
    <Navbar
      className="bg-black text-white shadow-sm"
      variant="dark"
      style={{ backgroundColor: "black" }}
    >
      <Container fluid style={{ padding: 0 }}>
        <Navbar.Brand href="/">
          <Image
            src="/src/data/logo4.png"
            alt="Logo"
            style={{ maxWidth: "100%", maxHeight: "5vh" }}
          />
        </Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link
            as={NavLink}
            to="/buyer/home/games"
            style={{
              marginTop: "3px",
              marginRight: "5px",
              borderRadius: "50px",
            }}
          >
            Games Store
          </Nav.Link>
          <Nav.Link
            as={NavLink}
            to="/buyer/home/accessories"
            style={{
              marginTop: "3px",
              marginRight: "5px",
              borderRadius: "50px",
            }}
          >
            Gaming Accessories Store
          </Nav.Link>
          <Nav.Link
            as={NavLink}
            to="/about"
            style={{
              marginTop: "3px",
              marginRight: "5px",
              borderRadius: "50px",
            }}
          >
            About
          </Nav.Link>
        </Nav>

        <Nav>
          <Nav.Link
            as={NavLink}
            to="/signin"
            style={{
              marginTop: "3px",
              marginRight: "3px",
              borderRadius: "50px",
            }}
          >
            Sign In
          </Nav.Link>
          <Nav.Link
            as={NavLink}
            to="/signup/buyer"
            style={{
              marginTop: "3px",
              marginRight: "3px",
              borderRadius: "50px",
            }}
          >
            Sign Up
          </Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  );
}
