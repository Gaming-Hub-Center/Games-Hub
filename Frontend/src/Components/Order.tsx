import React from "react";
import PhysicalOrderItem, {PhysicalOrderItemProps} from "./PhysicalOrderItem";
import DigitalOrderItem, {DigitalOrderItemProps} from "./DigitalOrderItem";
import Card from "react-bootstrap/Card";

interface OrderProps {
  id: number;
  orderDate: number[];
  orderPrice: number;
  paymentMethod: string;
  orderStatus: string;
  physicalOrderItems: PhysicalOrderItemProps[];
  digitalOrderItems: DigitalOrderItemProps[];
}

const Order: React.FC<OrderProps> = ({ id, orderDate, orderPrice, paymentMethod, orderStatus, physicalOrderItems, digitalOrderItems }) => {
  const formattedDate = `${orderDate[2]}/${orderDate[1]}/${orderDate[0]}`
  console.log(orderDate)
  return (
    <Card className="mb-3"
      style={{
        backgroundColor: 'black',
        color: 'white',
        width: '80%',
        marginTop: '15px',
        marginBottom: '15px'
      }}
    >
      <Card.Header as="h2" style={{color: "#733BC0"}}>
        Order Placed: {formattedDate} | Order Status: {orderStatus} | Order ID: {id}
      </Card.Header>
      <Card.Body>
        <Card.Text as="h3" style={{color: "#733BC0"}}>
          Total Price: ${orderPrice.toFixed(2)} | Payment Method: {paymentMethod}
        </Card.Text>
        <div> <br/> </div>
        {physicalOrderItems.map((item, index) => (
          <PhysicalOrderItem key={index} {...item} />
        ))}
        {digitalOrderItems.map((item, index) => (
          <DigitalOrderItem key={index} {...item} />
        ))}
      </Card.Body>
    </Card>
  );
};

export default Order;