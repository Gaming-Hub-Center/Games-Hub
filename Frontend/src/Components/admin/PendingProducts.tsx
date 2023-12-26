import React, { useState, useEffect } from 'react';
import './PendingProducts.css'
import { ProductDTO } from '../../Controller/DTO/ProductDTO/ProductDTO';
import { FaInfoCircle, FaThumbsDown, FaThumbsUp } from 'react-icons/fa';
import ProductDetailsModal from './ProductModat';

type PendingProductsProps = {
  productType: 'physical' | 'digital';
  iconVisibility: {
    showApprove: boolean;
    showDecline: boolean;
    showDetails: boolean;
  };
  status: 'Pending' | 'Approved' | 'Declined';
};

const fetchProducts = (productType: 'physical' | 'digital', page: number, statues: string): Promise<ProductDTO[]> => {
  // Replace with actual data fetching logic based on productType
  return new Promise(resolve => {
    setTimeout(() => {
      const products: ProductDTO[] = Array.from({ length: 20 }, (_, index): ProductDTO => {
        return {
          id: page * 10 + index,
          price: Math.random() * 100, // Mock price
          description: `${productType === 'physical' ? 'Physical' : 'Digital'} product description`,
          title: `${productType === 'physical' ? 'Physical' : 'Digital'} Product ${page * 20 + index}`,
          count: Math.floor(Math.random() * 100), // Mock count
          sellerID: Math.floor(Math.random() * 1000), // Mock seller ID
          created_date: new Date().toISOString(),
          category: productType === 'physical' ? 'Electronics' : 'Software', // Example categories
          images: [`/images/${productType}_product_${index}.jpg`], // Mock images
          code: `CODE${page * 10 + index}` // Mock code
        };
      });
      resolve(products);
    }, 1000);
  });
};

const AdminProductsComponent: React.FC<PendingProductsProps> = ({ productType, iconVisibility, status }) => {
  const [products, setProducts] = useState<ProductDTO[]>([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [loading, setLoading] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedProductDetails, setSelectedProductDetails] = useState<ProductDTO | null>(null);

  useEffect(() => {
    setLoading(true);
    fetchProducts(productType, currentPage, status)
      .then(fetchedProducts => {
        setProducts(fetchedProducts);
        setLoading(false);
      });
  }, [productType, currentPage, status]); // Added status to the dependency array

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
