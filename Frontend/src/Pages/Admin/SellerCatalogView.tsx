import {useEffect, useState} from "react";
import {ProductBriefDTO} from "../../Controller/DTO/ProductDTO/ProductBriefDTO";
import {httpRequest} from "../../Controller/HttpProxy";
import {Card, Container} from "react-bootstrap";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import {useParams} from "react-router-dom";


export function SellerCatalogView() {
    const { sellerId } = useParams();
    const [physicalProducts, setPhysicalProducts] = useState<ProductBriefDTO[]>([]);
    const [digitalProducts, setDigitalProducts] = useState<ProductBriefDTO[]>([]);


    useEffect(() => {
        const fetchData = async () => {
            const response = await getAllPhysical()
            setPhysicalProducts(response)
            const response2 = await getAllDigital()
            setDigitalProducts(response2);
        };
        fetchData();
    }, []);

    const getAllPhysical = async() => {
        const response = await httpRequest("GET", `/admin/view/physical/products/${sellerId}`);
        return response.data as ProductBriefDTO[]
    }

    const getAllDigital = async() => {
        const response = await httpRequest("GET", `/admin/view/digital/products/${sellerId}`);
        return response.data as ProductBriefDTO[]
    }




    return (
        <Container fluid style={{ backgroundColor: 'darkslateblue', color: 'white', height: '100vh', overflow: "auto", padding: '20px' }}>
            <Row>
                <Col md={6}>
                    <h3>Digital Products</h3>
                    {digitalProducts.map((product, index) => (
                        <Col key={index} md={4} className="mb-4">
                            <Card style={{ backgroundColor: 'black' }}>
                                <Card.Img variant="top" src={product.image} />
                                <Card.Body>
                                    <Card.Title>{product.title}</Card.Title>
                                    <Card.Text>{product.description}</Card.Text>
                                    <Card.Text>Price: ${product.price}</Card.Text>
                                </Card.Body>
                            </Card>
                        </Col>
                    ))}
                </Col>
                <Col md={6}>
                    <h3>Physical Products</h3>
                    {physicalProducts.map((product, index) => (
                        <Col key={index} md={3} style={{margin: '8px', width: '20%'}}  >
                            <Card className="mb-3" style={{ backgroundColor: 'black' }}>
                                <Card.Img variant="top" src={product.image} />
                                <Card.Body>
                                    <Card.Title>{product.title}</Card.Title>
                                    <Card.Text>{product.description}</Card.Text>
                                    <Card.Text>Price: ${product.price}</Card.Text>
                                </Card.Body>
                            </Card>
                        </Col>
                    ))}
                </Col>
            </Row>
        </Container>
    );
}