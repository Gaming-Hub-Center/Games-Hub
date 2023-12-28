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
                <div className="modal-body">
                    {/* Details Section */}
                    <div className="details-section">
                        <h2>Product Details</h2>
                        <div className="image-section">
                            {product.images && product.images[0] && (
                                <img src={product.images[0]} alt="Product" className="product-image" />
                            )}
                        </div>
                        <p>Title: {product.title}</p>
                        <p>Price: {product.price.toFixed(2)}</p>
                        <p>Description: {product.description}</p>
                        <p>Count: {product.count}</p>
                        <p>Seller Name: {sellerName}</p>
                        <p>Created Date: {new Date(product.postDate).toLocaleDateString()}</p>
                        <p>Category: {product.category}</p>
                    </div>
                </div>

                <button onClick={onClose}>Close</button>
            </div>
        </div>
    );
};

export default ProductDetailsModal;
