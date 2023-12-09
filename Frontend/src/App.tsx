import { Routes, Route } from "react-router-dom";
import { Container } from "react-bootstrap";
import { Welcome } from "./Pages/Welcome";
import { SignIn } from "./Pages/SignIn";
import { SignUpSeller } from "./Pages/SignUp/SignUpSeller";
import { SignUpBuyer } from "./Pages/SignUp/SignUpBuyer";
import { HomeGames } from "./Pages/Buyer/Home/Games";
import { NavbarC } from "./Components/NavbarC";
import { About } from "./Pages/About";
import { HomeAccessories } from "./Pages/Buyer/Home/Accessories";

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
        <Route path="/buyer/home/games" element={<HomeGames />}></Route>
        <Route
          path="/buyer/home/accessories"
          element={<HomeAccessories />}
        ></Route>
      </Routes>
    </Container>
  );
}

export default App;
