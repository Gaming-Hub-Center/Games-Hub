import { ProductPatchDTO } from "../DTO/ProductPatchDTO";
import { httpRequest } from "../HttpProxy";


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