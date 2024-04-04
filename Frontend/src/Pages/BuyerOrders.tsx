import React, { useEffect, useState } from 'react';
import Order from "../Components/Order";
import {Container} from "react-bootstrap";
import {httpRequest} from "../Controller/HttpProxy";
import {getId} from "../session/CurrentSession";
import {NavbarC} from "../Components/NavbarC";
import {useNavigate} from "react-router-dom";

const BuyerOrders = () => {
  const [orders, setOrders] = useState([]);
  const buyerID = getId()
  const navigate = useNavigate()

  useEffect(() => {
    httpRequest('GET', "order/orders")
      .then((response: any) => {
        setOrders(response.data)
      })
      .catch(error => {
        console.log(error.response.data.message)
      })
  }, [buyerID]);

  return (
    <>
      <NavbarC productType="your_product_type" updateProductCardPropsList={() => {}} />
      <Container fluid style={{ height: "92vh", overflow: "auto", padding: 0, backgroundColor: "#121212", display: "flex", flexWrap: "wrap", justifyContent: "center" }}>
        {orders.map((order, index) => (
          <Order key={index} {...order} physicalOrderItems={order.physicalOrderItemDAOs.map(item => ({
            title: item.id.physicalProductDAO.title,
            description: item.id.physicalProductDAO.description,
            count: item.count,
            unitPrice: item.unitPrice,
            totalPrice: item.totalPrice
          }))} digitalOrderItems={order.digitalOrderItemDAOs.map(item => ({
            // Assuming similar structure for digital items
            title: item.id.digitalProductDAO.title,
            description: item.id.digitalProductDAO.description,
            count: item.count,
            unitPrice: item.unitPrice,
            totalPrice: item.totalPrice,
            codes: item.codes
          }))}
          />
          ))
        }
      </Container>
    </>
  );
};

export default BuyerOrders;
