import { useEffect, useState } from "react";
import { ProductDTO } from "../../Controller/DTO/ProductDTO/ProductDTO";
import './ProductModal.css'
import { httpRequest } from "../../Controller/HttpProxy";

type ProductDetailsModalProps = {
    product: ProductDTO | null;
    isOpen: boolean;
    onClose: () => void;
  };
  
  const ProductDetailsModal: React.FC<ProductDetailsModalProps> = ({ product, isOpen, onClose }) => {
    if (!isOpen) return null;
    const [selectedImage, setSelectedImage] = useState<string | null>(null);
    const [sellerName, setSellerName] = useState<string>('');

    const handleImageClick = (image: string) => {
        setSelectedImage(image);
    };
    
    useEffect(() => {
      const requestData = {
        id: product.sellerId
      };

      console.log(product.sellerId)
      if (product && product.sellerId) {
        httpRequest('GET', `/seller/name`, null, requestData)
          .then(response => {
            console.log(response)
            setSellerName(response.data); // Replace 'name' with the actual field name from your response
          })
          .catch(error => console.error('Error fetching seller name:', error));
      }
    }, [product]);
    
    return (
      <div className="admin-modal-overlay">
        <div className="admin-modal-content">
          <h2>Product Details</h2>
          {/* Display product details */}
          <p>Title: {product?.title}</p>
          <p>Price: {product?.price.toFixed(2)}</p>
          <p>Description: {product?.description}</p>
          <p>Count: {product?.count}</p>
          <p>Seller Name: {sellerName}</p>
          <p>Created Date: {product && new Date(product.postDate).toLocaleDateString()}</p>
          <p>Category: {product?.category}</p>
          <button onClick={onClose}>Close</button>
          {/* TODO Add Images */}
        </div>
      </div>
    );
  };

  export default ProductDetailsModal;
  