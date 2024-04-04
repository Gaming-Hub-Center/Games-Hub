<<<<<<< Updated upstream
// PhysicalCart.tsx
import React, { useState, useEffect } from 'react';
import { Button, Table } from 'react-bootstrap';
import { httpRequest } from '../Controller/HttpProxy';
import { getId } from "../CurrentSession";


// Define the types for the data structure
type Buyer = {
  id: number;
  name: string;
  email: string;
  phone: string;
  address: string;
  balance: number;
};
=======
import React, { useState, useEffect } from "react";
import { Button, Container, Table } from "react-bootstrap";
import { httpRequest } from "../Controller/HttpProxy";
import { getBalance, getEmail, getId, getName } from "../session/CurrentSession";
import { useNavigate } from "react-router-dom";
import { NavbarC } from "../Components/NavbarC";
import {PaymentMethod} from "../enums/PaymentMethod";
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
const PhysicalCart = () => {
    const [cartItems, setCartItems] = useState<CartItem[]>([]);
    const buyerId = getId(); 
  
    useEffect(() => {
      fetchCartItems();
    }, []);
  
    const fetchCartItems = () => {
      httpRequest('get', `/cart/physical?buyerId=${buyerId}`)
        .then(response => {
          setCartItems(response.data);
        })
        .catch(error => {
          console.error('Error fetching cart items:', error);
        });
    };
  
    const updateItemCount = (productId: number, change: number) => {
      // Find the current item to get its count
      const currentItem = cartItems.find(item => item.id.productID === productId);
      if (!currentItem) {
        return; // or handle error
      }
  
      // Calculate the new count, ensuring it doesn't drop below 1
      const newCount = currentItem.count + change >= 1 ? currentItem.count + change : 1;
  
      const cartDTO = {
        buyerID: buyerId,
        productID: productId,
        count: newCount
      };
  
      httpRequest('post', '/cart/physical/addOrUpdate', cartDTO)
        .then(() => {
          fetchCartItems(); // Refresh the cart items to reflect the updated count
        })
        .catch(error => {
          console.error('Error updating cart item:', error);
        });
    };

    const onDeleteItem = (productId: number) => {
        httpRequest('delete', `/cart/physical/remove?buyerId=${buyerId}&productId=${productId}`)
          .then(() => {
            // If the delete is successful, fetch the latest cart items
            fetchCartItems();
          })
          .catch(error => {
            console.error('Error deleting cart item:', error);
          });
      };
  
    // The UI handlers for add and remove now call updateItemCount with appropriate change values
    const onAddItem = (productId: number) => {
      updateItemCount(productId, 1); // Increase count
    };
  
    const onRemoveItem = (productId: number) => {
      updateItemCount(productId, -1); // Decrease count but not below 1
    };
          // State to manage the sidebar visibility
    const [showSidebar, setShowSidebar] = useState(false);
  
  
    return (
        <div style={{ backgroundColor: '#121212', minHeight: '100vh', color: 'white' }}>
          <div style={{ position: 'relative', maxWidth: '1200px', margin: '0 auto', padding: '20px' }}>
            {/* Toggle Sidebar Button */}
            <Button variant="secondary" onClick={() => setShowSidebar(!showSidebar)} style={{ marginBottom: '10px' }}>
              {showSidebar ? 'Hide' : 'Show'} User Info
=======
type OrderCheckoutDTO = {
  paymentMethod: string;
};

const PhysicalCart = () => {
  const [cartItems, setCartItems] = useState<CartItem[]>([]);
  const [showSidebar, setShowSidebar] = useState(false);
  const [paymentMethod, setPaymentMethod] = useState(PaymentMethod.WALLET);
  const navigate = useNavigate();
  const buyerId = getId();

  useEffect(() => {
    fetchCartItems();
  }, []);

  const fetchCartItems = () => {
    httpRequest("GET", `cart/physical`)
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

    httpRequest("POST", "/cart/physical/addOrUpdate", cartDTO)
      .then(() => {
        fetchCartItems();
      })
      .catch((error) => {
        console.error(error.response.data.message);
      });
  };

  const onDeleteItem = (productId: number) => {
    httpRequest("DELETE", `/cart/physical/remove/${productId}`)
      .then(() => {
        fetchCartItems();
      })
      .catch((error) => {
        console.error(error.response.data.message);
      });
  };

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
    httpRequest("POST", "order/checkout/physical", orderCheckoutDTO)
      .then((response: any) => {
        alert(response.data);
        navigate("/buyer/orders");
      })
      .catch((error) => {
        alert(error.response.data.message);
      });
  };

  return (
    <div
      style={{ backgroundColor: "#121212", minHeight: "100vh", color: "white" }}
    >
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
                style={{color: "white", marginLeft: "8px"}}
              >
                Wallet
              </label>
            </div>
            <div style={{marginBottom: "20px"}}>
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
                style={{color: "white", marginLeft: "8px"}}
              >
                Cash on Delivery
              </label>
            </div>
            <Button
              variant="success"
              onClick={() => checkout()}
              style={{
                backgroundColor: "#733BC0",
                color: "#f0f0f0",
                borderColor: "#733BC0",
                borderRadius: "5px",
                cursor: "pointer",
                transition: "background-color 0.3s",
                width: "100%"
              }}
              onMouseEnter={(e) =>
                (e.currentTarget.style.backgroundColor =
                  "rgba(115,	59,	192 ,0.5)")
              }
              onMouseLeave={(e) =>
                (e.currentTarget.style.backgroundColor = "#733BC0")
              }
            >
              Confirm
>>>>>>> Stashed changes
            </Button>
      
            {/* User Info Sidebar */}
            <div style={{
              backgroundColor: '#1A1A1A',
              padding: '15px',
              borderRadius: '5px',
              width: '250px',
              position: 'fixed', // Fixed position
              right: showSidebar ? '0' : '-250px', // Slide in and out
              top: '0',
              height: '100%', // Full height
              transition: 'right 0.3s', // Smooth transition for sliding
              overflowY: 'auto' // Add scroll for long content
            }}>
              <h2 style={{ color: '#76B900' }}>User Information</h2>
              {cartItems[0] && (
                <>
                  <p><strong>Name:</strong> {cartItems[0].buyer.name}</p>
                  <p><strong>Email:</strong> {cartItems[0].buyer.email}</p>
                  <p><strong>Balance:</strong> ${cartItems[0].buyer.balance.toFixed(2)}</p>
                </>
              )}
            </div>
      
            {/* Main Content */}
            <h1 style={{ color: '#76B900', textAlign: 'center', marginBottom: '30px' }}>
              Physical Products Shopping Cart
            </h1>
            <Table striped bordered hover variant="dark" style={{ opacity: '0.85' }}>
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
                      <tr key={item.id.productID}>
                        <td>{item.product.title}</td>
                        <td>${item.product.price.toFixed(2)}</td>
                        <td>{item.count}</td>
                        <td>${(item.product.price * item.count).toFixed(2)}</td>
                        <td>
                          <Button variant="success" onClick={() => onAddItem(item.id.productID)} className="me-2">
                            +
                          </Button>
                          <Button variant="warning" onClick={() => onRemoveItem(item.id.productID)} disabled={item.count === 1} className="me-2">
                            -
                          </Button>
                          <Button variant="danger" onClick={() => onDeleteItem(item.id.productID)}>
                            Delete
                          </Button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
            </Table>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginTop: '20px', fontSize: '1.2rem' }}>
              <strong>Total Price: </strong>
              <span>${cartItems.reduce((acc, item) => acc + item.product.price * item.count, 0).toFixed(2)}</span>
              <Button variant="primary" size="lg">
                Checkout
              </Button>
            </div>
          </div>
        </div>
<<<<<<< Updated upstream
      );
      
    };
    
  
  export default PhysicalCart;
=======

        {/* Main Content */}
        <h1
          style={{
            color: "#733BC0",
            textAlign: "center",
            marginBottom: "30px",
          }}
        >
          Physical Products Shopping Cart
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
              (e.currentTarget.style.backgroundColor = "rgba(115,	59,	192 ,0.5)")
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
  );
};

export default PhysicalCart;
>>>>>>> Stashed changes
