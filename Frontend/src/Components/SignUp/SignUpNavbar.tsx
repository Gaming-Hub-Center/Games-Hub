import React from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { NavLink } from "react-router-dom";

export function SignUpNavbar() {
  // const handleBuyerMouseEnter = (e) => {
  //   e.currentTarget.style.backgroundColor = "rgba(0, 0, 0, 0.8)";
  //   e.currentTarget.style.color = "black";
  // };

  // const handleBuyerMouseLeave = (e) => {
  //   e.currentTarget.style.backgroundColor = "transparent";
  // };

  // const handleSellerMouseEnter = (e) => {
  //   e.currentTarget.style.backgroundColor = "rgba(0, 0, 0, 0.8)";
  //   e.currentTarget.style.color = "black";
  // };

  // const handleSellerMouseLeave = (e) => {
  //   e.currentTarget.style.backgroundColor = "transparent";
  // };

  return (
    <Navbar
      variant="dark"
      className="shadow-sm"
      style={{
        borderRadius: "8px",
        marginBottom: "8px",
        backgroundColor: "#733BC0",
      }}
    >
      <Container
        fluid
        className="d-flex align-items-center justify-content-center"
      >
        <Nav
          className="me-auto navbar-nav"
          style={{ width: "100%", display: "flex", justifyContent: "center" }}
        >
          <Nav.Link
            to="/signup/buyer"
            as={NavLink}
            style={{ textAlign: "center", width: "50%" }}
          >
            Customer account
          </Nav.Link>
          <div
            style={{ width: "1px", height: "38px", backgroundColor: "#f0f0f0" }}
          ></div>
          <Nav.Link
            to="/signup/seller"
            as={NavLink}
            style={{ textAlign: "center", width: "50%" }}
          >
            Seller account
          </Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  );
}
