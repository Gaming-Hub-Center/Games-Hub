import React from "react";
import { Container, Row, Col, Card, Button, Image } from "react-bootstrap";
import { NavbarC } from "../../../Components/NavbarC";
import { ProductCard } from "../../../Components/ProductCard";

export function HomeGames() {
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
          <Col md={12} style={{ height: "5vh" }}></Col>
          {/* Add more columns with games cards for the grid layout */}
          <ProductCard></ProductCard>
          <ProductCard></ProductCard>
          <ProductCard></ProductCard>
          <ProductCard></ProductCard>
          <Col></Col>
        </Row>
      </Container>
    </>
  );
}
