import React, { ChangeEvent, SetStateAction, useState } from "react";
import {
  Container,
  Row,
  Col,
  Pagination,
  Button,
  FormControl,
  Dropdown,
} from "react-bootstrap";
import { NavbarC } from "../../../Components/NavbarC";
import { ProductCard } from "../../../Components/ProductCard";
import "./PaginationC.css";
import { Form } from "react-bootstrap";

export function HomeGames() {
  const productCardPropsList = [
    {
      title:
        "GTA VTggrevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      description: "Trevor is gonna kill u :)",
      images: [
        "/src/data/GTA-V/1.jpg",
        "/src/data/GTA-V/2.jpg",
        "/src/data/GTA-V/3.jpg",
      ],
      price: 100,
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u bardo:)",
      images: [
        "/src/data/GTA-IV/1.png",
        "/src/data/GTA-IV/2.jpg",
        "/src/data/GTA-IV/3.jpg",
      ],
      price: 100,
    },
    {
      title: "GTA San Andreas",
      description: "Trevor is gonna kill u :)",
      images: [
        "/src/data/GTA-san/1.jpg",
        "/src/data/GTA-san/2.jpg",
        "/src/data/GTA-san/3.jpg",
      ],
      price: 100,
    },
    {
      title: "GTA San Andreas",
      description: "Trevor is gonna kill u :)",
      images: [
        "/src/data/GTA-san/1.jpg",
        "/src/data/GTA-san/2.jpg",
        "/src/data/GTA-san/3.jpg",
      ],
      price: 100,
    },
    {
      title: "GTA San Andreas",
      description: "Trevor is gonna kill u :)",
      images: [
        "/src/data/GTA-san/1.jpg",
        "/src/data/GTA-san/2.jpg",
        "/src/data/GTA-san/3.jpg",
      ],
      price: 100,
    },
    {
      title: "GTA San Andreas",
      description: "Trevor is gonna kill u :)",
      images: [
        "/src/data/GTA-san/1.jpg",
        "/src/data/GTA-san/2.jpg",
        "/src/data/GTA-san/3.jpg",
      ],
      price: 100,
    },
    {
      title: "GTA San Andreas",
      description: "Trevor is gonna kill u :)",
      images: [
        "/src/data/GTA-san/1.jpg",
        "/src/data/GTA-san/2.jpg",
        "/src/data/GTA-san/3.jpg",
      ],
      price: 100,
    },
  ];

  const [minPrice, setMinPrice] = useState(0);
  // # default max price...
  const [maxPrice, setMaxPrice] = useState(1000);
  const [sortOption, setSortOption] = useState("Ascendingly");
  const [categoryFilterOption, setCategoryFilterOption] = useState(null);

  const handleMinCostChange = (event: ChangeEvent<HTMLInputElement>) => {
    // Extract the numeric value from the input
    const minValue = parseFloat(event.target.value);

    if (!isNaN(minValue) && minValue >= 0 && minValue <= maxPrice) {
      setMinPrice(minValue);
    } else if (!isNaN(minValue) && minValue >= 0) {
      // If minValue is greater than maxPrice, set minPrice to maxPrice
      setMinPrice(maxPrice);
    } else {
      setMinPrice(0);
    }
  };

  const handleMaxCostChange = (event: ChangeEvent<HTMLInputElement>) => {
    // Extract the numeric value from the input
    const maxValue = parseFloat(event.target.value);

    // Check if the input is a valid number
    if (!isNaN(maxValue) && maxValue >= 0 && maxValue >= minPrice) {
      setMaxPrice(maxValue);
    } else if (!isNaN(maxValue) && maxValue >= 0) {
      // If maxValue is less than minPrice, set maxPrice to minPrice
      setMaxPrice(minPrice);
    } else {
      setMaxPrice(0);
    }
  };

  function handlePriceFilterRequest(
    e: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void {
    // # hna aho
  }

  const handleSortChange = (selectedOption: string) => {
    setSortOption(selectedOption);
    // # hna aho
  };

  const handleCategoryCheckboxClick = (category: string) => {
    setCategoryFilterOption(category);
  };

  const itemsPerPage = 20;
  const maxVisiblePages = 5;
  const [currentPage, setCurrentPage] = useState(1);

  // Calculate the index range for the current page
  const startIndex = (currentPage - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;

  // Get the product cards for the current page
  const currentProductCards = productCardPropsList.slice(startIndex, endIndex);

  const totalPages = Math.ceil(productCardPropsList.length / itemsPerPage);

  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  const renderPaginationItems = () => {
    const items = [];
    const startPage = Math.max(
      1,
      currentPage - Math.floor(maxVisiblePages / 2)
    );
    const endPage = Math.min(totalPages, startPage + maxVisiblePages - 1);

    for (let i = startPage; i <= endPage; i++) {
      items.push(
        <Pagination.Item
          key={i}
          active={i === currentPage}
          onClick={() => handlePageChange(i)}
        >
          {i}
        </Pagination.Item>
      );
    }

    return items;
  };

  return (
    <>
      <Container
        fluid
        style={{
          height: "170vh",
          padding: 0,
          backgroundColor: "#121212",
        }}
      >
        <NavbarC></NavbarC>
        <Row style={{ display: "flex", flexDirection: "row", height: "160vh" }}>
          <Col
            md={2}
            style={{
              height: "79vh",
              marginTop: "30px",
              marginLeft: "55px",
              backgroundColor: "black",
              color: "#f0f0f0",
              display: "flex",
              flexDirection: "column",
            }}
          >
            <Row style={{ height: "30%" }}>
              <div
                style={{
                  maxHeight: "30%",
                  display: "flex",
                  flexDirection: "column",
                }}
              >
                <span className="fs-3">Filter</span>
                <span className="fs-5">Category</span>
              </div>
              <div
                style={{
                  marginTop: "0.5vh",
                  maxHeight: "70%",
                  overflowY: "auto",
                  alignItems: "flex-start",
                }}
              >
                <Form.Check
                  type="checkbox"
                  label="Action"
                  onClick={() => handleCategoryCheckboxClick("Action")}
                />
                <Form.Check
                  type="checkbox"
                  label="Sports"
                  onClick={() => handleCategoryCheckboxClick("Sports")}
                />
              </div>
            </Row>
            <Row style={{ height: "10%" }}>
              <span className="fs-5" style={{ marginTop: "0.5vh" }}>
                Price
              </span>
              <Col
                style={{
                  display: "flex",
                  flexDirection: "row",
                  height: "50%",
                }}
              >
                <FormControl
                  style={{
                    width: "40%",
                    backgroundColor: "#121212",
                    color: "#f0f0f0",
                    borderColor: "#f0f0f0",
                  }}
                  type="number"
                  placeholder="Min"
                  className="mr-sm-2"
                  value={minPrice}
                  onChange={handleMinCostChange}
                />
                <span style={{ margin: "6px 5px" }}>to</span>
                <FormControl
                  style={{
                    width: "40%",
                    backgroundColor: "#121212",
                    color: "#f0f0f0",
                    borderColor: "#f0f0f0",
                  }}
                  type="number"
                  placeholder="Max"
                  className="mr-sm-2"
                  value={maxPrice}
                  onChange={handleMaxCostChange}
                />
                <Button
                  style={{
                    marginLeft: "10px",
                    backgroundColor: "#733BC0",
                    color: "#f0f0f0",
                    borderColor: "#733BC0",
                    borderRadius: "5px",
                    cursor: "pointer",
                    transition: "background-color 0.3s",
                  }}
                  onMouseEnter={(e) =>
                    (e.currentTarget.style.backgroundColor =
                      "rgba(115,	59,	192 ,0.5)")
                  }
                  onMouseLeave={(e) =>
                    (e.currentTarget.style.backgroundColor = "#733BC0")
                  }
                  onClick={handlePriceFilterRequest}
                >
                  Go
                </Button>
              </Col>
            </Row>
            <Row style={{ height: "30%" }}>
              <div
                style={{
                  maxHeight: "30%",
                  display: "flex",
                  flexDirection: "column",
                }}
              >
                <hr
                  style={{
                    borderColor: "gray",
                    margin: "10px 0",
                    width: "100%",
                  }}
                />{" "}
                <span className="fs-3">Sort</span>
                <Dropdown style={{ marginTop: "5px" }}>
                  <Dropdown.Toggle
                    style={{
                      backgroundColor: "#733BC0",
                      color: "#f0f0f0",
                      borderColor: "#733BC0",
                      borderRadius: "5px",
                      cursor: "pointer",
                      transition: "background-color 0.3s",
                    }}
                    onMouseEnter={(e) =>
                      (e.currentTarget.style.backgroundColor =
                        "rgba(115,	59,	192 ,0.5)")
                    }
                    onMouseLeave={(e) =>
                      (e.currentTarget.style.backgroundColor = "#733BC0")
                    }
                  >
                    Sort by price{" "}
                  </Dropdown.Toggle>
                  <Dropdown.Menu>
                    <Dropdown.Item
                      onClick={() => handleSortChange("Ascendingly")}
                      active={sortOption === "Ascendingly"}
                      style={{
                        backgroundColor:
                          sortOption === "Ascendingly" ? "#7b3cc0" : "white",
                        color: sortOption === "Ascendingly" ? "white" : "black",
                      }}
                      onMouseEnter={(e) => {
                        if (!sortOption || sortOption !== "Ascendingly") {
                          e.currentTarget.style.backgroundColor =
                            "rgba(115, 59, 192, 0.5)";
                        }
                      }}
                      onMouseLeave={(e) => {
                        if (!sortOption || sortOption !== "Ascendingly") {
                          e.currentTarget.style.backgroundColor = "white";
                          e.currentTarget.style.color = "black";
                        }
                      }}
                    >
                      Ascendingly
                    </Dropdown.Item>
                    <Dropdown.Item
                      onClick={() => handleSortChange("Descendingly")}
                      active={sortOption === "Descendingly"}
                      style={{
                        backgroundColor:
                          sortOption === "Descendingly" ? "#7b3cc0" : "white",
                        color:
                          sortOption === "Descendingly" ? "white" : "black",
                      }}
                      onMouseEnter={(e) => {
                        if (!sortOption || sortOption !== "Descendingly") {
                          e.currentTarget.style.backgroundColor =
                            "rgba(115, 59, 192, 0.5)";
                        }
                      }}
                      onMouseLeave={(e) => {
                        if (!sortOption || sortOption !== "Descendingly") {
                          e.currentTarget.style.backgroundColor = "white";
                          e.currentTarget.style.color = "black";
                        }
                      }}
                    >
                      Descendingly
                    </Dropdown.Item>
                  </Dropdown.Menu>
                </Dropdown>
              </div>
            </Row>
          </Col>
          <Col
            style={{
              height: "82vh",
              marginTop: "30px",
              display: "flex",
              flexDirection: "row",
              flexWrap: "wrap",
              justifyContent: "flex-start",
            }}
          >
            {currentProductCards.map((productCardProps, index) => (
              <ProductCard key={startIndex + index} {...productCardProps} />
            ))}
          </Col>
        </Row>
        <Row style={{ height: "8vh", backgroundColor: "black" }}>
          <Col
            style={{
              height: "8vh",
              display: "flex",
              justifyContent: "center",
            }}
          >
            <Pagination style={{ marginTop: "20px", color: "black" }}>
              <Pagination.First onClick={() => handlePageChange(1)} />
              <Pagination.Prev
                onClick={() =>
                  handlePageChange(currentPage > 1 ? currentPage - 1 : 1)
                }
              />
              {renderPaginationItems()}
              <Pagination.Next
                onClick={() =>
                  handlePageChange(
                    currentPage < totalPages ? currentPage + 1 : totalPages
                  )
                }
              />
              <Pagination.Last onClick={() => handlePageChange(totalPages)} />
            </Pagination>
          </Col>
        </Row>
      </Container>
    </>
  );
}
