import { ProductRequestDAO } from "./ProductRequestDAO"

export class DigitalProductRequestDAO extends ProductRequestDAO{
    constructor(
        id: number,
        seller: any,
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
        super(id, seller, title/*, imageURLs*/, price, description, postDate, count, productType, category, status, requestType);
    }
}