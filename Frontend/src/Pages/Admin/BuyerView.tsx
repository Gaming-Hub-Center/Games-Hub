import { useEffect, useState } from "react";
import { UserDTO } from "../../Controller/DTO/user/UserDTO";
import { httpRequest } from "../../Controller/HttpProxy";
import { Card, Container, Row, Col, Pagination, Button } from 'react-bootstrap';

export function BuyerView() {
    const [buyersList, setBuyersList] = useState<UserDTO[]>([]);
    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 8;
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const currentBuyers = buyersList.slice(startIndex, endIndex);
    const totalPages = Math.ceil(buyersList.length / itemsPerPage);

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    useEffect(() => {
        const fetchData = async () => {
            const response = await getAllBuyers()
            setBuyersList(response)
        };
        fetchData();
    }, []);

    const getAllBuyers = async() => {
        const response = await httpRequest("GET", `/admin/view/buyers`);
        return response.data as UserDTO[]
    }

    const deleteBuyer = async (id: number) => {
        const isConfirmed = window.confirm("Are you sure you want to remove this buyer from the system?");
        if (!isConfirmed) return;

        try {
            const response = await httpRequest("DELETE", `/admin/delete/buyer/${id}`);
            alert(response.data);
            setBuyersList(buyersList.filter(buyer => buyer.id !== id));
        } catch (error) {
            console.error(`Error deleting buyer with ID ${id}:`, error);
        }
    };

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
        <Container fluid style={{ backgroundColor: 'darkslateblue', color: 'white', height: '100vh', overflow: "hidden" }}>
            <Row style={{width: '100%', height: '70vh', marginLeft:'170px'}}>
                {currentBuyers.map((buyer, index) => (
                    <Col key={index} md={3} style={{margin: '8px', width: '20%'}}>
                        <Card style={{ backgroundColor: 'black', color: 'white' }}>
                            <Card.Header as="h5" style={{ backgroundColor: '#733BC0', color: 'black' }}>{buyer.name}</Card.Header>
                            <Card.Body>
                                <Card.Text><strong>Email:</strong> {buyer.email}</Card.Text>
                                <Card.Text><strong>Phone:</strong> {buyer.phone}</Card.Text>
                                <Card.Text><strong>Address:</strong> {buyer.address}</Card.Text>
                                <Card.Text><strong>balance:</strong> {buyer.balance}</Card.Text>
                                <Button
                                    style={{backgroundColor: "#733BC0", color: "#f0f0f0", borderColor: "#733BC0", borderRadius: "5px", cursor: "pointer", transition: "background-color 0.3s",}}
                                    onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = "rgba(115,	59,	192 ,0.5)")}
                                    onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = "#733BC0")}
                                    onClick={() => deleteBuyer(buyer.id)}
                                >
                                    <strong>Remove From System</strong>
                                </Button>
                            </Card.Body>
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
    );
}