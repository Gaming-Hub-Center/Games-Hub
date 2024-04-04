import axios from "axios"
import {getToken} from "../session/CurrentSession"


axios.defaults.baseURL = "http://localhost:8080"
axios.defaults.headers.post["Content-Type"] = "application/json"

export const httpRequest = async (method: string, url: string, data?: any, params?: any) => {
  let headers = {}

  if (getToken() !== null && getToken() !== "null")
    headers = { Authorization: `Bearer ${getToken()}` }

  return axios({
    method: method,
    headers: headers,
    url: url,
    data: data,
    params: params,
  })
}
