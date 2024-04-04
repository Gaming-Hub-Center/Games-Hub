import { SetStateAction, useState } from "react";
import { Form, Button, Col, Row, Container } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { isValidPhoneNumber } from "react-phone-number-input";
import "react-phone-number-input/style.css";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faEnvelope,
  faLock,
  faUser,
  faAddressCard,
  faPhone,
} from "@fortawesome/free-solid-svg-icons";
import { httpRequest } from "../../Controller/HttpProxy";

import { SignUpNavbar } from "../../Components/SignUp/SignUpNavbar";
import PhoneNumberInput from "../../Components/SignUp/PhoneNumberInputC";

import { BuyerRegistrationDTO } from "../../Controller/DTO/user/BuyerRegistrationDTO";
<<<<<<< Updated upstream
=======
import { GoogleSignUpButtonBuyer } from "../../Components/googleAuthButtons/googleSignupBuyer";
import {getCurrentProductPage, setRole, setToken} from "../../session/CurrentSession";
import {updateSessionPeriodically} from "../../session/UpdateSession";
import {ProductType} from "../../enums/ProductType";
>>>>>>> Stashed changes

export function SignUpBuyer() {
  const [validated, setValidated] = useState(false);
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [phoneInvalid, setPhoneInvalid] = useState(false); // State for phone number validation
  const [address, setAddress] = useState("");
  const navigate = useNavigate();

  const isValidName = (name: string) => {
    return name.length > 0 && /^[a-zA-Z]*$/.test(name);
  };

  const isValidEmail = (email: string) => {
    return /^[^\s@]+@[^\s@]+\.[c][o][m]$/.test(email);
  };

  const isValidPassword = (password: string | any[]) => {
    return password.length >= 8;
  };

  const isPasswordMatch = (password: string, confirmPassword: string) => {
    return confirmPassword.length >= 8 && password === confirmPassword;
  };

  const isValidAddress = (address: string) => {
    return address.length > 0;
  };

  const handlenameChange = (event: {
    target: { value: SetStateAction<string> };
  }) => {
    setName(event.target.value);
  };

  const handleEmailChange = (event: {
    target: { value: SetStateAction<string> };
  }) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event: {
    target: { value: SetStateAction<string> };
  }) => {
    setPassword(event.target.value);
  };

  const handleConfirmPasswordChange = (event: {
    target: { value: SetStateAction<string> };
  }) => {
    setConfirmPassword(event.target.value);
  };

  const handleAddressChange = (event: {
    target: { value: SetStateAction<string> };
  }) => {
    setAddress(event.target.value);
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.stopPropagation();
    }

    event.preventDefault();

    const buyerRegistrationDTO: BuyerRegistrationDTO = {
      name: name,
      email: email,
      password: password,
      phone: phoneNumber,
      address: address,
    };

    httpRequest('POST', 'registration/buyer', buyerRegistrationDTO)
      .then((response) => {
        const token = response.data as string
        const role = JSON.parse(atob(token.split(".")[1])).role
        setRole(role)
        setToken(token)
        setValidated(true)
        if (role === 'ADMIN')
          navigate('/admin/dashboard')
        else if (role === 'SELLER')
          navigate('/seller/catalog')
        else
          navigate(`/buyer/home/${getCurrentProductPage() === ProductType.PHYSICAL ? 'accessories' : 'games'}`)
        console.log(role)
        console.log(token)
        updateSessionPeriodically()
      })
      .catch((error) => {
        console.log(error)
        alert(error.response.data.message)
      })
  };

  return (
      <div
          style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            height: "100vh",
            backgroundImage: `url("/src/data/back2.jpg")`, // Replace with the path to your background image
            backgroundSize: "cover",
            backgroundPosition: "center",
          }}
      >
        <Form
            style={{
              width: "30%",
              backgroundColor: "#121212",
              color: "#f0f0f0",
              padding: "20px",
              borderRadius: "8px",
              boxShadow: "0 0 10px rgba(0,0,0,0.1)",
            }}
            noValidate
            validated={validated}
            onSubmit={handleSubmit}
        >
          <h2 style={{ textAlign: "center", marginBottom: "20px" }}>Sign Up</h2>
          <SignUpNavbar></SignUpNavbar>
          <Row className="mb-3">
            <Form.Group as={Col} controlId="validationCustomName">
              <Form.Label style={{ color: "#f0f0f0" }}>Name</Form.Label>
              <Container fluid style={{ padding: 0 }}>
                <Row>
                  <Col md={1} style={{ paddingTop: 8 }}>
                    <FontAwesomeIcon
                        icon={faUser}
                        style={{
                          color: "#733BC0",
                          fontSize: "20px",
                          marginRight: "10px",
                        }}
                    />
                  </Col>
                  <Col md={11}>
                    <Form.Control
                        required
                        type="text"
                        placeholder="Enter your name"
                        value={name}
                        onChange={handlenameChange}
                        isInvalid={!isValidName(name)}
                    />
                  </Col>
                </Row>
              </Container>
            </Form.Group>
          </Row>
          <Row className="mb-3">
            <Form.Group as={Col} controlId="validationCustomEmail">
              <Form.Label style={{ color: "#f0f0f0" }}>Email</Form.Label>
              <Container fluid style={{ padding: 0 }}>
                <Row>
                  <Col md={1} style={{ paddingTop: 8 }}>
                    <FontAwesomeIcon
                        icon={faEnvelope}
                        style={{
                          color: "#733BC0",
                          fontSize: "20px",
                          marginRight: "10px",
                        }}
                    />
                  </Col>
                  <Col md={11}>
                    <Form.Control
                        required
                        type="text"
                        placeholder="Enter your email"
                        value={email}
                        onChange={handleEmailChange}
                        isInvalid={!isValidEmail(email)}
                    />
                  </Col>
                </Row>
              </Container>
            </Form.Group>
          </Row>
          <Row className="mb-3">
            <Form.Group as={Col} controlId="validationCustomPassword">
              <Form.Label style={{ color: "#f0f0f0" }}>Password</Form.Label>
              <Container fluid style={{ padding: 0 }}>
                <Row>
                  <Col md={1} style={{ paddingTop: 8 }}>
                    <FontAwesomeIcon
                        icon={faLock}
                        style={{
                          color: "#733BC0",
                          fontSize: "20px",
                          marginRight: "10px",
                        }}
                    />
                  </Col>
                  <Col md={11}>
                    <Form.Control
                        required
                        type="password"
                        placeholder="Enter your password"
                        value={password}
                        onChange={handlePasswordChange}
                        isInvalid={!isValidPassword(password)}
                    />
                  </Col>
                </Row>
              </Container>
              <Form.Control.Feedback type="invalid"></Form.Control.Feedback>
            </Form.Group>
          </Row>
          <Row className="mb-3">
            <Form.Group as={Col} controlId="validationCustomConfirmPassword">
              <Form.Label style={{ color: "#f0f0f0" }}>
                Confirm Password
              </Form.Label>
              <Container fluid style={{ padding: 0 }}>
                <Row>
                  <Col md={1} style={{ paddingTop: 8 }}>
                    <FontAwesomeIcon
                        icon={faLock}
                        style={{
                          color: "#733BC0",
                          fontSize: "20px",
                          marginRight: "10px",
                        }}
                    />
                  </Col>
                  <Col md={11}>
                    <Form.Control
                        required
                        type="password"
                        placeholder="Confirm your password"
                        value={confirmPassword}
                        onChange={handleConfirmPasswordChange}
                        isInvalid={!isPasswordMatch(password, confirmPassword)}
                    />
                    <Form.Control.Feedback type="invalid"></Form.Control.Feedback>
                  </Col>
                </Row>
              </Container>
            </Form.Group>
          </Row>
          <Row className="mb-3">
            <Form.Group as={Col} controlId="validationPhone">
              <Form.Label style={{ color: "#f0f0f0" }}>
                <FontAwesomeIcon
                    icon={faPhone}
                    style={{
                      color: "#733BC0",
                      fontSize: "20px",
                      marginRight: "10px",
                    }}
                />
                Phone number
              </Form.Label>
              <PhoneNumberInput
                  value={phoneNumber}
                  onChange={setPhoneNumber}
                  invalid={phoneInvalid}
                  setInvalid={setPhoneInvalid}
              />
            </Form.Group>
          </Row>
          <Row className="mb-3">
            <Form.Group as={Col} controlId="validationAddress">
              <Form.Label style={{ color: "#f0f0f0" }}>Address</Form.Label>
              <Container fluid style={{ padding: 0 }}>
                <Row>
                  <Col md={1} style={{ paddingTop: 8 }}>
                    <FontAwesomeIcon
                        icon={faAddressCard}
                        style={{
                          color: "#733BC0",
                          fontSize: "20px",
                          marginRight: "10px",
                        }}
                    />
                  </Col>
                  <Col md={11}>
                    <Form.Control
                        required
                        type="text"
                        placeholder="Enter your Address"
                        value={address}
                        onChange={handleAddressChange}
                        isInvalid={!isValidAddress(address)}
                    />
                  </Col>
                </Row>
              </Container>
            </Form.Group>
          </Row>
          <div
              className="mb-1"
              style={{ display: "flex", justifyContent: "center" }}
          >
            <Button
                type="submit"
                disabled={
                    !isValidName(name) ||
                    !isValidEmail(email) ||
                    !isValidPassword(password) ||
                    !isPasswordMatch(password, confirmPassword) ||
                    !isValidPhoneNumber(phoneNumber) ||
                    !isValidAddress(address)
                }
                style={{
                  width: "100%",
                  borderColor: "#733BC0",
                  backgroundColor: "#733BC0",
                  transition: "background-color 0.3s",
                }}
                onMouseEnter={(e) =>
                    (e.currentTarget.style.backgroundColor =
                        "rgba(115, 59, 192, 0.6)")
                }
                onMouseLeave={(e) =>
                    (e.currentTarget.style.backgroundColor = "#733BC0")
                }
            >
              Submit
            </Button>
          </div>
          <div
              style={{ width: "100%", display: "flex", justifyContent: "center" }}
          >
            <p style={{ paddingRight: "10px" }}>Have already an account? </p>
            <Link to="/signin">Sign In</Link>
          </div>
        </Form>
      </div>
  );
}