import React, { useState } from 'react';
import { Form, Button, Col, Row } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import PhoneNumberInput from './PhoneNumberInput'; // Import the PhoneNumberInput component

export function SignUp() {
  const [validated, setValidated] = useState(false);
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [phoneInvalid, setPhoneInvalid] = useState(false); // State for phone number validation
  const [address, setAddress] = useState("");

  // ... isValidUsername, isValidEmail, isValidPassword, isPasswordMatch, handleUsernameChange, handleEmailChange, handlePasswordChange, handleConfirmPasswordChange, handleAddressChange

  const isValidAddress = (address: string) => {
    return address.length > 0;
  };

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

  return (
    <div style={{ display: "flex", justifyContent: "center", alignItems: "center", height: "100vh" }}>
      <Form style={{ width: "30%", backgroundColor: "#f8f9fa", padding: "20px", borderRadius: "8px", boxShadow: "0 0 10px rgba(0,0,0,0.1)" }} noValidate validated={validated} onSubmit={handleSubmit}>
        <h2 style={{ textAlign: "center", marginBottom: "20px" }}>Sign Up</h2>
        {/* Username, Email, Password, Confirm Password Fields */}
        {/* ... */}
        
        {/* Phone Number Input */}
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationPhone">
            <Form.Label>Phone number</Form.Label>
            <PhoneNumberInput
              value={phoneNumber}
              onChange={setPhoneNumber}
              invalid={phoneInvalid}
              setInvalid={setPhoneInvalid}
            />
          </Form.Group>
        </Row>

        {/* Address Input */}
        {/* ... */}
        
        {/* Submit and Sign In Buttons */}
        {/* ... */}
      </Form>
    </div>
  );
}
