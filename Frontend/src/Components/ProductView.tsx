import { useParams, useLocation } from "react-router-dom";
import { cardProps } from "./ProductCard";
import { Container, Row, Col, Carousel, Image, Button } from "react-bootstrap";
import { NavbarC } from "./NavbarC";
import { formatCurrency } from "../Utilities/formatCurrency";
import {useEffect, useState} from "react";
import {ProductDTO} from "../Controller/DTO/ProductDTO/ProductDTO";
import {httpRequest} from "../Controller/HttpProxy";

interface ProductViewProps {
    productType: string;
}

export function ProductView() {

    const { id } = useParams(); // Retrieve the product ID from the URL
    const location = useLocation();
    const { productType } = location.state || { productType: "defaultType" }; // Retrieve productType from state or set a default
    const [response, setResponse] = useState<ProductDTO | null>(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await getProductDetails(parseInt(id, 10), productType);
                setResponse(response);
            } catch (error) {
                console.error("Error fetching product details:", error);
            }
        };

        fetchData();
    }, [productType]);

    const getProductDetails = async (productId: number, productType: string) => {
        let response = null;
        try {
            response = await httpRequest(
                "GET",
                `/product/${productType}/getdetails?ID=${productId}`
            );
            console.log(response.data.count);
            console.log(response.data.code);
            console.log(response.data.category);
            return response.data as ProductDTO;
        } catch (error) {
            console.error("Error fetching product details:", error);
            throw error;
        }
    };

    // Function to add a product to the cart
    const addToCart = () => {};

    if (!response) {
        return null;
    }


  return (
    <>
      <Container
        fluid
        style={{
          height: "100vh",
          padding: 0,
          backgroundColor: "#121212",
        }}
      >
        <NavbarC productType={undefined} updateProductCardPropsList={undefined} />
        <Row
          style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            flexDirection: "row",
            height: "85vh",
          }}
        >
          <Col
            md={5}
            style={{
              height: "85vh",
              marginTop: "30px",
              backgroundColor: "black",
              borderRadius: "15px",
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <Carousel
              data-bs-theme="dark"
              style={{ marginTop: "20px", height: "400px", width: "750px" }}
            >
              <Carousel.Item>
                <Image
                  className="d-block"
                  src={`data:image/jpeg;base64,${response.images[0]}`}
                  alt="First slide"
                  style={{
                    objectFit: "cover",
                    height: "400px",
                    width: "100%",
                  }}
                />
              </Carousel.Item>
              <Carousel.Item>
                <Image
                  className="d-block"
                  src={`data:image/jpeg;base64,${response.images[1]}`}
                  alt="Second slide"
                  style={{
                    objectFit: "cover",
                    height: "400px",
                    width: "100%",
                  }}
                />
              </Carousel.Item>
              <Carousel.Item>
                <Image
                  className="d-block"
                  src={`data:image/jpeg;base64,${response.images[2]}`}
                  alt="Third slide"
                  style={{
                    objectFit: "cover",
                    height: "400px",
                    width: "100%",
                  }}
                />
              </Carousel.Item>
            </Carousel>
            <div
              style={{ width: "90%" }}
              className="d-flex justify-content-between align-items-baseline"
            >
              <span
                  className="fs-1"
                  style={{
                      color: "#f0f0f0",
                      maxHeight: "13vh",
                      overflow: "hidden",
                  }}
              >
                {response.title}
              </span>
              <span className="fs-3 text-muted ">{formatCurrency(response.price)}</span>
            </div>
            <span
              className="fs-5"
              style={{
                width: "90%",
                color: "#f0f0f0",
                height: "38vh",
                overflow: "auto",
                marginBottom: "15px",
              }}
            >
              {response.description}
            </span>
            <Button
              style={{
                width: "90%",
                height: "4vh",
                backgroundColor: "#733BC0",
                borderColor: "#733BC0",
                color: "#f0f0f0",
                marginBottom: "15px",
              }}
              onMouseEnter={(e) =>
                (e.currentTarget.style.backgroundColor =
                  "rgba(115, 59, 192, 0.6)")
              }
              onMouseLeave={(e) =>
                (e.currentTarget.style.backgroundColor = "#733BC0")
              }
              onClick={addToCart}
            >
              Add to cart
            </Button>
          </Col>
        </Row>
      </Container>
    </>
  );
}
