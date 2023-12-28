import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { deleteProduct, getAllDigitalProductsBySellerID, getAllPendingDigitalProductsBySellerID, getAllPendingPhysicalProductsBySellerID, getAllPhysicalProductsBySellerID } from "../../../Controller/API/SellerAPI";
import { DigitalProductDAO } from "../../../Models/product/DigitalProductDAO";
import { PhysicalProductDAO } from "../../../Models/product/PhysicalProductDAO";
import { DigitalProductRequestDAO } from "../../../Models/product_request/DigitalProductRequestDAO";
import { PhysicalProductRequestDAO } from "../../../Models/product_request/PhysicalProductRequestDAO";
import { ProductCatalogItem } from "./SellerProductCatalogItem";
import {getId} from "../../../CurrentSession";
import { Container } from "react-bootstrap";
import { NavbarC } from "../../../Components/NavbarC";

export function SellerProductCatalog() {
  const sellerId = window.sessionStorage.getItem("id");
  const [activeTab, setActiveTab] = useState('Catalog');
  const [activeProductType, setActiveProductType] = useState('Digital');
  const [pendingPhysicalProducts, setPendingPhysicalProducts] = useState<PhysicalProductRequestDAO[]>([]);
  const [catalogPhysicalProducts, setCatalogPhysicalProducts] = useState<PhysicalProductDAO[]>([]);
  const [pendingDigitalProducts, setPendingDigitalProducts] = useState<DigitalProductRequestDAO[]>([]);
  const [catalogDigitalProducts, setCatalogDigitalProducts] = useState<DigitalProductDAO[]>([]);
  const navigate = useNavigate();

   // Delete______9________________________________________________________

  const handleDeleteCatalogDigitalProduct = (item: DigitalProductDAO) => (event) => {
    deleteProduct(getId().toString(), 'digital', String(item.id), false);
    setCatalogDigitalProducts(catalogDigitalProducts.filter(product => product !== item))
  }

  const handleDeleteCatalogPhysicalProduct = (item: PhysicalProductDAO) => (event) => {
    deleteProduct(getId().toString(), 'physical', String(item.id), false);
    setCatalogPhysicalProducts(catalogPhysicalProducts.filter(product => product !== item))
  }

  const handleDeletePendingDigitalProduct = (item: DigitalProductRequestDAO) => (event) => {
    deleteProduct(String(item.seller.id), 'digital', String(item.id), true);
    setPendingDigitalProducts(pendingDigitalProducts.filter(product => product !== item))
  }

  const handleDeletePendingPhysicalProduct = (item: PhysicalProductRequestDAO) => (event) => {
    deleteProduct(String(item.seller.id), 'physical', String(item.id), true);
    setPendingPhysicalProducts(pendingPhysicalProducts.filter(product => product !== item))
  }

   // Go To Edit______________________________________________________________

  let handleEditCatalogDigitalProduct = (item: DigitalProductDAO) => (event) => {
    navigate(`/seller/${sellerId}/edit/product/digital/${item.id}/true`)
  };

  let handleEditCatalogPhysicalProduct = (item: PhysicalProductDAO) => (event) => {
    navigate(`/seller/${sellerId}/edit/product/physical/${item.id}/true`) 
  };

  let handleEditPendingDigitalProduct = (item: DigitalProductRequestDAO) => (event) => {
    navigate(`/seller/${sellerId}/edit/product/digital/${item.id}/false`)
  };

  let handleEditPendingPhysicalProduct = (item: PhysicalProductRequestDAO) => (event) => {
    navigate(`/seller/${sellerId}/edit/product/physical/${item.id}/false`)
  };

  // Go To View______________________________________________________________

  let handleViewCatalogDigitalProduct = (item: DigitalProductDAO) => (event) => {
    navigate(`/seller/${sellerId}/product/digital/${item.id}/true`)  
  };

  let handleViewCatalogPhysicalProduct = (item: PhysicalProductDAO) => (event) => {
    navigate(`/seller/${sellerId}/product/physical/${item.id}/true`)  
  };

  let handleViewPendingDigitalProduct = (item: DigitalProductRequestDAO) => (event) => {
    navigate(`/seller/${sellerId}/product/digital/${item.id}/false`)  
  };

  let handleViewPendingPhysicalProduct = (item: PhysicalProductRequestDAO) => (event) => {
    navigate(`/seller/${sellerId}/product/physical/${item.id}/false`)  
  };

  // Get Data______________________________________________________________
  useEffect(() => {
    const fetchData = async () => {
      try {
        const products = await getAllPendingPhysicalProductsBySellerID(sellerId);
        setPendingPhysicalProducts(products.data);
      } catch (error) {
        console.error('Error fetching pending physical products:', error.message);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const products = await getAllPendingDigitalProductsBySellerID(sellerId);
        setPendingDigitalProducts(products.data);
      } catch (error) {
        console.error('Error fetching pending digital products:', error.message);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const products = await getAllDigitalProductsBySellerID(sellerId);
        setCatalogDigitalProducts(products.data);
        console.log(products.data)
      } catch (error) {
        console.error('Error fetching digital products:', error.message);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    
    const fetchData = async () => {
      try {
        const products = await getAllPhysicalProductsBySellerID(sellerId);
        setCatalogPhysicalProducts(products.data);
      } catch (error) {
        console.error('Error fetching physical products:', error.message);
      }
    };

    fetchData();
  }, []);

  const handleCreateNewProduct = (event) => {
    navigate('/seller/create-product');
  } 
  
  return (
      <>
      <Container
        fluid
        style={{
          height: "170vh",
          padding: 0,
          backgroundColor: "#121212",
          color: "white",
        }}
      >
      <NavbarC productType={undefined} updateProductCardPropsList={undefined} />
      <div style={{ display: 'flex', justifyContent: 'center' }}>
        <div className="tab-content">
          <div style={{ width: '60vw'}} className="GH_Btn_series">
              <button type="button" className={`GH_Btn general ${activeTab === 'Catalog' ? 'active' : 'inactive'} left`} onClick={() => {setActiveTab('Catalog'); setActiveProductType('Digital')}}>
              Catalog
              </button>
              <button type="button" className={`GH_Btn general ${activeTab === 'Pending' ? 'active' : 'inactive'} right`} onClick={() => {setActiveTab('Pending'); setActiveProductType('Digital')}}>
              Pending
              </button>
          </div>
            <div className="GH_Btn_series" style={{width: '60vw'}}>
                <button style={{whiteSpace: 'nowrap'}} type="button" className={`GH_Btn general ${activeProductType === 'Digital' ? `active` : 'inactive'} left`}onClick={() => setActiveProductType('Digital')}>Digital Products</button>
                <button style={{whiteSpace: 'nowrap', overflowWrap: 'normal'}} type="button" className={`GH_Btn general ${activeProductType === 'Physical' ? `active` : 'inactive'} right`}onClick={() => setActiveProductType('Physical')}>Physical products</button>
                <button onClick={handleCreateNewProduct} style={{paddingLeft: '30px', paddingRight: '30px'}} className="GH_Btn general add"><i className="bi bi-plus-lg"></i></button>
            </div>
            <ul className={`list-group ${(activeTab === 'Catalog' && activeProductType === 'Digital') ? 'fade-in' : 'fade-out'}`}>
                {catalogDigitalProducts.map((item: DigitalProductDAO) => <ProductCatalogItem item={item} onClickHandler={handleViewCatalogDigitalProduct(item)} onDeleteHandler={handleDeleteCatalogDigitalProduct(item)} onEditHandler={handleEditCatalogDigitalProduct(item)}/>)}
            </ul>
            <ul className={`list-group ${(activeTab === 'Catalog' && activeProductType === 'Physical') ? 'fade-in' : 'fade-out'}`}>
                {catalogPhysicalProducts.map((item: PhysicalProductDAO) => <ProductCatalogItem item={item} onClickHandler={handleViewCatalogPhysicalProduct(item)} onDeleteHandler={handleDeleteCatalogPhysicalProduct(item)} onEditHandler={handleEditCatalogPhysicalProduct(item)}/>)}
            </ul>
            <ul className={`list-group ${(activeTab === 'Pending' && activeProductType === 'Digital') ? 'fade-in' : 'fade-out'}`}>
                {pendingDigitalProducts.map((item: DigitalProductRequestDAO) => <ProductCatalogItem item={item} onClickHandler={handleViewPendingDigitalProduct(item)} onDeleteHandler={handleDeletePendingDigitalProduct(item)} onEditHandler={handleEditPendingDigitalProduct(item)}/>)}
            </ul>
            <ul className={`list-group ${(activeTab === 'Pending' && activeProductType === 'Physical')? 'fade-in' : 'fade-out'}`}>
                {pendingPhysicalProducts.map((item: PhysicalProductRequestDAO) => <ProductCatalogItem item={item} onClickHandler={handleViewPendingPhysicalProduct(item)} onDeleteHandler={handleDeletePendingPhysicalProduct(item)} onEditHandler={handleEditPendingPhysicalProduct(item)}/>)}
            </ul>
        </div>
      </div>
      </Container>
      </>
      
  );
}