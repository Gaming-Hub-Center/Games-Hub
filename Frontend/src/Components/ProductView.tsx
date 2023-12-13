import { useLocation } from "react-router-dom";
import { cardProps } from "./ProductCard";
import { Container, Row, Col, Carousel, Image, Button } from "react-bootstrap";
import { NavbarC } from "./NavbarC";
import { formatCurrency } from "../Utilities/formatCurrency";
import { useState } from "react";

export function ProductView() {
  const location = useLocation();
  const { title, description, images, price } = location.state as cardProps;

  // Function to add a product to the cart
  const addToCart = () => {};

  return (
    <>
      <Container
        fluid
        style={{
          height: "100vh",
          padding: 0,
          backgroundColor: "#121212",
        }}
      >
        <NavbarC />
        <Row
          style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            flexDirection: "row",
            height: "85vh",
          }}
        >
          <Col
            md={5}
            style={{
              height: "85vh",
              marginTop: "30px",
              backgroundColor: "black",
              borderRadius: "15px",
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <Carousel
              data-bs-theme="dark"
              style={{ marginTop: "20px", height: "400px", width: "750px" }}
            >
              <Carousel.Item>
                <Image
                  className="d-block"
                  src={images[0]}
                  alt="First slide"
                  style={{
                    objectFit: "cover",
                    height: "400px",
                    width: "100%",
                  }}
                />
              </Carousel.Item>
              <Carousel.Item>
                <Image
                  className="d-block"
                  src={images[1]}
                  alt="Second slide"
                  style={{
                    objectFit: "cover",
                    height: "400px",
                    width: "100%",
                  }}
                />
              </Carousel.Item>
              <Carousel.Item>
                <Image
                  className="d-block"
                  src={images[2]}
                  alt="Third slide"
                  style={{
                    objectFit: "cover",
                    height: "400px",
                    width: "100%",
                  }}
                />
              </Carousel.Item>
            </Carousel>
            <div
              style={{ width: "90%" }}
              className="d-flex justify-content-between align-items-baseline"
            >
              <span className="fs-1" style={{ color: "#f0f0f0" }}>
                {title}
              </span>
              <span className="fs-3 text-muted ">{formatCurrency(price)}</span>
            </div>
            <span
              className="fs-5"
              style={{
                width: "90%",
                color: "#f0f0f0",
                height: "38vh",
                overflow: "auto",
                marginBottom: "15px",
              }}
            >
              {description}
            </span>
            <Button
              style={{
                width: "90%",
                height: "4vh",
                backgroundColor: "#733BC0",
                borderColor: "#733BC0",
                color: "#f0f0f0",
                marginBottom: "15px",
              }}
              onMouseEnter={(e) =>
                (e.currentTarget.style.backgroundColor =
                  "rgba(115, 59, 192, 0.6)")
              }
              onMouseLeave={(e) =>
                (e.currentTarget.style.backgroundColor = "#733BC0")
              }
              onClick={addToCart}
            >
              Add to cart
            </Button>
          </Col>
        </Row>
      </Container>
    </>
  );
}
