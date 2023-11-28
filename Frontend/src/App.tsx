import { Routes, Route } from "react-router-dom";
import { Container, Navbar } from "react-bootstrap";
import { Welcome } from "./Pages/Welcome";
import { SignUp } from "./Pages/SignUp";
import { NavbarC } from "./Components/NavbarC";
import { SignIn } from "./Pages/SignIn";

function App() {
  return (
    <Container fluid
      style={{
        paddingLeft: 0,
        paddingRight: 0,
        marginLeft: 0,
        marginRight: 0,
      }}
    >
      <Routes>
        <Route path="/signup" element={<SignUp />}></Route>
        <Route path="/signin" element={<SignIn />}></Route>

      </Routes>
    </Container>
  );
}

export default App;
