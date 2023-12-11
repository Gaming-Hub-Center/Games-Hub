import { ProductRequestDAO } from "./ProductRequestDAO"

export class DigitalProductDAO extends ProductRequestDAO{
    constructor(
        public id: number,
        public sellerId: number,
        public title: string,
        // public imageURLs: string[],
        public price: number,
        public description: string,
        public postDate: Date,
        public count: number,
        public productType: string,
        public category: string,
        public code: string,
        public status: string,
        public requestType: string
        ){
        super(id, sellerId, title/*, imageURLs*/, price, description, postDate, count, productType, category, status, requestType);
    }
}