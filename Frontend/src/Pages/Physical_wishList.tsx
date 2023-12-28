// PhysicalWishlist.tsx
import React, { useState, useEffect } from "react";
import { Button, Container, Table } from "react-bootstrap";
import { httpRequest } from "../Controller/HttpProxy";
import { getId } from "../CurrentSession";
import { NavbarC } from "../Components/NavbarC";

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
  id: number;
  title: string;
  description: string;
  category: string;
  price: number;
  sellerID: number;
  count: number;
  postDate: number[];
  code: string;
};

type WishlistItem = {
  id: {
    buyerID: number;
    productID: number;
  };
  buyer: Buyer;
  product: Product;
};

const PhysicalWishlist = () => {
  const [wishlistItems, setWishlistItems] = useState<WishlistItem[]>([]);
  const buyerId = getId();

  useEffect(() => {
    fetchWishlistItems();
  }, []);

  const fetchWishlistItems = () => {
    httpRequest("get", `/wishlist/physical?buyerId=${buyerId}`)
      .then((response) => {
        setWishlistItems(response.data);
      })
      .catch((error) => {
        console.error("Error fetching wishlist items:", error);
      });
  };

  const onAddToCart = (productId: number) => {
    const cartDTO = {
      buyerID: buyerId,
      productID: productId,
      count: 1,
    };
    httpRequest("post", "/cart/physical/addOrUpdate", cartDTO)
      .then(() => {
        alert(`Item with ID ${productId} added to cart!`);
      })
      .catch((error) => {
        console.error("Error updating cart item:", error);
      });
  };

  const onDeleteItem = (productId: number) => {
    const wishlistDTO = {
      buyerID: buyerId,
      productID: productId,
    };
    httpRequest("delete", `/wishlist/physical/remove`, wishlistDTO)
      .then(() => {
        fetchWishlistItems();
      })
      .catch((error) => {
        console.error("Error deleting wishlist item:", error);
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
        <h1
          style={{
            color: "#733BC0",
            textAlign: "center",
            marginBottom: "30px",
          }}
        >
          Physical Products Wishlist
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
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {wishlistItems.map((item) => (
              <tr key={item.product.id}>
                <td>{item.product.title}</td>
                <td>${item.product.price.toFixed(2)}</td>
                <td>
                  <Button
                    variant="success"
                    onClick={() => onAddToCart(item.product.id)}
                    className="me-2"
                  >
                    Add to Cart
                  </Button>
                  <Button
                    variant="danger"
                    onClick={() => onDeleteItem(item.product.id)}
                  >
                    Remove
                  </Button>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </Container>
    </div>
  );
};

export default PhysicalWishlist;
