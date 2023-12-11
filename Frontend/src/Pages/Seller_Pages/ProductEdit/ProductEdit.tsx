import { useEffect, useState } from "react";
import './ProductEdit.css';
import '../ProductCatalog/ProductCatalogItem.css';
import "./EditableProductDetailsItem.css";
import '../../../Components/UI_Components/GH_Btn.css'
import { ProductDAO } from "../../../Models/product/ProductDAO";
import { useNavigate, useParams } from "react-router";
import { getProduct, updateProduct } from "../../../Controller/API/SellerAPI";
import EditableProductDetailsItem from "./EditableProductDetailsItem";
import { ProductPatchDTO } from "../../../Controller/DTO/ProductPatchDTO";
import { ProductPatch } from "./Model/ProductPatch";

export function ProductEdit(props){
    const navigate = useNavigate();
    
    const { sellerId, productType, productId, inCatalog } = useParams();

    const [ product, setProduct ] = useState(new ProductDAO(0, 0, '', 0, '', [0,0,0], 0, '', ''));

    const [ productPatch, setProductPatch ] = useState<ProductPatch>(new ProductPatch('', ''))

    useEffect(() => {
        const fetchData = async () => {
          try {
            const response = await getProduct(sellerId, productType, productId, inCatalog != 'true');
            if(response.data !== ''){
                setProduct(response.data);
                setProductPatch(new ProductPatch(response.data.title, response.data.description));
            }
          } catch (error) {
            console.error('Error fetching product info:', error.message);
            // Handle errors as needed
          }
        };
    
        fetchData(); // Call the async function
      }, []);

    //retrieve images

    // let [displayedImageURLs, setDisplayedImagesURLs] = useState(productCopy.imageURLs);
    let [displayedImageIDX, setDisplayedImageIDX] = useState(0);

    // const handleDeleteImage = (e:any) => {
    //     displayedImageURLs.splice(displayedImageIDX, 1);
    //     if(displayedImageURLs.length === 1){
    //         setDisplayedImageIDX(0);
    //     }
    //     else
    //         setDisplayedImageIDX(displayedImageIDX - 1);
    //     setDisplayedImagesURLs(displayedImageURLs);
    //     console.log(displayedImageURLs);
    // }

    const handleUploadImage = (e: any) => {}

    const handleTitleChange = (newText: string) => {
        productPatch.title = newText;
    }

    const handleDescriptionChange = (newText: string) => {
        productPatch.description = newText;
    }

    const handleSaveEdit = (event) => {
        console.log(`${productPatch.title} == ${product.title} && ${productPatch.description} == ${product.description}`)
        if(productPatch.title == product.title && productPatch.description == product.description)
            return
        console.log(productPatch.getType())
        updateProduct(sellerId, productType, productId, inCatalog != 'true', productPatch.getType())
        setTimeout(() => {
            // Navigate to the specified route
            navigate(`/seller/${sellerId}/product/${productType}/${productId}/${inCatalog}`)
          }, 100);
        //save in db
    }

    return(
        <div className="product-details-form">
            <div className="product-images-column">
                {/* {displayedImageURLs.map((imageURL, index) => index === 0? null : <img key={imageURL} 
                onMouseOver={
                    () => setDisplayedImageIDX(index)
                } 
            src={imageURL} alt={"7amada helal"} />)}*/}
                <div>
                    <i className="bi bi-plus-lg"></i>
                </div>
            </div>
            
            <div className="editable-product-details-item">
                <div className="product-displayed-image-container">
                <img style={{cursor: 'pointer', marginRight: '10px', width: '300px', height: 'auto', justifySelf: 'center'}} onClick={handleUploadImage}
                    className={`product-displayed-image ${displayedImageIDX === 0 ? 'active' : 'inactive'}`}
                    src={'https://static-00.iconduck.com/assets.00/plus-large-thick-icon-1024x1024-2pcq6kcn.png'}
                    alt={"7amada helal"}
                />
                {/* {displayedImageURLs.map((URL, index) => (
                    index === 0 ? null : (
                        <img
                        key={URL}
                        className={`product-displayed-image ${displayedImageIDX === index ? 'active' : 'inactive'}`}
                        src={URL}
                        alt={"7amada helal"}
                        />
                        )
                    ))} */}
                </div>
                {/* {displayedImageURLs.length != 1 && <button onClick={handleDeleteImage} className="GH_Btn trash"><i className="bi bi-trash"></i></button>} */}
            </div>
            <div style={{marginLeft: '20px'}} className="product-details"> 
                <EditableProductDetailsItem onChange={handleTitleChange} item={productPatch.title} documentEdit={false} fontSize="1.25rem"/>
                <p><span className="product-details-type">Price:</span> ${product.price}</p>
                <p><span className="product-details-type">Stock:</span> {product.count}</p>
                <p><span className="product-details-type">Product ID:</span> {product.id}</p>
                <p><span className="product-details-type">Date Added:</span> {product.postDate[2]}-{product.postDate[1]}-{product.postDate[0]}</p>
                <hr />
                <p className="product-details-type">Description:</p>
                <EditableProductDetailsItem onChange={handleDescriptionChange} item={productPatch.description} documentEdit={true} fontSize="14px"/>
            </div>
            <button onClick={handleSaveEdit} className="GH_Btn save save-edits"><i className="bi bi-floppy"></i></button>
        </div>
    )

}