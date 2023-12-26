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

  
  const handleApprove = (productId) => {
    console.log(`Product ${productId} approved.`);
    // Add your logic to handle approval
  };
  
  const handleDecline = (productId) => {
      console.log(`Product ${productId} declined.`);
      // Add your logic to handle decline
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
      <h2>{productType === 'physical' ? 'Physical' : 'Digital'} {status} Products</h2> {/* Updated to use status */}
      {loading ? (
        <p>Loading...</p>
      ) : (
        <div>
          {products.map(product => (
            <div key={product.id} className="admin-pending-product-row">
              <span>{product.title}</span>
              <span>{new Date(product.created_date).toLocaleDateString()}</span>
              <div>
                {iconVisibility.showApprove && <FaThumbsUp className="approve-icon" onClick={() => handleApprove(product.id)} />}
                {iconVisibility.showDecline && <FaThumbsDown className="decline-icon" onClick={() => handleDecline(product.id)} />}
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
        <button onClick={() => setCurrentPage(currentPage - 1)} disabled={currentPage === 1}>
          Previous
        </button>
        <span>Page {currentPage}</span>
        <button onClick={() => setCurrentPage(currentPage + 1)}>
          Next
        </button>
      </div>
    </div>
  );
};

export default AdminProductsComponent;
