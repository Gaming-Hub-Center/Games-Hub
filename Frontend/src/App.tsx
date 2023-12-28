import { Routes, Route } from "react-router-dom";
import { Container } from "react-bootstrap";
import { Welcome } from "./Pages/Welcome";
import { SignIn } from "./Pages/SignIn";
import { SignUpSeller } from "./Pages/SignUp/SignUpSeller";
import { SignUpBuyer } from "./Pages/SignUp/SignUpBuyer";
import { HomeGames } from "./Pages/Buyer/Home/Games";
import { About } from "./Pages/About";
import { HomeAccessories } from "./Pages/Buyer/Home/Accessories";
import "./App.css";
import CatalogRequestForm from "./Pages/product-request-form/CatalogRequestForm";

import PhysicalCart from './Pages/PhysicalCart';
import DigitalCart from "./Pages/DigitalCart";

import { ProductView } from "./Components/ProductView";
import { SellerProductCatalog } from "./Pages/Seller_Pages/ProductCatalog/SellerProductCatalog";
import { SellerProductView } from "./Pages/Seller_Pages/ProductView/SellerProductView";
import { SellerProductEdit } from "./Pages/Seller_Pages/ProductEdit/SellerProductEdit";
import HomePageAdmin from "./Pages/admin/AdminDashboard";
import { gapi } from "gapi-script";

import PhysicalWishlist from "./Pages/Physical_wishList";
import DigitalWishlist from "./Pages/Digital_wishList";
import BuyerProfilePage from "./Pages/Profile-Page/buyer_profile_page";
import SellerProfilePage from "./Pages/Profile-Page/seller_profile_page";


import {SellerView} from "./Pages/admin/SellerView";
import {BuyerView} from "./Pages/admin/BuyerView";

import BuyerOrders from "./Pages/BuyerOrders";
import {AdminView} from "./Pages/admin/AdminView";
import {SellerCatalogView} from "./Pages/admin/SellerCatalogView";


const clientId = "922788866859-fv5d49j6cqd2orfai2c1dnte4c8v5ii8.apps.googleusercontent.com";

function App() {
  useEffect (() => {
    
      function start() {
        gapi.client.init({
        clientId: clientId,
        scope: ""
      })
    };
      gapi.load('client:auth2', start);
  });
  return (
    <Container
      fluid
      style={{
        paddingLeft: 0,
        paddingRight: 0,
        marginLeft: 0,
        marginRight: 0,
      }}
    >
      <Routes>
        <Route path="/" element={<Welcome />}></Route>
        <Route path="/about" element={<About />}></Route>
        <Route path="/signin" element={<SignIn />}></Route>
        <Route path="/signup/buyer" element={<SignUpBuyer />}></Route>
        <Route path="/signup/seller" element={<SignUpSeller />}></Route>

        <Route path="/buyer/profile" element={<BuyerProfilePage/>}></Route>
        <Route path="/seller/profile" element={<SellerProfilePage/>}></Route> 


        <Route path="/seller/create-product" element={<CatalogRequestForm/>}></Route>
        <Route path="/physical-cart" element={<PhysicalCart />}></Route>
        <Route path="/digital-cart" element={<DigitalCart />}></Route>

        <Route path="/seller/catalog" element={<SellerProductCatalog />}></Route>
        <Route path="/seller/:sellerId/product/:productType/:productId/:inCatalog" element={<SellerProductView />}></Route>
        <Route path="/seller/:sellerId/edit/product/:productType/:productId/:inCatalog" element={<SellerProductEdit />}></Route>

        <Route path="/buyer/home/games" element={<HomeGames />}></Route>
        <Route
          path="/buyer/home/accessories"
          element={<HomeAccessories />}
        ></Route>
        <Route
          path="/seller/create-product"
          element={<CatalogRequestForm />}
        ></Route>
        <Route path="/buyer/productview/:id" element={<ProductView />}></Route>
        <Route path="/physical-wishlist" element={<PhysicalWishlist />}></Route>
        <Route path="/digital-wishlist" element={<DigitalWishlist />}></Route>
        <Route path="/admin/view/sellers" element={<SellerView />}></Route>
        <Route path="/admin/view/buyers" element={<BuyerView />}></Route>
        <Route path="/admin/view/admins/:adminId" element={<AdminView />}></Route>
        <Route path="/admin/view/seller/products/:sellerId" element={<SellerCatalogView />}></Route>
        <Route path="/buyer/orders" element={<BuyerOrders />}></Route>
        <Route path="/admin/dashboard" element={<HomePageAdmin/>}></Route>
        {/* <Route path="/test" element={<Profile />}></Route> */}
      </Routes>
    </Container>
  );
}

export default App;
