import { ProductRequestDAO } from "./ProductRequestDAO"

export class DigitalProductRequestDAO extends ProductRequestDAO{
    constructor(
        id: number,
        sellerId: number,
        title: string,
        // imageURLs: string[],
        price: number,
        description: string,
        postDate: number[],
        count: number,
        productType: string,
        category: string,
        public code: string,
        status: string,
        requestType: string
        ){
        super(id, sellerId, title/*, imageURLs*/, price, description, postDate, count, productType, category, status, requestType);
    }
}