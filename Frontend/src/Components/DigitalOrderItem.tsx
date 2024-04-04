import React, { useState } from "react";
import { ListGroup, Row, Col, Button } from "react-bootstrap";

export interface DigitalOrderItemProps {
  title: string;
  description: string;
  count: number;
  unitPrice: number;
  totalPrice: number;
  codes: string[];
}

const DigitalOrderItem: React.FC<DigitalOrderItemProps> = ({ title, description, count, unitPrice, totalPrice, codes }) => {
  const [currentIndex, setCurrentIndex] = useState(0);

  const handlePrev = () => {
    setCurrentIndex((prevIndex) => (prevIndex > 0 ? prevIndex - 1 : prevIndex));
  };

  const handleNext = () => {
    setCurrentIndex((prevIndex) => (prevIndex < codes.length - 1 ? prevIndex + 1 : prevIndex));
  };

  return (
    <div className="text-white rounded" style={{ width: "100%" }}>
      <hr className="my-3" style={{ borderTop: "3px solid #bbb" }}/>
      {/* Gray bold line */}
      <Row className="mb-2 justify-content-center align-items-center">
        <Col>
          <h4>{title}</h4>
        </Col>
      </Row>
      <Row className="mb-2 justify-content-center align-items-center">
        <Col>
          <p>{description}</p>
        </Col>
      </Row>
      <Row className="mb-2 justify-content-center align-items-center">
        <Col xs="auto" md={4}>
          <p className="fs-5">Quantity: {count}</p>
        </Col>
        <Col xs="auto" md={4}>
          <p className="fs-5">Unit Price: ${unitPrice.toFixed(2)}</p>
        </Col>
        <Col xs="auto" md={4}>
          <p className="fs-5">Total: ${totalPrice.toFixed(2)}</p>
        </Col>
      </Row>
      <Row className="mb-2 justify-content-center align-items-center">
        <Col xs="auto">
          <Button style={{backgroundColor: "#733BC0"}} variant="secondary" onClick={handlePrev}>&lt;</Button>
        </Col>
        <Col xs="auto" style={{marginTop: "15px"}}>
          <p className="fs-5">{codes.length > 0 ? `#${codes[currentIndex]}` : "No Codes"}</p>
        </Col>
        <Col xs="auto">
          <Button style={{backgroundColor: "#733BC0"}} variant="secondary" onClick={handleNext}>&gt;</Button>
        </Col>
      </Row>
    </div>
  );
};

export default DigitalOrderItem;
