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
import BuyerOrders from "./Pages/BuyerOrders";
import HomePageAdmin from "./Pages/admin/AdminDashboard";


function App() {
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
        <Route path="/buyer/orders" element={<BuyerOrders />}></Route>
        <Route path="/admin/dashboard" element={<HomePageAdmin/>}></Route>
      </Routes>
    </Container>
  );
}

export default App;
