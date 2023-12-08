import { SetStateAction, useState } from "react";
import { Form, Button, Col, Row, Container } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { isValidPhoneNumber } from "react-phone-number-input";
import "react-phone-number-input/style.css";
import PhoneNumberInput from "../Components/PhoneNumberInputC";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faEnvelope,
  faLock,
  faUser,
  faAddressCard,
  faPhone,
} from "@fortawesome/free-solid-svg-icons";
import { SignUpNavbar } from "../Components/SignUpNavbar";
import { httpRequest } from "../Controller/HttpProxy";
import { UserDTO } from "../Controller/DTO/UserDTO";
import {clearCurrentSession, setJwtToken} from "../CurrentSession";
import {BuyerRegistrationDTO} from "../Controller/DTO/BuyerRegistrationDTO";

export function SignUp() {
  const [validated, setValidated] = useState(false);
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [phoneInvalid, setPhoneInvalid] = useState(false); // State for phone number validation
  const [address, setAddress] = useState("");
  const navigate = useNavigate()

  const isValidUsername = (username: string) => {
    return /^[a-zA-Z][a-zA-Z0-9]*$/.test(username);
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

  const handleUsernameChange = (event: {
    target: { value: SetStateAction<string> };
  }) => {
    setUsername(event.target.value);
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
    if (
      form.checkValidity() === false ||
      !isValidEmail(email) ||
      !isValidPassword(password) ||
      !isPasswordMatch(password, confirmPassword) ||
      !isValidPhoneNumber(phoneNumber) ||
      !isValidAddress(address)
    ) {
      event.stopPropagation();
    }

    event.preventDefault();

    const buyerRegistrationDTO: BuyerRegistrationDTO = {
      name: username,
      email: email,
      password: password,
      phone: phoneNumber,
      address: address,
    }

    clearCurrentSession()

    httpRequest('POST', 'registration/buyer', buyerRegistrationDTO)
    .then((response) => {
      const responseData = response.data as UserDTO
      setJwtToken(responseData.token)
      setValidated(true)
      navigate('/welcome')
      console.log(responseData)
    })
    .catch((error) => {
      console.log(error)
      alert("The email address you have entered is already associated with an account. Please use a different email address or sign in to your existing account.")
    })
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
      }}
    >
      <Form
        style={{
          width: "30%",
          backgroundColor: "#f8f9fa",
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
            <Form.Label>Username</Form.Label>
            <Container fluid style={{ padding: 0 }}>
              <Row>
                <Col md={1} style={{ paddingTop: 8 }}>
                  <FontAwesomeIcon
                    icon={faUser}
                    style={{
                      color: "black",
                      fontSize: "20px",
                      marginRight: "10px",
                    }}
                  />
                </Col>
                <Col md={11}>
                  <Form.Control
                    required
                    type="text"
                    placeholder="Enter your Username"
                    value={username}
                    onChange={handleUsernameChange}
                    isInvalid={!isValidUsername(username)}
                  />
                </Col>
              </Row>
            </Container>
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomEmail">
            <Form.Label>Email</Form.Label>
            <Container fluid style={{ padding: 0 }}>
              <Row>
                <Col md={1} style={{ paddingTop: 8 }}>
                  <FontAwesomeIcon
                    icon={faEnvelope}
                    style={{
                      color: "black",
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
            <Form.Label>Password</Form.Label>
            <Container fluid style={{ padding: 0 }}>
              <Row>
                <Col md={1} style={{ paddingTop: 8 }}>
                  <FontAwesomeIcon
                    icon={faLock}
                    style={{
                      color: "black",
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
            <Form.Control.Feedback type="invalid">
              Password must be at least 8 characters.
            </Form.Control.Feedback>
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomConfirmPassword">
            <Form.Label>Confirm Password</Form.Label>
            <Container fluid style={{ padding: 0 }}>
              <Row>
                <Col md={1} style={{ paddingTop: 8 }}>
                  <FontAwesomeIcon
                    icon={faLock}
                    style={{
                      color: "black",
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
                  <Form.Control.Feedback type="invalid">
                    Passwords should match.
                  </Form.Control.Feedback>
                </Col>
              </Row>
            </Container>
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationPhone">
            <Form.Label>
              <FontAwesomeIcon
                icon={faPhone}
                style={{
                  color: "black",
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
            <Form.Label>Address</Form.Label>
            <Container fluid style={{ padding: 0 }}>
              <Row>
                <Col md={1} style={{ paddingTop: 8 }}>
                  <FontAwesomeIcon
                    icon={faAddressCard}
                    style={{
                      color: "black",
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
              !isValidEmail(email) ||
              !isValidPassword(password) ||
              !isPasswordMatch(password, confirmPassword) ||
              !isValidPhoneNumber(phoneNumber) ||
              !isValidAddress(address)
            }
            style={{ width: "100%" }}
          >
            Submit
          </Button>
        </div>
        <div
          style={{ width: "100%", display: "flex", justifyContent: "center" }}
        >
          <p style={{ paddingRight: "10px" }}>Have already an account? </p>
          <Link to="/SignIn">Sign In</Link>
        </div>
      </Form>
    </div>
  );
}
