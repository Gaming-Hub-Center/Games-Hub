import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";

interface cardProps {
  title: string;
  description: string;
  images: string[];
}

export function ProductCard({ title, description, images }: cardProps) {
  function goToViewProduct(
    e: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void {}

  function addToCart(
    e: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void {}

  return (
    <Card style={{ width: "18rem", height: "38vh", marginRight: "10px" , marginBottom: "10px", backgroundColor: "black" ,color: "#f0f0f0"}}>
      <Card.Img
        variant="top"
        src={images[0]}
        style={{ height: "40%", objectFit: "cover" }}
      />
      <Card.Body
        style={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "space-between",
        }}
      >
        <Card.Title>{title}</Card.Title>
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
              border: "none",
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
              border: "none",
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
        </div>
      </Card.Body>
    </Card>
  );
}
