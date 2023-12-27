import {useEffect, useState} from "react";
import {ProductBriefDTO} from "../../Controller/DTO/ProductDTO/ProductBriefDTO";
import {httpRequest} from "../../Controller/HttpProxy";
import {Card, Container, Pagination} from "react-bootstrap";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import {useParams} from "react-router-dom";
import Button from "react-bootstrap/Button";


export function SellerCatalogView() {
    const { sellerId } = useParams();

    const [physicalProducts, setPhysicalProducts] = useState<ProductBriefDTO[]>([]);
    const [digitalProducts, setDigitalProducts] = useState<ProductBriefDTO[]>([]);


    const [currentDigitalPage, setCurrentDigitalPage] = useState(1);
    const digitalItemsPerPage = 4; // Assuming 4 items per page
    const totalDigitalPages = Math.ceil(digitalProducts.length / digitalItemsPerPage);
    const digitalStartIndex = (currentDigitalPage - 1) * digitalItemsPerPage;
    const digitalEndIndex = digitalStartIndex + digitalItemsPerPage;
    const currentDigitalProducts = digitalProducts.slice(digitalStartIndex, digitalEndIndex);


    const [currentPhysicalPage, setCurrentPhysicalPage] = useState(1);
    const physicalItemsPerPage = 4; // Assuming 4 items per page
    const totalPhysicalPages = Math.ceil(physicalProducts.length / physicalItemsPerPage);
    const physicalStartIndex = (currentPhysicalPage - 1) * physicalItemsPerPage;
    const physicalEndIndex = physicalStartIndex + physicalItemsPerPage;
    const currentPhysicalProducts = physicalProducts.slice(physicalStartIndex, physicalEndIndex);

    const handlePhysicalPageChange = (pageNumber) => {
        setCurrentPhysicalPage(pageNumber);
    };

    const handleDigitalPageChange = (pageNumber) => {
        setCurrentDigitalPage(pageNumber);
    };

    const renderPaginationItems = (totalPages, currentPage, setPage) => {
        let items = [];
        for (let number = 1; number <= totalPages; number++) {
            items.push(
                <Pagination.Item key={number} active={number === currentPage} onClick={() => setPage(number)}>
                    {number}
                </Pagination.Item>
            );
        }
        return items;
    };

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

    const deleteProduct = async(productId: number, productType: string) => {
        const response = await httpRequest("DELETE", `/admin/delete/${productType}/product/${productId}`)
        alert(response.data)
            if(productType === "physical")
                setPhysicalProducts(physicalProducts.filter(product => product.id !== productId));
            else
                setDigitalProducts(digitalProducts.filter(product => product.id !== productId));
    }

    return (
        <Container fluid style={{ backgroundColor: 'darkslateblue', color: 'white', height: '100vh', overflow: "auto", padding: '20px' }}>
            <Row>
                <Col md={6} style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', width: '45%'}}>
                    <h3 style={{ width: '100%', textAlign: 'center' }} >Digital Products</h3>
                    {currentDigitalProducts.reduce((acc, product, index) => {
                        const rowNumber = Math.floor(index / 2);
                        if (!acc[rowNumber]) { acc[rowNumber] = []; }
                        acc[rowNumber].push(product);
                        return acc;
                    }, []).map((productRow, rowIndex) => (
                        <Row key={rowIndex}>
                            {productRow.map((product, productIndex) => (
                                <Col md={6} key={productIndex} style={{width: '420px'}}>
                                    <Card style={{ backgroundColor: 'black', color: 'white', height: '35vh', width: '100%', margin: '10px' }}>
                                        <Card.Header as="h5" style={{ backgroundColor: '#733BC0', color: 'black', height: '4vh' }}>{product.title}</Card.Header>
                                        <Card.Body>
                                            <Card.Img variant="top" src={`data:image/jpeg;base64,${product.image}`}></Card.Img>
                                            <Card.Title>Price: {product.price}</Card.Title>
                                            <Card.Text><strong>{product.description}</strong></Card.Text>
                                            <Card.Text><strong>{product.id}</strong></Card.Text>
                                            <Button
                                                style={{backgroundColor: "#733BC0", color: "#f0f0f0", borderColor: "#733BC0", borderRadius: "5px", cursor: "pointer", transition: "background-color 0.3s", height: '4vh'}}
                                                onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = "rgba(115,	59,	192 ,0.5)")}
                                                onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = "#733BC0")}
                                                onClick={() => deleteProduct(product.id, product.productType)}
                                            >
                                                Delete Product
                                            </Button>
                                        </Card.Body>
                                    </Card>
                                </Col>
                            ))}
                        </Row>
                    ))}
                    <Row>
                        <Col style={{ height: "8vh", display: "flex", justifyContent: "center",}}>
                            <Pagination style={{ justifyContent: 'center' }}>
                                <Pagination.First onClick={() => handleDigitalPageChange(1)} disabled={currentDigitalPage === 1} />
                                <Pagination.Prev onClick={() => handleDigitalPageChange(Math.max(1, currentDigitalPage - 1))} disabled={currentDigitalPage === 1} />
                                {renderPaginationItems(totalDigitalPages, currentDigitalPage, setCurrentDigitalPage)}
                                <Pagination.Next onClick={() => handleDigitalPageChange(Math.min(totalDigitalPages, currentDigitalPage + 1))} disabled={currentDigitalPage === totalDigitalPages} />
                                <Pagination.Last onClick={() => handleDigitalPageChange(totalDigitalPages)} disabled={currentDigitalPage === totalDigitalPages} />
                            </Pagination>
                        </Col>
                    </Row>
                </Col>
                <Col md={6} style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' , width: '50%', marginLeft: '90px'}}>
                    <h3 style={{ width: '100%', textAlign: 'center' }}>Physical Products</h3>
                    {currentPhysicalProducts.reduce((acc, product, index) => {
                        const rowNumber = Math.floor(index / 2);
                        if (!acc[rowNumber]) { acc[rowNumber] = []; }
                        acc[rowNumber].push(product);
                        return acc;
                    }, []).map((productRow, rowIndex) => (
                        <Row key={rowIndex}>
                            {productRow.map((product, productIndex) => (
                                <Col md={6} key={productIndex} style={{width: '420px'}}>
                                    <Card style={{ backgroundColor: 'black', color: 'white', height: '35vh', minWidth: '100%', maxWidth: '100%', margin: '10px' }}>
                                        <Card.Header as="h5" style={{ backgroundColor: '#733BC0', color: 'black', height: '4vh' }}>{product.title}</Card.Header>
                                        <Card.Body>
                                            <Card.Img variant="top" src={`data:image/jpeg;base64,${product.image}`}></Card.Img>
                                            <Card.Title>Price: {product.price}</Card.Title>
                                            <Card.Text><strong>{product.description}</strong></Card.Text>
                                            <Card.Text><strong>{product.id}</strong></Card.Text>
                                            <Button
                                                style={{backgroundColor: "#733BC0", color: "#f0f0f0", borderColor: "#733BC0", borderRadius: "5px", cursor: "pointer", transition: "background-color 0.3s", height: '4vh'}}
                                                onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = "rgba(115,	59,	192 ,0.5)")}
                                                onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = "#733BC0")}
                                                onClick={() => deleteProduct(product.id, product.productType)}
                                            >
                                                Delete Product
                                            </Button>
                                        </Card.Body>
                                    </Card>
                                </Col>
                            ))}
                        </Row>
                    ))}
                    <Row>
                        <Col style={{ height: "8vh", display: "flex", justifyContent: "center",}}>
                            <Pagination style={{ justifyContent: 'center' }}>
                                <Pagination.First onClick={() => handlePhysicalPageChange(1)} disabled={currentPhysicalPage === 1} />
                                <Pagination.Prev onClick={() => handlePhysicalPageChange(Math.max(1, currentPhysicalPage - 1))} disabled={currentPhysicalPage === 1} />
                                {renderPaginationItems(totalPhysicalPages, currentPhysicalPage, setCurrentPhysicalPage)}
                                <Pagination.Next onClick={() => handlePhysicalPageChange(Math.min(totalPhysicalPages, currentPhysicalPage + 1))} disabled={currentPhysicalPage === totalPhysicalPages} />
                                <Pagination.Last onClick={() => handlePhysicalPageChange(totalPhysicalPages)} disabled={currentPhysicalPage === totalPhysicalPages} />
                            </Pagination>
                        </Col>
                    </Row>
                </Col>
            </Row>
        </Container>
    );

}