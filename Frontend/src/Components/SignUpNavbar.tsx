import React from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { NavLink } from "react-router-dom";
import "./SignUpNavbar.css";

export function SignUpNavbar() {
  return (
    <Navbar
      expand="lg"
      bg="primary"
      variant="primary"
      data-bs-theme="dark"
      className="navbar"
    >
      <Container
        fluid
        className="d-flex align-items-center justify-content-center"
      >
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto navbar-nav">
            <Nav.Link to="/SignUp" as={NavLink}>
              Customer account
            </Nav.Link>
            <div
              style={{
                height: "40px",
                width: "1px",
                background: "#fff",
                margin: "0 10px",
              }}
            ></div>
            <Nav.Link to="/SignUpSeller" as={NavLink}>
              Seller account
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
