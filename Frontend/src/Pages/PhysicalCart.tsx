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

type Product = {
  productID: number;
  price: number;
  count: number;
  title: string;
  description: string;
  category: string;
  sellerID: number;
  date: number;
};

type CartItemId = {
  buyerID: number;
  productID: number;
};

type CartItem = {
  id: CartItemId;
  count: number;
  buyer: Buyer;
  product: Product;
};

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
      );
      
    };
    
  
  export default PhysicalCart;