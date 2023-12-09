import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";

export function ProductCard() {
  return (
    <Card style={{ width: "18rem", height: "38vh", marginRight: "10px" }}>
      <Card.Img variant="top" src="/src/data/GTAV.jpg" />
      <Card.Body>
        <Card.Title>Card Title</Card.Title>
        <Card.Text>
          Some quick example text to build on the card title and make up the
          bulk of the card's content.
        </Card.Text>
        <Button variant="primary">Go somewhere</Button>
      </Card.Body>
    </Card>
  );
}
