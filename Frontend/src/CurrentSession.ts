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

export const getRole = () => {
    return window.sessionStorage.getItem("role")
}

export const getToken = () => {
    return window.sessionStorage.getItem("token")
}

function setSessionItem(key: string, value: any) {
    if (value)
        window.sessionStorage.setItem(key, value.toString());
}

export const storeUserData = (userDTO: UserDTO) => {
    setSessionItem("id", userDTO.id);
    setSessionItem("name", userDTO.name);
    setSessionItem("email", userDTO.email);
    setSessionItem("phone", userDTO.phone);
    setSessionItem("address", userDTO.address);
    setSessionItem("balance", userDTO.balance);
    setSessionItem("nationalID", userDTO.nationalID);
    setSessionItem("dateJoined", userDTO.dateJoined);
    setSessionItem("sellerDescription", userDTO.sellerDescription);
    setSessionItem("vatRegistrationNumber", userDTO.vatRegistrationNumber);
    setSessionItem("role", userDTO.role);
    setSessionItem("token", userDTO.token);
}

export const setCurrentProductPage = (currentPage: string) => {
    setSessionItem("currentPage", currentPage)
}

export const getCurrentProductPage = () => {
    return window.sessionStorage.getItem("currentPage")
}

export const clearCurrentSession = () => {
  window.sessionStorage.clear();
};

export const noCurrentSession = () => {
    return getToken() === null || getToken() === "null";
};