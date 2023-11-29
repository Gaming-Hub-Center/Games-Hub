import React from 'react';
import PhoneInput from 'react-phone-number-input';
import 'react-phone-number-input/style.css';
import { isValidPhoneNumber } from 'react-phone-number-input';

interface PhoneNumberInputCProps {
  value: string;
  onChange: (value: string) => void;
  invalid: boolean;
  setInvalid: (value: boolean) => void;
}

const PhoneNumberInputC: React.FC<PhoneNumberInputCProps> = ({ value, onChange, invalid, setInvalid }) => {
  const handlePhoneNumberChange = (phoneNumber: string) => {
    onChange(phoneNumber);
    setInvalid(!isValidPhoneNumber(phoneNumber));
  };

  return (
    <PhoneInput
      international
      defaultCountry="US"
      placeholder="Enter your Phone number"
      value={value}
      onChange={handlePhoneNumberChange}
      className={invalid ? 'is-invalid' : ''}
    />
  );
};