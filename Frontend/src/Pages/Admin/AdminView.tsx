import { useEffect, useState } from "react";
import { AdminDTO } from "../../Controller/DTO/user/AdminDTO"; // Assuming the path to your DTO
import { httpRequest } from "../../Controller/HttpProxy";
import { Card, Container, Row, Col, Pagination, Button } from 'react-bootstrap';
import {useParams} from "react-router-dom";

export function AdminView() {
    const { adminId } = useParams();
    const [adminsList, setAdminsList] = useState<AdminDTO[]>([]);
    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 12;
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const currentAdmins = adminsList.slice(startIndex, endIndex);
    const totalPages = Math.ceil(adminsList.length / itemsPerPage);

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    useEffect(() => {
        const fetchData = async () => {
            const response = await getAllAdmins();
            setAdminsList(response);
        };
        fetchData();
    }, []);

    const getAllAdmins = async() => {
        const response = await httpRequest("GET", `/admin/view/admins/${adminId}`);
        return response.data as AdminDTO[];
    }

    const deleteAdmin = async (id: number) => {
        const isConfirmed = window.confirm("Are you sure you want to remove this admin from the system?");
        if (!isConfirmed) return;

        try {
            const response = await httpRequest("DELETE", `/admin/delete/admin/${id}`);
            alert(response.data);
            setAdminsList(adminsList.filter(admin => admin.id !== id));
        } catch (error) {
            console.error(`Error deleting admin with ID ${id}:`, error);
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
            <Row style={{width: '100%', height: '80vh', marginLeft:'170px'}}>
                {currentAdmins.map((admin, index) => (
                    <Col key={index} md={3} style={{margin: '8px', width: '20%'}}>
                        <Card style={{ backgroundColor: 'black', color: 'white' }}>
                            <Card.Header as="h5" style={{ backgroundColor: '#733BC0', color: 'black' }}>{admin.name}</Card.Header>
                            <Card.Body>
                                <Card.Text><strong>Email:</strong> {admin.email}</Card.Text>
                                <Card.Text><strong>Phone:</strong> {admin.phone}</Card.Text>
                                <Button
                                    style={{backgroundColor: "#733BC0", color: "#f0f0f0", borderColor: "#733BC0", borderRadius: "5px", cursor: "pointer", transition: "background-color 0.3s",}}
                                    onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = "rgba(115,	59,	192 ,0.5)")}
                                    onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = "#733BC0")}
                                    onClick={() => deleteAdmin(admin.id)}
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
