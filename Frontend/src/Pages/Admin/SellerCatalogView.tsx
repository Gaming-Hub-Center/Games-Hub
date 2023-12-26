import {useEffect, useState} from "react";
import {ProductBriefDTO} from "../../Controller/DTO/ProductDTO/ProductBriefDTO";
import {httpRequest} from "../../Controller/HttpProxy";
import {Container} from "react-bootstrap";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";


export function SellerCatalogView() {
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
        const response = await httpRequest("GET", `/admin/view/physical/products/{sellerId}`);
        return response.data as ProductBriefDTO[]
    }

    const getAllDigital = async() => {
        const response = await httpRequest("GET", `/admin/view/digital/products/{sellerId}`);
        return response.data as ProductBriefDTO[]
    }




    return(
        <Container>
            <Col>
                <Row>
                    Physical Products
                </Row>
                <Row>

                </Row>
            </Col>
            <Col>
                <Row>
                    Digital Products
                </Row>
                <Row>

                </Row>
            </Col>
        </Container>
    );
}