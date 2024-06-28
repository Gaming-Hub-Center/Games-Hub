import {ProductPatchDTO} from "../DTO/ProductPatchDTO";
import {httpRequest} from "../HttpProxy";


export async function getAllPhysicalProductsBySellerID(sellerId: string) {
    return httpRequest('GET', `/seller/products/physical`);
}

export async function getAllDigitalProductsBySellerID(sellerId: string) {
    return httpRequest('GET', `/seller/products/digital`);
}

export async function getAllPendingPhysicalProductsBySellerID(sellerId: string) {
    return httpRequest('GET', `/seller/products/pending/physical`);
}

export async function getAllPendingDigitalProductsBySellerID(sellerId: string) {
    return httpRequest('GET', `/seller/products/pending/digital`);
}

export async function getCatalogDigtalProductBySellerId(sellerId: string, productId: string) {
    return httpRequest('GET', `/seller/product/digital/${productId}`)
}

export async function getCatalogPhysicalProductBySellerId(sellerId: string, productId: string) {
    return httpRequest('GET', `/seller/product/physical/${productId}`)
}

export async function getPhysicalDigtalProductBySellerId(sellerId: string, productId: string) {
    return httpRequest('GET', `/seller/product/pending/digital/${productId}`)
}

export async function getPhysicalPhysicalProductBySellerId(sellerId: string, productId: string) {
    return httpRequest('GET', `/seller/product/pending/physical/${productId}`)
}

export async function getProduct(sellerId: string, productType: string, productId: string, isPending: boolean) {
    return httpRequest('GET', `/seller/product/${isPending ? 'pending/' : ''}${productType}/${productId}`)
}

export function deleteProduct(sellerID: string, productType: string, productID: string, isPending: boolean) {
    httpRequest('DELETE', `/seller/product/${isPending ? 'pending/' : ''}${productType}/${productID}`)
}

export function updateProduct(sellerID: string, productType: string, productID: string, isPending: boolean, patch: ProductPatchDTO) {
    httpRequest('PATCH', `/seller/product/${isPending ? 'pending/' : ''}${productType}/${productID}`, patch)
}