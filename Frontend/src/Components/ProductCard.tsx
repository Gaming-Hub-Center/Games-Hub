import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";

interface cardProps {
  title: string;
  description: string;
  images: string[];
}

export function ProductCard({ title, description, images }: cardProps) {
  return (
    <Card style={{ width: "18rem", height: "38vh", marginRight: "10px" }}>
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
          <Button variant="primary">View</Button>
          <Button variant="primary">Add to cart</Button>
        </div>
      </Card.Body>
    </Card>
  );
}
