import { useState } from 'react';
import Alert from 'react-bootstrap/Alert';
import Button from 'react-bootstrap/Button';

function AlertAleadyExists() {
  const [show, setShow] = useState(true);

  if (show) {
    return (
      <Alert variant="danger" onClose={() => setShow(false)} dismissible>
        <Alert.Heading>You got an error!</Alert.Heading>
        <p>
          The Product Already Exist.
        </p>
      </Alert>
    );
  }
  // return <Button onClick={() => setShow(true)}>Show Alert</Button>;
}

export default AlertAleadyExists;