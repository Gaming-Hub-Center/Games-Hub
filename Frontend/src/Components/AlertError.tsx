import { useState } from 'react';
import Alert from 'react-bootstrap/Alert';
import Button from 'react-bootstrap/Button';

function AlertError() {
  const [show, setShow] = useState(true);

  if (show) {
    return (
      <Alert variant="danger" onClose={() => setShow(false)} dismissible>
        <Alert.Heading>You got an error!</Alert.Heading>
        <p>
          Encountered problem on submitting your request. Please try again.
        </p>
      </Alert>
    );
  }
  // return <Button onClick={() => setShow(true)}>Show Alert</Button>;
}

export default AlertError;