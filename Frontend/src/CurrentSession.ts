import { UserDTO } from "./Controller/DTO/user/UserDTO"

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
