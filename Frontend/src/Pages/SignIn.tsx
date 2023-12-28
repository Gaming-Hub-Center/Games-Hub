import { faGoogle } from "@fortawesome/free-brands-svg-icons";
import { faEnvelope, faLock } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { SetStateAction, useState } from "react";
import { Container } from "react-bootstrap";
import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import {Link, useNavigate} from "react-router-dom";
import { UserSignInDTO } from "../Controller/DTO/user/UserSignInDTO";
import { UserDTO } from "../Controller/DTO/user/UserDTO";
import { httpRequest } from "../Controller/HttpProxy";
import { clearCurrentSession, storeUserData } from "../CurrentSession";
import { GoogleLoginButton } from "../Components/googleAuthButtons/googleLogin";

export function SignIn() {
    const [validated, setValidated] = useState(false);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.stopPropagation();
        }

        event.preventDefault();

        const userSignInDTO: UserSignInDTO = {
            email: email,
            password: password,
        };

        clearCurrentSession();

        httpRequest('POST', 'auth/signin', userSignInDTO)
            .then((response) => {
                const responseData = response.data as UserDTO
                storeUserData(responseData)
                setValidated(true)
                if (responseData.role === 'ADMIN')
                  navigate('/admin/dashboard')
                else if (responseData.role === 'SELLER')
                  navigate('/seller/catalog')
                else
                  navigate('/')
                console.log(responseData)
            })
            .catch((error) => {
                console.log(error)
                alert("The email or password you entered is incorrect. Please re-check your credentials and try again.")
            })
    };

    const isValidEmail = (email: string) => {
        return /^[^\s@]+@[^\s@]+\.com$/.test(email);
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
                backgroundImage: `url("/src/data/back2.jpg")`, // Replace with the path to your background image
                backgroundSize: "cover",
                backgroundPosition: "center",
            }}
        >
            <Form
                style={{
                    width: "30%",
                    backgroundColor: "#121212",
                    padding: "20px",
                    borderRadius: "8px",
                    boxShadow: "0 0 10px rgba(0,0,0,0.1)",
                    color: "#f0f0f0",
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
                        <Form.Label>Password</Form.Label>
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
                    </Form.Group>
                </Row>
                <div
                    className="mb-1"
                    style={{ display: "flex", justifyContent: "center" }}
                >
                    <Button
                        type="submit"
                        disabled={!isValidEmail(email) || !isValidPassword(password)}
                        style={{
                            width: "100%",
                            backgroundColor: "#733BC0",
                            borderColor: "#733BC0",
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
                <GoogleLoginButton />
                
                <div style={{ display: "flex", justifyContent: "center" }}>
                    <p style={{ paddingRight: "10px" }}>Don't have an account? </p>
                    <Link to="/signup/buyer">Sign Up</Link>
                </div>
            </Form>
        </div>
    );
}
