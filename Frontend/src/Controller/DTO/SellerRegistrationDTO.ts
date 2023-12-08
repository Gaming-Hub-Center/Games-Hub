import {UserRegistrationDTO} from "./UserRegistrationDTO";

export type SellerRegistrationDTO = UserRegistrationDTO & {
    description: string
    nationalID: string
    vatRegistrationNumber: string
}
