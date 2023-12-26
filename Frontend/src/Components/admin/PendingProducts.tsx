import React, { useState, useEffect } from 'react';
import './PendingProducts.css'
import { ProductDTO } from '../../Controller/DTO/ProductDTO/ProductDTO';
import { FaInfoCircle, FaThumbsDown, FaThumbsUp } from 'react-icons/fa';

  type PendingProductsProps = {
    productType: 'physical' | 'digital';
  };

  const fetchProducts = (productType: 'physical' | 'digital', page: number): Promise<ProductDTO[]> => {
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
  
  // Dummy handler functions - Replace these with your actual logic
    const handleApprove = (productId) => {
        console.log(`Product ${productId} approved.`);
        // Add your logic to handle approval
    };
    
    const handleDecline = (productId) => {
        console.log(`Product ${productId} declined.`);
        // Add your logic to handle decline
    };
    
    const handleShowDetails = (productId) => {
        console.log(`Show details for Product ${productId}.`);
        // Add your logic to show details
    };
    

  const PendingProductsComponent: React.FC<PendingProductsProps> = ({ productType }) => {
    const [products, setProducts] = useState<ProductDTO[]>([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [loading, setLoading] = useState(false);
  
    useEffect(() => {
      setLoading(true);
      fetchProducts(productType, currentPage)
        .then(fetchedProducts => {
          setProducts(fetchedProducts);
          setLoading(false);
        });
    }, [productType, currentPage]);
  
    return (
      <div>
        <h2>{productType === 'physical' ? 'Physical' : 'Digital'} Pending Products</h2>
        {loading ? (
          <p>Loading...</p>
        ) : (
          <div>
            {products.map(product => (
              <div key={product.id} className="admin-pending-product-row">
                <span>{product.title}</span>
                <span>{new Date(product.created_date).toLocaleDateString()}</span>
                <div>
                    <FaThumbsUp className="approve-icon" onClick={() => handleApprove(product.id)} />
                    <FaThumbsDown className="decline-icon" onClick={() => handleDecline(product.id)} />
                    <FaInfoCircle className="details-icon" onClick={() => handleShowDetails(product.id)} />
                </div>
              </div>
            ))}
          </div>
        )}
  
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
  
  export default PendingProductsComponent;