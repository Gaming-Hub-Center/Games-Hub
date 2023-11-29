// src/services/AuthService.ts
import axios from 'axios';

interface LoginCredentials {
  email: string;
  password: string;
}

const login = async (credentials: LoginCredentials) => {
  try {
    const response = await axios.post('/localhost:8080/auth/signin', credentials);
    // You might want to handle token storage or other actions here
    return response.data;
  } catch (error) {
    // Error handling logic
    throw error;
  }
};

export const AuthService = {
  login,
};
