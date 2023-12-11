import { ProductDAO } from "./ProductDAO"

export class DigitalProductDAO extends ProductDAO{
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
        public code: string
        ){
        super(id, sellerId, title/*, imageURLs*/, price, description, postDate, count, productType, category);
    }
}