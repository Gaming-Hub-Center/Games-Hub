// src/services/AuthService.ts
import axios from 'axios';

// Define interfaces for the user and seller registration data
export interface UserRegistrationData {
  userName: string;
  email: string;
  password: string;
  imageID: string;
  phone: string;
  address: string;
}

export interface SellerRegistrationData extends UserRegistrationData {
  description: string;
  nationalID: string;
  vatRegistrationNumber: string;
}

// Function to register a normal user
const registerUser = async (data: UserRegistrationData): Promise<any> => {
  try {
    const response = await axios.post('/localhost:8080/registration/buyer', data);
    return response.data;
  } catch (error) {
    // Error handling logic
    throw error;
  }
};

// Function to register a seller
const registerSeller = async (data: SellerRegistrationData): Promise<any> => {
  try {
    const response = await axios.post('//localhost:8080/registration/seller', data);
    return response.data;
  } catch (error) {
    // Error handling logic
    throw error;
  }
};

export const AuthService = {
  // ...existing methods
  registerUser,
  registerSeller,
};
