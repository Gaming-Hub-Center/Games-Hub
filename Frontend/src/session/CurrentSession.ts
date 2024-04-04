import {UserDTO} from "../Controller/DTO/user/UserDTO"

const setSessionItem = (key: string, value: any) => {
    if (value)
        window.sessionStorage.setItem(key, value.toString())
}

export const setCurrentProductPage = (currentPage: string) => {
    setSessionItem("currentPage", currentPage)
}

export const getCurrentProductPage = () => {
    return window.sessionStorage.getItem("currentPage")
}

export const getId = () => {
    return parseInt(window.sessionStorage.getItem("id"))
}

export const getName = () => {
    return window.sessionStorage.getItem("name")
}

export const getEmail = () => {
    return window.sessionStorage.getItem("email")
}

export const getPhone = () => {
    return window.sessionStorage.getItem("phone")
}

export const getAddress = () => {
    return window.sessionStorage.getItem("address")
}

export const getBalance = () => {
    return parseFloat(window.sessionStorage.getItem("balance"))
}

export const getNationalID = () => {
    return window.sessionStorage.getItem("nationalID")

}

export const getDateJoined = () => {
    return window.sessionStorage.getItem("dateJoined")
}

export const getSellerDescription = () => {
    return window.sessionStorage.getItem("sellerDescription")
}

export const getVatRegistrationNumber = () => {
    return window.sessionStorage.getItem("vatRegistrationNumber")
}

<<<<<<< Updated upstream:Frontend/src/CurrentSession.ts
export const getJwtToken = () => {
    return window.sessionStorage.getItem("jwtToken")
}

export const storeUserData = (userDTO: UserDTO) => {
    window.sessionStorage.setItem("id", userDTO.id.toString())
    window.sessionStorage.setItem("name", userDTO.name)
    window.sessionStorage.setItem("email", userDTO.email)
    window.sessionStorage.setItem("phone", userDTO.phone)
    window.sessionStorage.setItem("address", userDTO.address)
    window.sessionStorage.setItem("balance", userDTO.balance.toString())
    window.sessionStorage.setItem("nationalID", userDTO.nationalID)
    window.sessionStorage.setItem("dateJoined", userDTO.dateJoined)
    window.sessionStorage.setItem("sellerDescription", userDTO.sellerDescription)
    window.sessionStorage.setItem("vatRegistrationNumber", userDTO.vatRegistrationNumber)
    window.sessionStorage.setItem("jwtToken", userDTO.token)
}

export const clearCurrentSession = () => {
  window.sessionStorage.clear();
};
=======
export const setRole = (role: string) => {
    setSessionItem("role", role)
}

export const getRole = () => {
    return window.sessionStorage.getItem("role")
}

export const setToken = (token: string) => {
    setSessionItem("token", token)
}

export const getToken = () => {
    return window.sessionStorage.getItem("token")
}

export const storeUserData = (userDTO: UserDTO) => {
    setSessionItem("id", userDTO.id)
    setSessionItem("name", userDTO.name)
    setSessionItem("email", userDTO.email)
    setSessionItem("phone", userDTO.phone)
    setSessionItem("address", userDTO.address)
    setSessionItem("balance", userDTO.balance)
    setSessionItem("nationalID", userDTO.nationalID)
    setSessionItem("dateJoined", userDTO.dateJoined)
    setSessionItem("sellerDescription", userDTO.sellerDescription)
    setSessionItem("vatRegistrationNumber", userDTO.vatRegistrationNumber)
}

export const noCurrentSession = () => {
    return getToken() === null || getToken() === "null"
}

export const clearCurrentSession = () => {
  window.sessionStorage.clear()
}
>>>>>>> Stashed changes:Frontend/src/session/CurrentSession.ts
