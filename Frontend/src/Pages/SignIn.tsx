import { SetStateAction, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';

export function SignIn() {
  const [validated, setValidated] = useState(false);
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (event: { currentTarget: any; preventDefault: () => void; stopPropagation: () => void; }) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false || !isValidEmail(email) || !isValidPassword(password)) {
      event.preventDefault();
      event.stopPropagation();
    }

    setValidated(true);
  };

  const isValidEmail = (email: string) => {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  };

  const isValidPassword = (password: string | any[]) => {
    return password.length >= 8;
  };

  const handleEmailChange = (event: { target: { value: SetStateAction<string>; }; }) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event: { target: { value: SetStateAction<string>; }; }) => {
    setPassword(event.target.value);
  };

  return (
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
      <Form style={{ width: '30%', backgroundColor: '#f8f9fa', padding: '20px', borderRadius: '8px', boxShadow: '0 0 10px rgba(0,0,0,0.1)' }} noValidate validated={validated} onSubmit={handleSubmit}>
        <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>Sign In</h2>
        <Row className="mb-3">
          <Form.Group as={Col}>
            <Form.Label>Email</Form.Label>
            <Form.Control
              required
              type="text"
              placeholder="Enter your email"
              value={email}
              onChange={handleEmailChange}
            />
            <Form.Control.Feedback type="invalid">
              Please enter a valid email address.
            </Form.Control.Feedback>
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col}>
            <Form.Label>Password</Form.Label>
            <Form.Control
              required
              type="password"
              placeholder="Enter your password"
              value={password}
              onChange={handlePasswordChange}
            />
            <Form.Control.Feedback type="invalid">
              Password must be at least 8 characters.
            </Form.Control.Feedback>
          </Form.Group>
        </Row>
        <Form.Group className="mb-3">
          <Form.Check
            required
            label="Agree to terms and conditions"
            feedback="You must agree before submitting."
            feedbackType="invalid"
          />
        </Form.Group>
        <div style={{ display: 'flex', justifyContent: 'center' }}>
          <Button type="submit">Submit</Button>
        </div>
      </Form>
    </div>
  );
}
