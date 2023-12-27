import { useEffect, useState } from "react";
import { ProductDTO } from "../../Controller/DTO/ProductDTO/ProductDTO";
import './ProductModal.css'
import { httpRequest } from "../../Controller/HttpProxy";
import { ProductRequestDTO } from "../../Controller/DTO/request-dto/ProductRequestDTO";

type ProductDetailsModalProps = {
    product: ProductRequestDTO | null;
    isOpen: boolean;
    onClose: () => void;
};

const ProductDetailsModal: React.FC<ProductDetailsModalProps> = ({ product, isOpen, onClose }) => {
    if (!isOpen || !product) return null;

    const [selectedImage, setSelectedImage] = useState<string | null>(null);
    const [sellerName, setSellerName] = useState<string>('');

    useEffect(() => {
        if (product.sellerId) {
            httpRequest('GET', `/seller/name`, null, { id: product.sellerId })
                .then(response => setSellerName(response.data))
                .catch(error => console.error('Error fetching seller name:', error));
        }
    }, [product]);

    useEffect(() => {
        // Set the first image as the selected image by default
        if (product.images && product.images.length > 0) {
            setSelectedImage(product.images[0]);
        }
    }, [product.images]);

    return (
        <div className="admin-modal-overlay">
            <div className="admin-modal-content">
                <h2>Product Details</h2>
                {/* Display product details */}
                <p>Title: {product.title}</p>
                <p>Price: {product.price.toFixed(2)}</p>
                <p>Description: {product.description}</p>
                <p>Count: {product.count}</p>
                <p>Seller Name: {sellerName}</p>
                <p>Created Date: {new Date(product.postDate).toLocaleDateString()}</p>
                <p>Category: {product.category}</p>

                {/* Image Gallery */}
                <div className="image-gallery">
                    {product.images && product.images.map((image, index) => (
                        <img
                            key={index}
                            src={image}
                            alt={`Product Image ${index}`}
                            className="thumbnail"
                            onClick={() => setSelectedImage(image)}
                        />
                    ))}
                </div>
                {selectedImage && <img src={selectedImage} alt="Selected Product" className="selected-image" />}

                <button onClick={onClose}>Close</button>
            </div>
        </div>
    );
};

export default ProductDetailsModal;
