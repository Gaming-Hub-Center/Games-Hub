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
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
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
      <Container
        fluid
        style={{
          height: "200vh",
          padding: 0,
          backgroundColor: "#121212"
        }}
      >
      <NavbarC></NavbarC>
        <Row style={{ display: "flex" , flexDirection: "row"}}>
          <Col
            md={2}
            style={{
              height: "77vh",
              marginTop: "30px",
              marginLeft: "55px",
              backgroundColor: "white",
            }}
          ></Col>
          <Col
            style={{
              height: "80vh",
              marginTop: "30px",
              display: "flex",
              flexDirection: "row",
              flexWrap: "wrap",
              justifyContent: "flex-start",
            }}
          >
             {productCardPropsList.map((productCardProps, index) => (
              <ProductCard key={index} {...productCardProps} />
            ))}
           
          </Col>
        </Row>
      </Container>
    </>
  );
}
