import { useState } from "react";
import { ProductDTO } from "../../Controller/DTO/ProductDTO/ProductDTO";
import './ProductModal.css'
import { getName } from "../../CurrentSession";

type ProductDetailsModalProps = {
    product: ProductDTO | null;
    isOpen: boolean;
    onClose: () => void;
  };
  
  const ProductDetailsModal: React.FC<ProductDetailsModalProps> = ({ product, isOpen, onClose }) => {
    if (!isOpen) return null;
    const [selectedImage, setSelectedImage] = useState<string | null>(null);

    const handleImageClick = (image: string) => {
        setSelectedImage(image);
    };

    return (
      <div className="admin-modal-overlay">
        <div className="admin-modal-content">
            <h2>Product Details</h2>
            {/* Display dummy product details */}
            <p>Title: {product.title}</p>
            <p>Price: {product.price.toFixed(2)}</p>
            <p>Description: {product.description}</p>
            <p>Count: {product.count}</p>
            <p>Seller Name: {getName()}</p>
            <p>Created Date: {new Date(product.postDate).toLocaleDateString()}</p>
            <p>Category: {product.category}</p>
            <button onClick={onClose}>Close</button>
        </div>
      </div>
    );
  };

  export default ProductDetailsModal;
  