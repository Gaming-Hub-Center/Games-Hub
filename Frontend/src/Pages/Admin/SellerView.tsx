import {useEffect, useState} from "react";
import { UserDTO } from "../../Controller/DTO/user/UserDTO";
import { httpRequest } from "../../Controller/HttpProxy";
import { Card, Container, Row, Col, Pagination, Button } from 'react-bootstrap';
import {useNavigate} from "react-router-dom";


export function SellerView() {
    const [sellersList, setSellersList] = useState<UserDTO[]>([]);
    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 8;
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const currentSellers = sellersList.slice(startIndex, endIndex);
    const totalPages = Math.ceil(sellersList.length / itemsPerPage);
    const navigate = useNavigate();

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
        const isConfirmed = window.confirm("Are you sure you want to remove this seller from the system?");
        if (!isConfirmed) return;

        try {
            const response = await httpRequest("DELETE", `/admin/delete/seller/${id}`);
            alert(response.data)
            setSellersList(sellersList.filter(seller => seller.id !== id));
        } catch (error) {
            console.error(`Error deleting seller with ID ${id}:`, error);
        }
    }

    function goToViewSellerProducts(sellerId: number): void {
        navigate(`/admin/view/seller/products/${sellerId}`);
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
        <Container fluid style={{ backgroundColor: 'darkslateblue', color: 'white', height: '100vh', overflow: "hidden" }}>
            <Row style={{width: '100%', height: '95.5vh', marginLeft:'170px'}}>
                {currentSellers.map((seller, index) => (
                    <Col key={index} md={3} style={{margin: '8px', width: '20%'}}  >
                        <Card style={{ backgroundColor: 'black', color: 'white', height: '46vh', width: '100%' }}>
                            <Card.Header as="h5" style={{ backgroundColor: '#733BC0', color: 'black', height: '4vh' }}>{seller.name}</Card.Header>
                            <Card.Body style={{height: '30vh'}}>
                                <Card.Text><strong>Email:</strong> {seller.email}</Card.Text>
                                <Card.Text><strong>Phone:</strong> {seller.phone}</Card.Text>
                                <Card.Text><strong>Address:</strong> {seller.address}</Card.Text>
                                <Card.Text><strong>balance:</strong> {seller.balance}</Card.Text>
                                <Card.Text><strong>nationalID:</strong> {seller.nationalID}</Card.Text>
                                <Card.Text><strong>sellerDescription:</strong> {seller.sellerDescription}</Card.Text>
                                <Card.Text><strong>vatRegistrationNumber:</strong> {seller.vatRegistrationNumber}</Card.Text>
                                <div style={{display: "flex", flexDirection: "row", justifyContent: "space-between",}} >
                                    <Button
                                        style={{backgroundColor: "#733BC0", color: "#f0f0f0", borderColor: "#733BC0", borderRadius: "5px", cursor: "pointer", transition: "background-color 0.3s", height: '4vh'}}
                                        onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = "rgba(115,	59,	192 ,0.5)")}
                                        onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = "#733BC0")}
                                        onClick={() => deleteSeller(seller.id)}
                                    >
                                        <strong>Remove From System</strong>
                                    </Button>
                                    <Button
                                        style={{backgroundColor: "#733BC0", color: "#f0f0f0", borderColor: "#733BC0", borderRadius: "5px", cursor: "pointer", transition: "background-color 0.3s", height: '4vh'}}
                                        onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = "rgba(115,	59,	192 ,0.5)")}
                                        onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = "#733BC0")}
                                        onClick={() => goToViewSellerProducts(seller.id)}
                                    >
                                        <strong>View Products</strong>
                                    </Button>
                                </div>
                            </Card.Body>
                            <Card.Footer style={{ backgroundColor: 'white', color: 'black', height: '4vh' }}>
                                <small className="text-muted">Joined: {new Date(seller.dateJoined).toLocaleDateString('en-US')}</small>
                            </Card.Footer>
                        </Card>
                    </Col>
                ))}
            </Row>
            <Row>
                <Col style={{ height: "8vh", display: "flex", justifyContent: "center",}}>
                    <Pagination style={{ justifyContent: 'center' }}>
                        <Pagination.First onClick={() => handlePageChange(1)} disabled={currentPage === 1} />
                        <Pagination.Prev onClick={() => handlePageChange(Math.max(1, currentPage - 1))} disabled={currentPage === 1} />
                        {renderPagination()}
                        <Pagination.Next onClick={() => handlePageChange(Math.min(totalPages, currentPage + 1))} disabled={currentPage === totalPages} />
                        <Pagination.Last onClick={() => handlePageChange(totalPages)} disabled={currentPage === totalPages} />
                    </Pagination>
                </Col>
            </Row>
        </Container>
    );
}