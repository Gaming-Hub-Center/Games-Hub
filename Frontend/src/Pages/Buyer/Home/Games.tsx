import React from "react";
import { Container, Row, Col, Card, Button, Image } from "react-bootstrap";
import { NavbarC } from "../../../Components/NavbarC";
import { ProductCard } from "../../../Components/ProductCard";

export function HomeGames() {
  const productCardPropsList = [
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
  ];

  return (
    <>
      <Container fluid style={{ height: "100vh", padding: 0 }}>
        <Row style={{ height: "5vh", margin: 0 }}>
          <Col
            md={12}
            style={{
              backgroundColor: "rgb(0, 0, 0)",
              maxHeight: "5vh",
              padding: 0,
              margin: 0,
            }}
          >
            <NavbarC></NavbarC>
          </Col>
        </Row>
        <Row style={{ display: "flex", height: "95vh" }}>
          <Col md={2} >
          
          
          </Col>
          <Col md={10} style={{ height: "5vh" }}></Col>
          {productCardPropsList.map((productCardProps, index) => (
            <ProductCard key={index} {...productCardProps} />
          ))}
          <Col></Col>
        </Row>
      </Container>
    </>
  );
}
