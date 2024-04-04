import {UserDTO} from "../Controller/DTO/user/UserDTO"
import {storeUserData} from "./CurrentSession"
import {httpRequest} from "../Controller/HttpProxy"

const updateUserData = () => {
  httpRequest("GET", "session/refresh/user")
    .then((response: any) => {
      const userDTO = response.data as UserDTO
      storeUserData(userDTO)
      console.log(userDTO)
    })
    .catch((error) => {
      console.log(error.response.data.message)
    })
}

const updateToken = () => {
  httpRequest("GET", "session/refresh/token")
    .then((response: any) => {
      const token = response.data as string
      sessionStorage.setItem("token", token)
      console.log(token)
    })
    .catch((error) => {
      console.log(error.response.data.message)
    })
}

const intervalIDs = []

const addIntervalId = (id: any) => {
  intervalIDs.push(id)
}

export const clearAllIntervals = () => {
  intervalIDs.forEach(clearInterval)
  intervalIDs.length = 0
}

export const updateSessionPeriodically = () => {
  updateUserData()
  updateToken()

  const intervalIdUserData = setInterval(updateUserData, 1000)
  addIntervalId(intervalIdUserData)

  const intervalIdToken = setInterval(updateToken, 1800000)
  addIntervalId(intervalIdToken)
}

