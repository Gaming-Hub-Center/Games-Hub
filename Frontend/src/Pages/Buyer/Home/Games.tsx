import React from "react";
import { Container, Row, Col, Card, Button, Image } from "react-bootstrap";
import { NavbarC } from "../../../Components/NavbarC";
import { Route, Routes } from "react-router-dom";

export function HomeGames() {
  return (
    <>
      <Container
        fluid
        style={{ height: "100vh", overflow: "hidden", padding: 0 }}
      >
        <Row style={{ height: "8vh", margin: 0 }}>
          <Col
            md={1}
            style={{
              backgroundColor: "rgb(0, 0, 0)",
              display: "flex",
              maxHeight: "100%",
              padding: 0,
            }}
          >
            <Image
              src="/src/data/logo.png"
              alt="Logo"
              style={{ maxWidth: "100%", maxHeight: "100%" }}
            />
          </Col>
          <Col
            md={11}
            style={{
              backgroundColor: "rgb(0, 0, 0)",
              maxHeight: "100%",
              padding: 0,
              margin: 0
            }}
          >
            <NavbarC></NavbarC>
          </Col>
        </Row>
        <Row style={{ display: "flex", height: "90vh", margin: 0 }}>
          <Col xs={12} sm={6} md={4} lg={3}></Col>
          {/* Add more columns with games cards for the grid layout */}
        </Row>
      </Container>
    </>
  );
}
