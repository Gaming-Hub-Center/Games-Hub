import React from "react";
import {Container} from "react-bootstrap";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

export interface PhysicalOrderItemProps {
  title: string;
  description: string;
  count: number;
  unitPrice: number;
  totalPrice: number;
}

const PhysicalOrderItem: React.FC<PhysicalOrderItemProps> = ({ title, description, count, unitPrice, totalPrice }) => {
  return (
    <div className="text-white rounded" style={{width: "100%"}}>
      <hr className="my-3" style={{ borderTop: "3px solid #bbb" }} /> {/* Gray bold line */}
      <Row className="mb-2">
        <Col>
          <h4>{title}</h4>
        </Col>
      </Row>
      <Row className="mb-2">
        <Col>
          <p>{description}</p>
        </Col>
      </Row>
      <Row className="mb-2">
        <Col xs={12} md={4}>
          <p className="fs-5">Quantity: {count}</p> {/* fs-5 for larger text */}
        </Col>
        <Col xs={12} md={4}>
          <p className="fs-5">Unit Price: ${unitPrice.toFixed(2)}</p>
        </Col>
        <Col xs={12} md={4}>
          <p className="fs-5">Total: ${totalPrice.toFixed(2)}</p>
        </Col>
      </Row>
    </div>
  );
};

export default PhysicalOrderItem