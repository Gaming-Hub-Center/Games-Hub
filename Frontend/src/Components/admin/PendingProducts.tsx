import React, { useState, useEffect } from 'react';
import './PendingProducts.css'
import { ProductDTO } from '../../Controller/DTO/ProductDTO/ProductDTO';
import { FaInfoCircle, FaThumbsDown, FaThumbsUp } from 'react-icons/fa';
import ProductDetailsModal from './ProductModat';
import { httpRequest } from '../../Controller/HttpProxy';

type PendingProductsProps = {
  productType: 'physical' | 'digital';
  iconVisibility: {
    showApprove: boolean;
    showDecline: boolean;
    showDetails: boolean;
  };
  status: 'Pending' | 'Approved' | 'Declined';
};


const AdminProductsComponent: React.FC<PendingProductsProps> = ({ productType, iconVisibility, status }) => {
  const [products, setProducts] = useState<ProductDTO[]>([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [loading, setLoading] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedProductDetails, setSelectedProductDetails] = useState<ProductDTO | null>(null);
  const pageSize = 18; // Set the number of items you want per page
  const [paginatedProducts, setPaginatedProducts] = useState<ProductDTO[]>([]);
  
  //------------------ Pagination --------------------------------------

  useEffect(() => {
    // Calculate the index of the first and last items on the current page
    const firstPageIndex = (currentPage - 1) * pageSize;
    const lastPageIndex = firstPageIndex + pageSize;
    // Set products for the current page
    setPaginatedProducts(products.slice(firstPageIndex, lastPageIndex));
  }, [currentPage, products]); // Recalculate when currentPage or products change

  // Handlers for pagination
  const goToPreviousPage = () => setCurrentPage(currentPage => Math.max(1, currentPage - 1));
  const goToNextPage = () => setCurrentPage(currentPage => Math.min(totalPages, currentPage + 1));

  // Calculate the total number of pages
  const totalPages = Math.ceil(products.length / pageSize);

  //-------------- End Of Pagination --------------------------------------

  const fetchProducts = async (productType, page, status) => {
    const url = "/admin/products";
  
    const requestData = {
      productType: productType,
      status: status
      // page: page // If needed, include 'page' in the request data
    };
  
    // Use the httpRequest function to make the GET API call
    httpRequest("GET", url, null, requestData)
      .then((response) => {
        // Assuming response.data contains the array of ProductDTO
        setProducts(response.data); 
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching products:", error);
        setLoading(false);
      });
  };

  useEffect(() => {
    setLoading(true); // Start loading
    fetchProducts(productType, currentPage, status);
  }, [productType, currentPage, status]); // Dependencies array

  
  const handleApprove = (product) => {
    console.log(product)
    console.log(`Product ${product.id} approved.`);

    const requestData = {
      productType: productType,
      requestId: product.id
      // page: page // If needed, include 'page' in the request data
    };

    console.log(requestData)

    httpRequest("GET", "/admin/approve", null, requestData)
    .then((response) => {
      // Assuming the response contains the updated list of products
      setProducts(response.data); // Update the state with the updated list of products
    })
    .catch((error) => {
      console.error("Error Approving Products", error);
    });
  
  };
  
  const handleDecline = (product) => {
    console.log(product)
    console.log(`Product ${product.id} approved.`);

    const requestData = {
      productType: productType,
      requestId: product.id
      // page: page // If needed, include 'page' in the request data
    };

    console.log(requestData)

    httpRequest("GET", "/admin/decline", null, requestData)
    .then((response) => {
      // Assuming the response contains the updated list of products
      setProducts(response.data); // Update the state with the updated list of products
    })
    .catch((error) => {
      console.error("Error Declining Products", error);
    });
  };

  const handleShowDetails = (product: ProductDTO) => {
    setSelectedProductDetails(product); // Set the selected product
    setIsModalOpen(true); // Open the modal
  };

  // Define the function to close the modal
  const handleCloseModal = () => {
      setIsModalOpen(false);
      setSelectedProductDetails(null);
  };

  return (
    <div>
      <h2>{productType === 'physical' ? 'Physical' : 'Digital'} {status} Products</h2>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <div>
          {paginatedProducts.map(product => ( // Change this line to use paginatedProducts
            <div key={product.id} className="admin-pending-product-row">
              <span>{product.title}</span>
              <span>
                {status === 'Pending' ? 
                new Date(product.dateReceived).toLocaleDateString() : 
                new Date(product.postDate).toLocaleDateString()}
              </span>
              <div>
                {iconVisibility.showApprove && <FaThumbsUp className="approve-icon" onClick={() => handleApprove(product)} />}
                {iconVisibility.showDecline && <FaThumbsDown className="decline-icon" onClick={() => handleDecline(product)} />}
                {iconVisibility.showDetails && (
                  <FaInfoCircle className="details-icon" onClick={() => handleShowDetails(product)} />
                )}                  
              </div>
            </div>
          ))}
        </div>
      )}
  
      <ProductDetailsModal 
        product={selectedProductDetails} 
        isOpen={isModalOpen} 
        onClose={handleCloseModal}
      />
  
      <div className="admin-pagination">
        <button onClick={goToPreviousPage} disabled={currentPage === 1}>
          Previous
        </button>
        <span>Page {currentPage} of {totalPages}</span>
        <button onClick={goToNextPage} disabled={currentPage === totalPages}>
          Next
        </button>
      </div>
    </div>
  );
}

export default AdminProductsComponent;
