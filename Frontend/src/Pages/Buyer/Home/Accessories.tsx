import React, {ChangeEvent, useEffect, useState} from "react";
import { Container, Row, Col, Pagination } from "react-bootstrap";
import { NavbarC } from "../../../Components/NavbarC";
import { ProductCard } from "../../../Components/ProductCard";
import "./PaginationC.css";
import {httpRequest} from "../../../Controller/HttpProxy";

export function HomeAccessories() {

  const [productCardPropsList, setProductCardPropsList] = useState([]);
 // const productCardPropsList = [];
  const itemsPerPage = 20;
  const maxVisiblePages = 5;
  const [currentPage, setCurrentPage] = useState(1);

  // Fetch all products when the component mounts
  useEffect(() => {
    const fetchData = async () => {
      const products = await fetchAllProducts();
      setProductCardPropsList(products); // Update state with fetched data
    };
    fetchData();
  }, []); // Empty dependency array to run only once

  const fetchAllProducts = async () => {
    let response = null;
    try {
      response = await httpRequest("GET", `/product/physical/getall`);
      return response.data; // Return the fetched data
    } catch (error) {
      console.error(`Error getting all products:`, error);
    }
  };

  const updateProductCardPropsList = (newList) => {
    setProductCardPropsList(newList);
  };

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

  function handlePriceFilterRequest(e: React.MouseEvent<HTMLButtonElement, MouseEvent>): void {
    e.preventDefault();
    filterProducts(categoryFilterOption, minPrice, maxPrice, "digital").then(filteredProducts => {
      setProductCardPropsList(filteredProducts);
    })
        .catch(error => console.error("Error in filtering:", error));
  }

  const handleCategoryCheckboxClick = (category: string) => {
    const newCategoryFilterOption = categoryFilterOption === category ? null : category;
    setCategoryFilterOption(newCategoryFilterOption);
    filterProducts(newCategoryFilterOption, minPrice, maxPrice, "physical")
        .then(filteredProducts => {
          setProductCardPropsList(filteredProducts);
        })
        .catch(error => console.error("Error in filtering:", error));
  };

  const filterProducts = async (category: string, lowerBound: number, upperBound: number, productType: string) => {
    let url = `/product/${productType}/filter?`
    if(category != null) url += `category=${category}&`
    if(lowerBound != null) url += `lowerBound=${lowerBound}&`
    if(upperBound != null) url += `upperBound=${upperBound}`
    try {
      const response = await httpRequest("GET", url)
      console.log(response.data.length)
      console.log(response.data[0]?.price)
      return response.data
    } catch (error) {
      console.error(`Error filtering product with category ${category}, lowerBound ${lowerBound}. upperBound ${upperBound}:`, error);
    }
  };

  const handleSortChange = (selectedOption: string) => {
    setSortOption(selectedOption);
    const ascending = selectedOption === "Ascendingly";
    sortProducts(ascending, "physical").then(sortedProducts => {
      console.log(sortedProducts.length)
      setProductCardPropsList(sortedProducts);
    })
        .catch(error => console.error("Error in sorting:", error));
  };

  const sortProducts = async (ascending: boolean, productType: string) => {
    const url = `/product/${productType}/sort?ascending=${ascending}`;
    console.log(productType)
    try {
      const response = await httpRequest("GET", url)
      console.log(response.data.length)
      console.log(response.data[1]?.price)
      return response.data
    } catch (error) {
      console.error(`Error sorting product`, error);
    }
  };

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
        <NavbarC
            productType={"physical"}
            updateProductCardPropsList={updateProductCardPropsList}
        />
        <Row style={{ display: "flex", flexDirection: "row", height: "160vh" }}>
          <Col
            md={2}
            style={{
              height: "77vh",
              marginTop: "30px",
              marginLeft: "55px",
              backgroundColor: "white",
            }}
          ></Col>
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
