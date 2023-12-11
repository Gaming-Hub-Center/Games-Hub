import { deleteProduct } from '../../../Controller/API/SellerAPI';
import { DigitalProductDAO } from '../../../Models/product/DigitalProductDAO';
import { ProductDAO } from '../../../Models/product/ProductDAO';
import { ProductRequestDAO } from '../../../Models/product_request/ProductRequestDAO';
import './ProductCatalogItem.css'
import 'bootstrap-icons/font/bootstrap-icons.css'

interface ProductCatalogItemProps{
    item: ProductDAO | ProductRequestDAO;
    onClickHandler: (event) => void;
    onDeleteHandler: (event) => void;
    onEditHandler: (event) => void;
}

export function ProductCatalogItem({item, onClickHandler, onDeleteHandler, onEditHandler}: ProductCatalogItemProps){


    return (
        <div className="catalog-item" key={item.id}>
            {/* <div className="product-image">
                <img src={item.imageURLs[0]} alt={"7amada helal"} />
            </div> */}
            <div onClick={onClickHandler} className="product-details">
                <h5 className="product-details-title">{item.title}</h5> 
                <p><span className="product-details-type">Stock:</span> {item.count}</p>
                <p><span className="product-details-type">Product ID:</span> {item.id}</p>
                {item.postDate != null ? <p><span className="product-details-type">Date Added:</span> {item.postDate[2]}-{item.postDate[1]}-{item.postDate[0]}</p> : <></>}
            </div>
            <span className="product-price">${item.price}</span>
            <div className="catalog-actions">
                <button onClick={onDeleteHandler} className="GH_Btn trash"><i className="bi bi-trash"></i></button><br />
                <button onClick={onEditHandler} className="GH_Btn edit"><i className="bi bi-pencil"></i></button>
            </div>
        </div> 
    );
}