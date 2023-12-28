import axios from "axios";
import {getToken, storeUserData} from "../CurrentSession";
import {UserDTO} from "./DTO/user/UserDTO";


axios.defaults.baseURL = "http://localhost:8080";
axios.defaults.headers.post["Content-Type"] = "application/json";

let lastUpdateTimestamp = 0;

const updateUserData = async () => {
  const currentTime = Date.now();
  if (currentTime - lastUpdateTimestamp < 15000)
    return;

  lastUpdateTimestamp = Date.now()

  try {
    const response = await sendHttpRequest('GET', 'auth/user');
    const responseData = response.data as UserDTO;
    console.log('User data updated:', responseData)
    storeUserData(responseData);
  } catch (error) {
    console.error('Error updating user data:', error);
  }
};

export const httpRequest = async (method: string, url: string, data?: any) => {
  const jwtToken = getToken();
  if (jwtToken && jwtToken !== "null") {
    await updateUserData();
  }

  return sendHttpRequest(method, url, data);
};

export const sendHttpRequest = (method: string, url: string, data?: any) => {
  let headers = {};

  if (getToken() !== null && getToken() !== "null")
    headers = { Authorization: `Bearer ${getToken()}` };

  return axios({
    method: method,
    headers: headers,
    url: url,
    data: data,
  });
};
