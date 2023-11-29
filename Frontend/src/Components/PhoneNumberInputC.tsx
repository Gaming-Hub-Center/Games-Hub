import React from "react";
import PhoneInput from "react-phone-number-input";
import "react-phone-number-input/style.css";
import { isValidPhoneNumber } from "react-phone-number-input";

interface PhoneNumberInputProps {
  value: string;
  onChange: (value: string) => void;
  invalid: boolean;
  setInvalid: (value: boolean) => void;
}

const PhoneNumberInput: React.FC<PhoneNumberInputProps> = ({
  value,
  onChange,
  invalid,
  setInvalid,
}) => {
  const handlePhoneNumberChange = (phoneNumber: string) => {
    onChange(phoneNumber);
    setInvalid(!isValidPhoneNumber(phoneNumber));
  };

  return (
    <PhoneInput
      international
      defaultCountry="PS"
      placeholder="Enter your Phone number"
      value={value}
      onChange={handlePhoneNumberChange}
      className={invalid ? "is-invalid" : ""}
    />
  );
};

export default PhoneNumberInput;
