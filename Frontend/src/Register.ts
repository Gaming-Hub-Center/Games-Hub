import axios from 'axios';

// Function to register a normal user
const registerUser = (data): Promise<any> => {
  try {
    const response = axios.post('/localhost:8080/registration/buyer', data);
    return response;
  } catch (error) {
    // Error handling logic
    throw error;
  }
};

// Function to register a seller
const registerSeller = async (data): Promise<any> => {
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
