import React from "react";
import { Container, Row, Col, Card, Button, Image } from "react-bootstrap";
import { NavbarC } from "../../../Components/NavbarC";
import { Route, Routes } from "react-router-dom";

export function HomeAccessories() {
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
        <Row style={{ display: "flex", height: "90vh", margin: 0 }}>
          <Col xs={12} sm={6} md={4} lg={3}></Col>
          {/* Add more columns with games cards for the grid layout */}
        </Row>
      </Container>
    </>
  );
}
