import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import { useNavigate } from "react-router-dom";
import { formatCurrency } from "../Utilities/formatCurrency";
import {httpRequest} from "../Controller/HttpProxy";
import {getCurrentProductPage, getId} from "../CurrentSession";
import React from "react";

export interface cardProps {
  id: number;
  title: string;
  description: string;
  url: string;
  price: number;
  productType: string;
}


export function ProductCard({ id, title, description, url, price, productType }: cardProps) {
  const navigate = useNavigate();

  function goToViewProduct(
    e: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void {
    navigate(`/buyer/productview/${id}`, {
      state: { productType },
    });
  }

  function addToCart(e: React.MouseEvent<HTMLButtonElement, MouseEvent>): void {
    const cartDTO = {
      buyerID: getId(),
      productID: id,
      count: 1,
    };
    if(productType === "digital") {
      httpRequest("post", "/cart/digital/addOrUpdate", cartDTO)
      .then(() => {
        alert("added successfully");
      })
      .catch((error) => {
        console.error("Error updating cart item:", error);
      });
    }
    else {
      httpRequest("post", "/cart/physical/addOrUpdate", cartDTO)
      .then(() => {
        alert("added successfully");
      })
      .catch((error) => {
        console.error("Error updating cart item:", error);
      });
    }
  }

  function addToWishlist(
    e: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void {
    const wishlistDTO = {
      buyerID: getId(),
      productID: id,
    };
    if(productType === "digital") {
      httpRequest("post", "/wishlist/digital/add", wishlistDTO)
      .then(() => {
        alert("added successfully");
      })
      .catch(() => {
        alert("item is already added")
      });
    }
    else {
      httpRequest("post", "/wishlist/physical/add", wishlistDTO)
      .then(() => {
        alert("added successfully");
      })
      .catch((error) => {
        alert("item is already added")
      });
    }
  }

  return (
    <Card
      style={{
        width: "18rem",
        height: "38vh",
        marginRight: "10px",
        marginBottom: "10px",
        backgroundColor: "black",
        color: "#f0f0f0",
      }}
    >
      <Card.Img
        variant="top"
        src={url}
        style={{ height: "40%", objectFit: "cover" }}
      />
      <Card.Body
        style={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "space-between",
        }}
      >
        <Card.Title className="d-flex justify-content-between align-items-baseline">
          <span
            style={{
              overflow: "hidden",
              whiteSpace: "nowrap",
              textOverflow: "ellipsis",
              height: "2.6vh",
              display: "inline-block",
            }}
          >
            {title}
          </span>
          <span className="ms-2 text-muted ">{formatCurrency(price)}</span>
        </Card.Title>
        <Card.Text
          style={{
            height: "10vh",
            overflow: "auto",
          }}
        >
          {description}
        </Card.Text>
        <div
          style={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-between",
          }}
        >
          <Button
            style={{
              backgroundColor: "#733BC0",
              color: "#f0f0f0",
              borderColor: "#733BC0",
              borderRadius: "5px",
              cursor: "pointer",
              transition: "background-color 0.3s",
            }}
            onMouseEnter={(e) =>
              (e.currentTarget.style.backgroundColor = "rgba(115,	59,	192 ,0.5)")
            }
            onMouseLeave={(e) =>
              (e.currentTarget.style.backgroundColor = "#733BC0")
            }
            onClick={goToViewProduct}
          >
            View
          </Button>
          <Button
            style={{
              backgroundColor: "#733BC0",
              color: "#f0f0f0",
              borderColor: "#733BC0",
              borderRadius: "5px",
              cursor: "pointer",
              transition: "background-color 0.3s",
            }}
            onMouseEnter={(e) =>
              (e.currentTarget.style.backgroundColor = "rgba(115,	59,	192 ,0.5)")
            }
            onMouseLeave={(e) =>
              (e.currentTarget.style.backgroundColor = "#733BC0")
            }
            onClick={addToCart}
          >
            Add to cart
          </Button>
          <Button
            style={{
              backgroundColor: "#733BC0",
              color: "#f0f0f0",
              borderColor: "#733BC0",
              borderRadius: "5px",
              cursor: "pointer",
              transition: "background-color 0.3s",
            }}
            onMouseEnter={(e) =>
              (e.currentTarget.style.backgroundColor = "rgba(115,	59,	192 ,0.5)")
            }
            onMouseLeave={(e) =>
              (e.currentTarget.style.backgroundColor = "#733BC0")
            }
            onClick={addToWishlist}
          >
            Add to wishlist
          </Button>
        </div>
      </Card.Body>
    </Card>
  );
}
