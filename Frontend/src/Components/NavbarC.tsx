import { Form, NavLink } from "react-router-dom";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import { Button, Col, Container, FormControl, Image } from "react-bootstrap";
import { SetStateAction, useState } from "react";

export function NavbarC() {
  const [searchText, setSearchText] = useState("");

  const handleSearchTextChange = (event: {
    target: { value: SetStateAction<string> };
  }) => {
    setSearchText(event.target.value);
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {};

  return (
    <Navbar
      className="bg-black text-white shadow-sm"
      variant="dark"
      style={{ backgroundColor: "black" }}
      sticky="top"
    >
      <Container fluid style={{ paddingLeft: 0 }}>
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
        <Col
          style={{ marginLeft: "20px", display: "flex", flexDirection: "row" }}
        >
          <FormControl
            style={{ width: "800px" }}
            type="text"
            placeholder="Search"
            className="mr-sm-2"
            value={searchText}
            onChange={handleSearchTextChange}
          />
          <Button
            style={{ marginLeft: "10px" }}
            variant="outline-success"
            // onClick={handleSubmit}
          >
            Search
          </Button>
        </Col>

        <Nav>
          <Nav.Link
            as={NavLink}
            to="/signin"
            style={{
              marginTop: "3px",
              marginRight: "10px",
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
              marginRight: "10px",
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

{
  /* <NavDropdown
            title={""}
            style={{ marginTop: "3px", marginRight: "20px", paddingRight: "0" }}
            align="end"
          >
            <NavDropdown.Item as={NavLink} to="/signup/buyer">
              Sign up
            </NavDropdown.Item>
            <NavDropdown.Item as={NavLink} to="/signin">
              Sign in
            </NavDropdown.Item>
          </NavDropdown> */
}
