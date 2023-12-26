import { useState } from "react";
import { ProductDTO } from "../../Controller/DTO/ProductDTO/ProductDTO";
import './ProductModal.css'

type ProductDetailsModalProps = {
    product: ProductDTO | null;
    isOpen: boolean;
    onClose: () => void;
  };
  
  const ProductDetailsModal: React.FC<ProductDetailsModalProps> = ({ product, isOpen, onClose }) => {
    if (!isOpen) return null;
    const [selectedImage, setSelectedImage] = useState<string | null>(null);

    // Dummy product data
    const dummyProduct = {
      id: 101,
      title: "Dummy Product Title",
      price: 39.99,
      description: "This is a dummy product description.",
      count: 10,
      sellerID: 2001,
      created_date: new Date().toISOString(),
      category: "Electronics",
      images: ["D:/CSED/semester5/Games-Hub/Games-Hub/Frontend/src/Components/admin/download.jpeg"],
      code: "CODE101"
    };

    const handleImageClick = (image: string) => {
        setSelectedImage(image);
    };

    return (
      <div className="admin-modal-overlay">
        <div className="admin-modal-content">
            <h2>Product Details</h2>
            {/* Display dummy product details */}
            <p>ID: {dummyProduct.id}</p>
            <p>Title: {dummyProduct.title}</p>
            <p>Price: {dummyProduct.price.toFixed(2)}</p>
            <p>Description: {dummyProduct.description}</p>
            <p>Count: {dummyProduct.count}</p>
            <p>Seller ID: {dummyProduct.sellerID}</p>
            <p>Created Date: {new Date(dummyProduct.created_date).toLocaleDateString()}</p>
            <p>Category: {dummyProduct.category}</p>
            <p>Code: {dummyProduct.code}</p>
            <div className="image-gallery">
            {product.images && product.images.map((image, index) => (
              <img
                key={index}
                src={image}
                alt={`Product ${product.id}`}
                className="product-image-thumbnail"
                onClick={() => handleImageClick(image)}
              />
            ))}
            </div>
  
            {/* Larger image view section */}
            {selectedImage && (
                <div className="large-image-container">
                    <img src={selectedImage} alt="Selected product" className="large-image" />
                </div>
            )}
  
            <button onClick={onClose}>Close</button>
        </div>
      </div>
    );
  };

  export default ProductDetailsModal;
  