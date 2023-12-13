import { Routes, Route } from "react-router-dom";
import { Container } from "react-bootstrap";
import { Welcome } from "./Pages/Welcome";
import { SignIn } from "./Pages/SignIn";
import { SignUpSeller } from "./Pages/SignUp/SignUpSeller";
import { SignUpBuyer } from "./Pages/SignUp/SignUpBuyer";
import CatalogRequestForm from "./Components/product-request-form/CatalogRequestForm";
import { ProductCatalog } from "./Pages/Seller_Pages/ProductCatalog/ProductCatalog";
import { ProductView } from "./Pages/Seller_Pages/ProductView/ProductView";
import { ProductEdit } from "./Pages/Seller_Pages/ProductEdit/ProductEdit";

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
        <Route path="/welcome" element={<Welcome />}></Route>
        <Route path="/signin" element={<SignIn />}></Route>
        <Route path="/signup/buyer" element={<SignUpBuyer />}></Route>
        <Route path="/signup/seller" element={<SignUpSeller />}></Route>
        <Route path="/seller/create-product" element={<CatalogRequestForm/>}></Route>
        <Route path="/seller/:sellerId/catalog" element={<ProductCatalog />}></Route>
        <Route path="/seller/:sellerId/product/:productType/:productId/:inCatalog" element={<ProductView />}></Route>
        <Route path="/seller/:sellerId/edit/product/:productType/:productId/:inCatalog" element={<ProductEdit />}></Route>
      </Routes>
    </Container>
  );
}

export default App;
