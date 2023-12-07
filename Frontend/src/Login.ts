import axios from 'axios';

export interface LoginCredentials {
  email: string;
  password: string;
}

const login = (credentials: LoginCredentials) => {
  try {
    const response = axios.post('http://localhost:8080/auth/signin', credentials);
    return response; // Return the response data
  } catch (error) {
    throw error;
  }
};

export const AuthService = {
  login,
};
