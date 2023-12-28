import { NavLink, useNavigate} from "react-router-dom";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import { Button, Col, Container, FormControl, Image } from "react-bootstrap";
import React, {SetStateAction, useCallback, useState} from "react";
import {httpRequest} from "../Controller/HttpProxy";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCartShopping, faHeart, faMagnifyingGlass} from "@fortawesome/free-solid-svg-icons";
import {getCurrentProductPage} from "../CurrentSession";

export function NavbarC( { productType, updateProductCardPropsList } ) {
  const [searchText, setSearchText] = useState("");
  const navigate = useNavigate();


  const handleSearchTextChange = (event: {
        target: { value: SetStateAction<string> };
    }) => {
        setSearchText(event.target.value);
    };

    const searchProducts = async (key, productType) => {
        try {
            const response = await httpRequest("GET", `/product/${productType}/search?key=${key}`);
            return response.data;
        } catch (error) {
            console.error(`Error searching product with key ${key}:`, error);
        }
    };

    const handleSearchRequest = async (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
        e.preventDefault();
        const products = await searchProducts(searchText, productType);
        console.log(products.length)
        updateProductCardPropsList(products);
    };

  function goToCart(e: React.MouseEvent<HTMLButtonElement, MouseEvent>): void {
    const path = getCurrentProductPage() === 'Physical' ? '/physical-cart' : '/digital-cart'
    navigate(path);
  }

  function goToWishlist(e: React.MouseEvent<HTMLButtonElement, MouseEvent>): void {
    navigate("/digital-wishlist");
  }

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
                        style={{
                            width: "800px",
                            backgroundColor: "#121212",
                            color: "#f0f0f0",
                            borderColor: "#f0f0f0",
                        }}
                        type="text"
                        placeholder="Search"
                        className="mr-sm-2"
                        value={searchText}
                        onChange={handleSearchTextChange}
                    />
                    <Button
                        style={{
                            backgroundColor: "#733BC0",
                            color: "#f0f0f0",
                            borderColor: "#733BC0",
                            borderRadius: "5px",
                            cursor: "pointer",
                            transition: "background-color 0.3s",
                            marginLeft: "10px",
                        }}
                        onMouseEnter={(e) =>
                            (e.currentTarget.style.backgroundColor = "rgba(115,	59,	192 ,0.5)")
                        }
                        onMouseLeave={(e) =>
                            (e.currentTarget.style.backgroundColor = "#733BC0")
                        }
                        onClick={handleSearchRequest}
                    >
                        <FontAwesomeIcon icon={faMagnifyingGlass} />
                    </Button>
                </Col>
              <Col>
                <Button style={{
                  backgroundColor: "#733BC0",
                  color: "#f0f0f0",
                  borderColor: "#733BC0",
                  borderRadius: "5px",
                  cursor: "pointer",
                  transition: "background-color 0.3s",
                  marginLeft: "10px",
                }}
                        onMouseEnter={(e) =>
                          (e.currentTarget.style.backgroundColor = "rgba(115,	59,	192 ,0.5)")
                        }
                        onMouseLeave={(e) =>
                          (e.currentTarget.style.backgroundColor = "#733BC0")
                        }
                        onClick={goToCart}
                >
                  <FontAwesomeIcon icon={faCartShopping} />
                </Button>
                <Button style={{
                  backgroundColor: "#733BC0",
                  color: "#f0f0f0",
                  borderColor: "#733BC0",
                  borderRadius: "5px",
                  cursor: "pointer",
                  transition: "background-color 0.3s",
                  marginLeft: "10px",
                }}
                        onMouseEnter={(e) =>
                          (e.currentTarget.style.backgroundColor = "rgba(115,	59,	192 ,0.5)")
                        }
                        onMouseLeave={(e) =>
                          (e.currentTarget.style.backgroundColor = "#733BC0")
                        }
                        onClick={goToWishlist}
                >
                  <FontAwesomeIcon icon={faHeart} />
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