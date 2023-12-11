import { useState, useEffect } from "react";
import { getAllPendingDigitalProductsBySellerID, getAllDigitalProductsBySellerID, getAllPhysicalProductsBySellerID, getAllPendingPhysicalProductsBySellerID, deleteProduct } from "../../../Controller/API/SellerAPI";
import { ProductCatalogItem } from "./ProductCatalogItem";
import { PhysicalProductDAO } from "../../../Models/product/PhysicalProductDAO";
import { DigitalProductDAO } from "../../../Models/product/DigitalProductDAO";
import { ProductDAO } from "../../../Models/product/ProductDAO";
import { ProductRequestDAO } from "../../../Models/product_request/ProductRequestDAO";
import { useNavigate, useParams } from "react-router-dom";
import { DigitalProductRequestDAO } from "../../../Models/product_request/DigitalProductRequestDAO";
import { PhysicalProductRequestDAO } from "../../../Models/product_request/PhysicalProductRequestDAO";

interface ProductCatalogProps{
  sellerId: number
}

export function ProductCatalog() {
  const { sellerId } = useParams();
  
  const [activeTab, setActiveTab] = useState('Catalog');
  const [activeProductType, setActiveProductType] = useState('Digital');
  const [pendingPhysicalProducts, setPendingPhysicalProducts] = useState<PhysicalProductRequestDAO[]>([]);
  const [catalogPhysicalProducts, setCatalogPhysicalProducts] = useState<PhysicalProductDAO[]>([]);
  const [pendingDigitalProducts, setPendingDigitalProducts] = useState<DigitalProductRequestDAO[]>([]);
  const [catalogDigitalProducts, setCatalogDigitalProducts] = useState<DigitalProductDAO[]>([]);
  const navigate = useNavigate();

   // Delete______________________________________________________________

  const handleDeleteCatalogDigitalProduct = (item: DigitalProductDAO) => (event) => {
    deleteProduct(String(item.sellerId), 'digital', String(item.id), false);
    setCatalogDigitalProducts(catalogDigitalProducts.filter(product => product !== item))
    console.log(`deleting digital product: seller: ${String(item.sellerId)} product: ${String(item.id)}`);
  }

  const handleDeleteCatalogPhysicalProduct = (item: PhysicalProductDAO) => (event) => {
    deleteProduct(String(item.sellerId), 'physical', String(item.id), false);
    setCatalogPhysicalProducts(catalogPhysicalProducts.filter(product => product !== item))
    console.log(`deleting physical product: seller: ${String(item.sellerId)} product: ${String(item.id)}`);
  }

  const handleDeletePendingDigitalProduct = (item: DigitalProductRequestDAO) => (event) => {
    deleteProduct(String(item.sellerId), 'digital', String(item.id), true);
    setPendingDigitalProducts(pendingDigitalProducts.filter(product => product !== item))
    console.log(`deleting pending digital product: seller: ${String(item.sellerId)} product: ${String(item.id)}`);
  }

  const handleDeletePendingPhysicalProduct = (item: PhysicalProductRequestDAO) => (event) => {
    deleteProduct(String(item.sellerId), 'physical', String(item.id), true);
    setPendingPhysicalProducts(pendingPhysicalProducts.filter(product => product !== item))
    console.log(`deleting pending physical product: seller: ${String(item.sellerId)} product: ${String(item.id)}`); 
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
    console.log(`/seller/${sellerId}/product/digital/${item.id}/true`)
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
      </>
      
  );
}