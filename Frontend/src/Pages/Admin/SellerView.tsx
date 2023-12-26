import {useEffect, useState} from "react";
import { UserDTO } from "../../Controller/DTO/user/UserDTO";
import { httpRequest } from "../../Controller/HttpProxy";
import { Card, Container, Row, Col, Pagination, Button } from 'react-bootstrap';


export function SellerView() {
    const [sellersList, setSellersList] = useState<UserDTO[]>([]);
    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 6;
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const currentSellers = sellersList.slice(startIndex, endIndex);
    const totalPages = Math.ceil(sellersList.length / itemsPerPage);

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    useEffect(() => {
        const fetchData = async () => {
            const response = await getAllSellers()
            setSellersList(response)
        };
        fetchData();
    },[]);

    const getAllSellers = async() => {
        const response = await httpRequest("GET", `/admin/view/sellers`);
        return response.data as UserDTO[]
    }

    const deleteSeller = async(id: number) => {
        try {
            const response = await httpRequest("DELETE", `/admin/delete/seller/${id}`);
            alert(response.data)
            setSellersList(sellersList.filter(seller => seller.id !== id));
        } catch (error) {
            console.error(`Error deleting seller with ID ${id}:`, error);
        }
    }

    const renderPagination = () => {
        let items = [];
        for (let number = 1; number <= totalPages; number++) {
            items.push(
                <Pagination.Item key={number} active={number === currentPage} onClick={() => handlePageChange(number)}>
                    {number}
                </Pagination.Item>,
            );
        }
        return <Pagination>{items}</Pagination>;
    };

    return (
        <div style={{ backgroundColor: '#121212', height: '100vh' }}>
            <Container className="mt-4" style={{ backgroundColor: 'rgba(169, 169, 169, 0.8)', color: 'white' }}>
                <Row>
                    {currentSellers.map((seller, index) => (
                        <Col key={index} md={4} className="mb-4">
                            <Card style={{ backgroundColor: 'black', color: 'white' }}>
                                <Card.Header as="h5" style={{ backgroundColor: '#733BC0', color: 'black' }}>{seller.name}</Card.Header>
                                <Card.Body>
                                    <Card.Text><strong>Email:</strong> {seller.email}</Card.Text>
                                    <Card.Text><strong>Phone:</strong> {seller.phone}</Card.Text>
                                    <Card.Text><strong>Address:</strong> {seller.address}</Card.Text>
                                    <Card.Text><strong>balance:</strong> {seller.balance}</Card.Text>
                                    <Card.Text><strong>nationalID:</strong> {seller.nationalID}</Card.Text>
                                    <Card.Text><strong>dateJoined:</strong> {new Date(seller.dateJoined).toLocaleDateString('en-US')}</Card.Text>
                                    <Card.Text><strong>sellerDescription:</strong> {seller.sellerDescription}</Card.Text>
                                    <Card.Text><strong>vatRegistrationNumber:</strong> {seller.vatRegistrationNumber}</Card.Text>
                                    <Button
                                        style={{backgroundColor: "#733BC0", color: "#f0f0f0", borderColor: "#733BC0", borderRadius: "5px", cursor: "pointer", transition: "background-color 0.3s",}}
                                        onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = "rgba(115,	59,	192 ,0.5)")}
                                        onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = "#733BC0")}
                                        onClick={() => deleteSeller(seller.id)}
                                    >
                                        Remove From System
                                    </Button>
                                </Card.Body>
                                <Card.Footer style={{ backgroundColor: 'white', color: 'black' }}>
                                    <small className="text-muted">Joined: {new Date(seller.dateJoined).toLocaleDateString('en-US')}</small>
                                </Card.Footer>
                            </Card>
                        </Col>
                    ))}
                </Row>
                <Row>
                    <Col>
                        <div className="d-flex justify-content-center my-4">
                            <Pagination style={{ justifyContent: 'center' }}>
                                <Pagination.First onClick={() => handlePageChange(1)} disabled={currentPage === 1} />
                                <Pagination.Prev onClick={() => handlePageChange(Math.max(1, currentPage - 1))} disabled={currentPage === 1} />
                                {renderPagination()}
                                <Pagination.Next onClick={() => handlePageChange(Math.min(totalPages, currentPage + 1))} disabled={currentPage === totalPages} />
                                <Pagination.Last onClick={() => handlePageChange(totalPages)} disabled={currentPage === totalPages} />
                            </Pagination>
                        </div>
                    </Col>
                </Row>
            </Container>
        </div>
    );
}