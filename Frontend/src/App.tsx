import { Routes, Route } from "react-router-dom";
import { Container, Navbar } from "react-bootstrap";
import { Welcome } from "./Pages/Welcome";
import { SignUp } from "./Pages/SignUp";
import { NavbarC } from "./Components/NavbarC";
import { SignUpNavbar } from "./Components/SignUpNavbar";

import { SignIn } from "./Pages/SignIn";
import { SignUpSeller } from "./Pages/SignUpSeller";

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
        <Route path="/signup" element={<SignUp />}></Route>
        <Route path="/signin" element={<SignIn />}></Route>
        <Route path="/signupseller" element={<SignUpSeller />}></Route>
      </Routes>
    </Container>
  );
}

export default App;
