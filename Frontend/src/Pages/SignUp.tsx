import { SetStateAction, useState } from "react";
import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import { Link } from "react-router-dom";
import PhoneInput, { isValidPhoneNumber } from "react-phone-number-input";
import "react-phone-number-input/style.css";

export function SignUp() {
  const [validated, setValidated] = useState(false);
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [address, setAddress] = useState("");

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    const form = event.currentTarget;
    if (
      form.checkValidity() === false ||
      !isValidEmail(email) ||
      !isValidPassword(password) ||
      !isPasswordMatch(password, confirmPassword) ||
      !isValidPhoneNumber(phoneNumber) ||
      !isValidAddress(address)
    ) {
      event.preventDefault();
      event.stopPropagation();
    }

    setValidated(true);
  };

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

  const handlePhoneNumberChange = (phoneNumber: string) => {
    if (isValidPhoneNumber(phoneNumber)) {
      setPhoneNumber(phoneNumber);
    }
  };

  const handleAddressChange = (event: {
    target: { value: SetStateAction<string> };
  }) => {
    setAddress(event.target.value);
  };

  const isValidAddress = (address: string) => {
    // You can add your validation logic for the address here
    return address.length > 0;
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
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomName">
            <Form.Label>Username</Form.Label>
            <Form.Control
              required
              type="text"
              placeholder="Enter your Username"
              value={username}
              onChange={handleUsernameChange}
              isInvalid={!isValidUsername(username)}
            />
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomEmail">
            <Form.Label>Email</Form.Label>
            <Form.Control
              required
              type="text"
              placeholder="Enter your email"
              value={email}
              onChange={handleEmailChange}
              isInvalid={!isValidEmail(email)}
            />
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              required
              type="password"
              placeholder="Enter your password"
              value={password}
              onChange={handlePasswordChange}
              isInvalid={!isValidPassword(password)}
            />
            <Form.Control.Feedback type="invalid">
              Password must be at least 8 characters.
            </Form.Control.Feedback>
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomConfirmPassword">
            <Form.Label>Confirm Password</Form.Label>
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
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationPhone">
            <Form.Label>Phone number</Form.Label>
            <PhoneInput
              international
              defaultCountry="EG"
              placeholder="Enter your Phone number"
              value={phoneNumber}
              onChange={handlePhoneNumberChange}
            />
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomName">
            <Form.Label>Address</Form.Label>
            <Form.Control
              required
              type="text"
              placeholder="Enter your Address"
              value={address}
              onChange={handleAddressChange}
              isInvalid={!isValidAddress(address)}
            />
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
        <div style={{ display: "flex", justifyContent: "center" }}>
          <Link to="/SignIn" style={{ width: "100%" }}>
            <Button variant="secondary" style={{ width: "100%" }}>
              Sign In
            </Button>
          </Link>
        </div>
      </Form>
    </div>
  );
}
