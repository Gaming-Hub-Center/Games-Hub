import { useState, useEffect } from "react";
import { getAllPendingDigitalProductsBySellerID, getAllDigitalProductsBySellerID, getAllPhysicalProductsBySellerID, getAllPendingPhysicalProductsBySellerID, deleteProduct } from "../../../Controller/API/SellerAPI";
import { ProductCatalogItem } from "./ProductCatalogItem";
import { PhysicalProductDAO } from "../../../Models/product/PhysicalProductDAO";
import { DigitalProductDAO } from "../../../Models/product/DigitalProductDAO";
import { ProductDAO } from "../../../Models/product/ProductDAO";
import { ProductRequestDAO } from "../../../Models/product_request/ProductRequestDAO";
import { useNavigate, useParams } from "react-router-dom";
import { right } from "@popperjs/core";

interface ProductCatalogProps{
  sellerId: number
}

export function ProductCatalog() {
  const { sellerId } = useParams();
  
  const [activeTab, setActiveTab] = useState('Catalog');
  const [activeProductType, setActiveProductType] = useState('Digital');
  const [pendingPhysicalProducts, setPendingPhysicalProducts] = useState<PhysicalProductDAO[]>([]);
  const [catalogPhysicalProducts, setCatalogPhysicalProducts] = useState<PhysicalProductDAO[]>([]);
  const [pendingDigitalProducts, setPendingDigitalProducts] = useState<DigitalProductDAO[]>([]);
  const [catalogDigitalProducts, setCatalogDigitalProducts] = useState<DigitalProductDAO[]>([]);
  const navigate = useNavigate();

  const handleDeleteCatalogDigitalProduct = (item: ProductDAO | ProductRequestDAO) => (event) => {
    deleteProduct(item.sellerId, 'digital', item.id, false);
    setCatalogDigitalProducts(catalogDigitalProducts.filter(product => product !== item))
    console.log(`deleting digital product: seller: ${item.sellerId} product: ${item.id}`);
  }

  const handleDeleteCatalogPhysicalProduct = (item: ProductDAO | ProductRequestDAO) => (event) => {
    // deleteProduct(item.sellerId, 'physical', item.id, false);
    setCatalogPhysicalProducts(catalogPhysicalProducts.filter(product => product !== item))
    console.log(`deleting physical product: seller: ${item.sellerId} product: ${item.id}`);
  }

  const handleDeletePendingDigitalProduct = (item: ProductDAO | ProductRequestDAO) => (event) => {
    // deleteProduct(item.sellerId, 'digital', item.id, true);
    setPendingDigitalProducts(pendingDigitalProducts.filter(product => product !== item))
    console.log(`deleting pending digital product: seller: ${item.sellerId} product: ${item.id}`);
  }

  const handleDeletePendingPhysicalProduct = (item: ProductDAO | ProductRequestDAO) => (event) => {
    // deleteProduct(item.sellerId, 'physical', item.id, true);
    setPendingPhysicalProducts(pendingPhysicalProducts.filter(product => product !== item))
    console.log(`deleting pending physical product: seller: ${item.sellerId} product: ${item.id}`); 
  }

  let handleEditCatalogDigitalProduct = () => {
    // navigate()  
  };

  let handleEditCatalogPhysicalProduct = () => {
    // navigate()  
  };

  let handleEditPendingDigitalProduct = () => {
    // navigate()  
  };

  let handleEditPendingPhysicalProduct = () => {
    // navigate()  
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const products = await getAllPendingPhysicalProductsBySellerID(sellerId);
        console.log(products.data)
        setPendingPhysicalProducts(products.data);
      } catch (error) {
        console.error('Error fetching pending physical products:', error.message);
        // Handle errors as needed
      }
    };

    fetchData(); // Call the async function
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const products = await getAllPendingDigitalProductsBySellerID(sellerId);
        console.log(products.data)
        setPendingDigitalProducts(products.data);
      } catch (error) {
        console.error('Error fetching pending digital products:', error.message);
        // Handle errors as needed
      }
    };

    fetchData(); // Call the async function
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const products = await getAllDigitalProductsBySellerID(sellerId);
        console.log(products.data)
        setCatalogDigitalProducts(products.data);
      } catch (error) {
        console.error('Error fetching digital products:', error.message);
        // Handle errors as needed
      }
    };

    fetchData(); // Call the async function
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const products = await getAllPhysicalProductsBySellerID(sellerId);
        console.log(products.data)
        setCatalogPhysicalProducts(products.data);
      } catch (error) {
        console.error('Error fetching physical products:', error.message);
        // Handle errors as needed
      }
    };

    fetchData(); // Call the async function
  }, []);

  const handleCreateNewProduct = (event) => {
    console.log('navigate')
    //navigate to create new product
  } 
  
  return (
      <>
      <div>
          <button type="button" className={`GH_Btn ${activeTab === 'Catalog' ? 'active' : ''}`} onClick={() => {setActiveTab('Catalog'); setActiveProductType('Digital')}}>
          Catalog
          </button>
          <button type="button" className={`GH_Btn ${activeTab === 'Pending' ? 'active' : ''}`} onClick={() => {setActiveTab('Pending'); setActiveProductType('Digital')}}>
          Pending
          </button>
      </div>
      <div className="tab-content">
          <div style={{position: 'relative', display: 'flex', width: '600px'}}>
              <button  type="button" className={`GH_Btn ${activeProductType === 'Digital' ? `active` : ''}`}onClick={() => setActiveProductType('Digital')}>Digital Products</button>
              <button  type="button" className={`GH_Btn ${activeProductType === 'Physical' ? `active` : ''}`}onClick={() => setActiveProductType('Physical')}>Physical products</button>
              <button onClick={handleCreateNewProduct} style={{marginLeft: 'auto'}} className="GH_Btn add create-new-product">Add</button>
          </div>
          <ul className={`list-group ${(activeTab === 'Catalog' && activeProductType === 'Digital') ? 'fade-in' : 'fade-out'}`}>
              {catalogDigitalProducts.map((item: DigitalProductDAO) => <ProductCatalogItem item={item} onDeleteHandler={handleDeleteCatalogDigitalProduct(item)} onEditHandler={handleEditCatalogDigitalProduct}/>)}
          </ul>
          <ul className={`list-group ${(activeTab === 'Catalog' && activeProductType === 'Physical') ? 'fade-in' : 'fade-out'}`}>
              {catalogPhysicalProducts.map((item: PhysicalProductDAO) => <ProductCatalogItem item={item} onDeleteHandler={handleDeleteCatalogPhysicalProduct(item)} onEditHandler={handleEditCatalogPhysicalProduct}/>)}
          </ul>
          <ul className={`list-group ${(activeTab === 'Pending' && activeProductType === 'Digital') ? 'fade-in' : 'fade-out'}`}>
              {pendingDigitalProducts.map((item: DigitalProductDAO) => <ProductCatalogItem item={item} onDeleteHandler={handleDeletePendingDigitalProduct(item)} onEditHandler={handleEditPendingDigitalProduct}/>)}
          </ul>
          <ul className={`list-group ${(activeTab === 'Pending' && activeProductType === 'Physical')? 'fade-in' : 'fade-out'}`}>
              {pendingPhysicalProducts.map((item: PhysicalProductDAO) => <ProductCatalogItem item={item} onDeleteHandler={handleDeletePendingPhysicalProduct(item)} onEditHandler={handleEditPendingPhysicalProduct}/>)}
          </ul>
      </div>
      </>
      
  );
}