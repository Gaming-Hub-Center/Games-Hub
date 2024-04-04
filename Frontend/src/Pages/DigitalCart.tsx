import React, { useState, useEffect } from "react";
import { Button, Container, Table } from "react-bootstrap";
import { httpRequest } from "../Controller/HttpProxy";
import { getBalance, getEmail, getId, getName } from "../session/CurrentSession";
import { useNavigate } from "react-router-dom";
import { NavbarC } from "../Components/NavbarC";
import {PaymentMethod} from "../enums/PaymentMethod";

type Product = {
  productId: number;
  price: number;
  count: number;
  title: string;
  description: string;
  category: string;
  sellerId: number;
  date: number;
};

type CartItemId = {
  buyerId: number;
  productId: number;
};

type CartItem = {
  id: CartItemId;
  count: number;
  product: Product;
};

type OrderCheckoutDTO = {
  paymentMethod: string;
};

const DigitalCart = () => {
  const [cartItems, setCartItems] = useState<CartItem[]>([]);
  const [showSidebar, setShowSidebar] = useState(false);
  const [paymentMethod, setPaymentMethod] = useState(PaymentMethod.WALLET);
  const navigate = useNavigate();
  const buyerId = getId();

  useEffect(() => {
    fetchCartItems();
  }, []);

  const fetchCartItems = () => {
    httpRequest("GET", `cart/digital`)
      .then((response: any) => {
        setCartItems(response.data);
        console.log(response.data)
      })
      .catch((error) => {
        console.error(error.response.data.message);
      });
  };

  const updateItemCount = (productId: number, change: number) => {
    const cartDTO = {
      productId: productId,
      changeCount: change,
    }

    httpRequest("POST", "/cart/digital/addOrUpdate", cartDTO)
      .then(() => {
        fetchCartItems();
      })
      .catch((error) => {
        console.error(error.response.data.message);
      });
  };

  const onDeleteItem = (productId: number) => {
    httpRequest("DELETE", `/cart/digital/remove/${productId}`)
      .then(() => {
        fetchCartItems();
      })
      .catch((error) => {
        console.error(error.response.data.message);
      });
  };

  // The UI handlers for add and remove now call updateItemCount with appropriate change values
  const onAddItem = (productId: number) => {
    updateItemCount(productId, 1); // Increase count
  };

  const onRemoveItem = (productId: number) => {
    updateItemCount(productId, -1); // Decrease count but not below 1
  };

  const checkout = () => {
    const orderCheckoutDTO: OrderCheckoutDTO = {
      paymentMethod: paymentMethod,
    };
    console.log(orderCheckoutDTO);
    httpRequest("POST", "order/checkout/digital", orderCheckoutDTO)
      .then((response: any) => {
        alert(response.data);
        navigate("/buyer/orders");
      })
      .catch((error) => {
        alert(error.response.data.message);
      });
  };

  return (
    <>
      <div style={{ backgroundColor: "#121212", color: "white" }}>
        <NavbarC
          productType="your_product_type"
          updateProductCardPropsList={() => {}}
        />
        <Container
          style={{
            position: "relative",
            maxWidth: "1200px",
            height: "92vh",
            margin: "0 auto",
            padding: "20px",
          }}
        >
          {/* User Info Sidebar */}
          <div
            style={{
              backgroundColor: "#1A1A1A",
              padding: "20px",
              borderRadius: "8px",
              width: "350px",
              position: "fixed",
              right: showSidebar ? "0" : "-350px",
              top: "0",
              height: "100%",
              transition: "right 0.3s",
              overflowY: "auto",
              boxShadow: "0 4px 8px 0 rgba(0,0,0,0.2)",
              zIndex: 1000,
              marginTop: "8vh",
            }}
          >
            <h2
              style={{
                color: "#733BC0",
                textAlign: "center",
                marginBottom: "20px",
                fontSize: "24px",
                fontWeight: "bold",
              }}
            >
              User Information
            </h2>
            {
              <>
                <p style={{ color: "#CCC", marginBottom: "10px" }}>
                  <strong>Name:</strong> {getName()}
                </p>
                <p style={{ color: "#CCC", marginBottom: "10px" }}>
                  <strong>Email:</strong> {getEmail()}
                </p>
                <p style={{ color: "#CCC", marginBottom: "20px" }}>
                  <strong>Balance:</strong> ${getBalance().toFixed(2)}
                </p>
              </>
            }
            {/* Payment Method Selection */}
            <div
              style={{
                marginTop: "20px",
                borderTop: "1px solid #333",
                paddingTop: "20px",
              }}
            >
              <h3 style={{ color: "white", marginBottom: "15px" }}>
                Payment Method
              </h3>
              <div style={{ marginBottom: "10px" }}>
                <input
                  type="radio"
                  id="wallet"
                  name="paymentMethod"
                  value="wallet"
                  checked={paymentMethod === PaymentMethod.WALLET}
                  onChange={() => setPaymentMethod(PaymentMethod.WALLET)}
                />
                <label
                  htmlFor="wallet"
                  style={{ color: "white", marginLeft: "8px" }}
                >
                  Wallet
                </label>
              </div>
              <div style={{ marginBottom: "20px" }}>
                <input
                  type="radio"
                  id="cod"
                  name="paymentMethod"
                  value="cod"
                  checked={paymentMethod === PaymentMethod.COD}
                  onChange={() => setPaymentMethod(PaymentMethod.COD)}
                />
                <label
                  htmlFor="cod"
                  style={{ color: "white", marginLeft: "8px" }}
                >
                  Cash on Delivery
                </label>
              </div>
              <Button
                variant="success"
                onClick={() => checkout()}
                style={{
                  marginTop: "10px",
                  width: "100%",
                  fontWeight: "bold",
                  letterSpacing: "1px",
                  backgroundColor: "#733BC0",
                }}
              >
                Confirm
              </Button>
            </div>
          </div>

          {/* Main Content */}
          <h1
            style={{
              color: "#733BC0",
              textAlign: "center",
              marginBottom: "30px",
            }}
          >
            Digital Products Shopping Cart
          </h1>
          <Table
            striped
            bordered
            hover
            variant="dark"
            style={{ opacity: "0.85" }}
          >
            <thead>
              <tr>
                <th>Item Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {cartItems.map((item) => (
                <tr key={item.id.productId}>
                  <td>{item.product.title}</td>
                  <td>${item.product.price.toFixed(2)}</td>
                  <td>{item.count}</td>
                  <td>${(item.product.price * item.count).toFixed(2)}</td>
                  <td>
                    <Button
                      variant="success"
                      onClick={() => onAddItem(item.id.productId)}
                      className="me-2"
                    >
                      +
                    </Button>
                    <Button
                      variant="warning"
                      onClick={() => onRemoveItem(item.id.productId)}
                      disabled={item.count === 1}
                      className="me-2"
                    >
                      -
                    </Button>
                    <Button
                      variant="danger"
                      onClick={() => onDeleteItem(item.id.productId)}
                    >
                      Delete
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              marginTop: "20px",
              fontSize: "1.2rem",
            }}
          >
            <strong>Total Price: </strong>
            <span>
              $
              {cartItems
                .reduce((acc, item) => acc + item.product.price * item.count, 0)
                .toFixed(2)}
            </span>
            <Button
              size="lg"
              style={{
                backgroundColor: "#733BC0",
                color: "#f0f0f0",
                borderColor: "#733BC0",
                borderRadius: "5px",
                cursor: "pointer",
                transition: "background-color 0.3s",
                marginLeft: "10px",
              }}
              onMouseEnter={(e) =>
                (e.currentTarget.style.backgroundColor =
                  "rgba(115,	59,	192 ,0.5)")
              }
              onMouseLeave={(e) =>
                (e.currentTarget.style.backgroundColor = "#733BC0")
              }
              onClick={() => setShowSidebar(!showSidebar)}
            >
              Checkout
            </Button>
          </div>
        </Container>
      </div>
    </>
  );
};

export default DigitalCart;
