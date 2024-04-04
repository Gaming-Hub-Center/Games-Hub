<<<<<<< Updated upstream
import axios from "axios";
import { getJwtToken } from "../CurrentSession";
=======
import axios from "axios"
import {getToken} from "../session/CurrentSession"
>>>>>>> Stashed changes


axios.defaults.baseURL = "http://localhost:8080"
axios.defaults.headers.post["Content-Type"] = "application/json"

<<<<<<< Updated upstream

export const httpRequest = (method: string, url: string, data?: any) => {
  let headers = {};

  if (getJwtToken() !== null && getJwtToken() !== "null")
    headers = { Authorization: `Bearer ${getJwtToken()}` };
=======
export const httpRequest = async (method: string, url: string, data?: any, params?: any) => {
  let headers = {}

  if (getToken() !== null && getToken() !== "null")
    headers = { Authorization: `Bearer ${getToken()}` }
>>>>>>> Stashed changes

  return axios({
    method: method,
    headers: headers,
    url: url,
    data: data,
<<<<<<< Updated upstream
  });
};
=======
    params: params,
  })
}
>>>>>>> Stashed changes
