import { useEffect, useState } from "react";
import '../ProductCatalog/SellerProductCatalogItem.css'
import '../../../Components/UI_Components/GH_Btn.css'
import '../ProductEdit/SellerProductEdit.css'
import { ProductDAO } from "../../../Models/product/ProductDAO";
import { useParams } from "react-router";
import { getProduct } from "../../../Controller/API/SellerAPI";
import { Container } from "react-bootstrap";
import { NavbarC } from "../../../Components/NavbarC";

export function SellerProductView(props){

    const { sellerId, productType, productId, inCatalog } = useParams();

    const [ product, setProduct ] = useState(new ProductDAO(0, 0, '', 0, '', [0,0,0], 0, '', ''));

    useEffect(() => {
        const fetchData = async () => {
          try {
            const response = await getProduct(sellerId, productType, productId, inCatalog != 'true');
            if(response.data !== '')
                setProduct(response.data);
          } catch (error) {
            console.error('Error fetching product info:', error.message);
          }
        };
    
        fetchData();
      }, []);

    //retrieve images

    let [displayedImageIDX, setDisplayedImageIDX] = useState(0);

    return(
        <Container
        fluid
        style={{
          height: "170vh",
          padding: 0,
          backgroundColor: "#121212",
          color: 'white'
        }}
      >
        <NavbarC productType={undefined} updateProductCardPropsList={undefined} />
        <div className="product-details-form">
            <div className="product-images-column">
                {/* {product.imageURLs.map((imageURL, index) => <img 
                onMouseOver={
                    () => setDisplayedImageIDX(index)
                }
                src={imageURL} alt={"7amada helal"} />)} */}
                <div>
                    
                </div>
            </div>
            <div style={{ marginRight: '10px' }} className="product-displayed-image-container">
                <img style={{marginRight: '10px', width: '200px', height: 'auto', justifySelf: 'center'}}
                        className={`product-displayed-image ${displayedImageIDX === 0 ? 'active' : 'inactive'}`}
                        src={'https://uxwing.com/wp-content/themes/uxwing/download/business-professional-services/product-icon.png'}
                        alt={"7amada helal"}
                    />
                {/* {product.imageURLs.map(
                    ((URL, index) => <img className={`product-displayed-image ${displayedImageIDX === index ? 'active' : 'inactive'}`} src={URL} alt={"7amada helal"} />)
                )} */}
            </div>
            <div className="product-details"> 
                <h5>{product.title}</h5>
                <p><span className="product-details-type">Price:</span> ${product.price}</p>
                <p><span className="product-details-type">Stock:</span> {product.count}</p>
                <p><span className="product-details-type">Product ID:</span> {product.id}</p>
                {product.postDate !== null ? <p><span className="product-details-type">Date Added:</span>{product.postDate[2]}-{product.postDate[1]}-{product.postDate[0]}</p> : <></>}
                <hr />
                <div style={{fontSize: `14px`}} dangerouslySetInnerHTML={{ __html: product.description }} />
            </div>
        </div>
        </Container>
    )

}