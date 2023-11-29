import { faEnvelope, faLock } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { SetStateAction, useState } from "react";
import { Container } from "react-bootstrap";
import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import { Link } from "react-router-dom";

export function SignIn() {
  const [validated, setValidated] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    const form = event.currentTarget;
    if (
      form.checkValidity() === false ||
      !isValidEmail(email) ||
      !isValidPassword(password)
    ) {
      event.preventDefault();
      event.stopPropagation();
    }
    // aw hna grb enta
    setValidated(true);
    // rabt hna
  };

  const isValidEmail = (email: string) => {
    return /^[^\s@]+@[^\s@]+\.[c][o][m]$/.test(email);
  };

  const isValidPassword = (password: string | any[]) => {
    return password.length >= 8;
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
        <h2 style={{ textAlign: "center", marginBottom: "20px" }}>Sign In</h2>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomEmail">
            <Form.Label>Email address</Form.Label>
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
          </Form.Group>
        </Row>
        <div
          className="mb-1"
          style={{ display: "flex", justifyContent: "center" }}
        >
          <Button
            type="submit"
            disabled={!isValidEmail(email) || !isValidPassword(password)}
            style={{ width: "100%" }}
          >
            Submit
          </Button>
        </div>
        <div style={{ display: "flex", justifyContent: "center" }}>
          <p style={{ paddingRight: "10px" }}>Don't have an account? </p>
          <Link to="/SignUp">Sign Up</Link>
        </div>
      </Form>
    </div>
  );
}
