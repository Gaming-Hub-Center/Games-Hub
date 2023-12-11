import axios from "axios";
import { getUserID } from "../../CurrentSession";
import { ProductDAO } from "../../Models/product/ProductDAO";
import { DigitalProductDAO } from "../../Models/product/DigitalProductDAO";
import { PhysicalProductDAO } from "../../Models/product/PhysicalProductDAO";
import { httpRequest } from "../HttpProxy";

const apiUrl = 'http://localhost:8080/';

async function GETRequest(api: string){
    try {
        const response = await axios.get(`${apiUrl}${api}`);
        console.log('Data received:', response.data);
        return response.data;
    } catch (error) {
        console.error('Error:', error.message);
        throw error;
    }
}

export async function getAllPhysicalProductsBySellerID(sellerId: string){
    return httpRequest('GET', `/product-request/${sellerId}/products/physical`);
}

export async function getAllDigitalProductsBySellerID(sellerId: string){
    return httpRequest('GET', `/product-request/${sellerId}/products/digital`);
}

export async function getAllPendingPhysicalProductsBySellerID(sellerId: string){
    return httpRequest('GET', `/product-request/${sellerId}/products/pending/physical`);
}

export async function getAllPendingDigitalProductsBySellerID(sellerId: string){
    return httpRequest('GET', `/product-request/${sellerId}/products/pending/digital`);
}

export function deleteProduct(sellerID: number, productType: string, productID: number, isPending: boolean){
    httpRequest('DELETE', `/product-request/${sellerID}/product/${isPending ? 'pending/' : ''}${productType}/${productID}`)
}