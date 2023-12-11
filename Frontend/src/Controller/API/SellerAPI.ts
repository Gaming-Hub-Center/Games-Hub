import axios from "axios";
import { getUserID } from "../../CurrentSession";
import { ProductDAO } from "../../Models/product/ProductDAO";
import { DigitalProductDAO } from "../../Models/product/DigitalProductDAO";
import { PhysicalProductDAO } from "../../Models/product/PhysicalProductDAO";
import { httpRequest } from "../HttpProxy";
import { ProductPatchDTO } from "../DTO/ProductPatchDTO";

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

export async function getCatalogDigtalProductBySellerId(sellerId: string, productId: string){
    return httpRequest('GET', `/product-request/${sellerId}/product/digital/${productId}`)
}

export async function getCatalogPhysicalProductBySellerId(sellerId: string, productId: string){
    return httpRequest('GET', `/product-request/${sellerId}/product/physical/${productId}`)
}

export async function getPhysicalDigtalProductBySellerId(sellerId: string, productId: string){
    return httpRequest('GET', `/product-request/${sellerId}/product/pending/digital/${productId}`)
}

export async function getPhysicalPhysicalProductBySellerId(sellerId: string, productId: string){
    return httpRequest('GET', `/product-request/${sellerId}/product/pending/physical/${productId}`)
}

export async function getProduct(sellerId: string, productType: string, productId: string, isPending: boolean){
    return httpRequest('GET', `/product-request/${sellerId}/product/${isPending? 'pending/' : ''}${productType}/${productId}`)
}

export function deleteProduct(sellerID: string, productType: string, productID: string, isPending: boolean){
    httpRequest('DELETE', `/product-request/${sellerID}/product/${isPending ? 'pending/' : ''}${productType}/${productID}`)
}

export function updateProduct(sellerID: string, productType: string, productID: string, isPending: boolean, patch: ProductPatchDTO){
    httpRequest('PATCH', `/product-request/${sellerID}/product/${isPending ? 'pending/' : ''}${productType}/${productID}`, patch)
}