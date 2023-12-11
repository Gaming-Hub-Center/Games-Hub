import { ProductDAO } from "./ProductDAO"

export class DigitalProductDAO extends ProductDAO{
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
        public code: string
        ){
        super(id, sellerId, title/*, imageURLs*/, price, description, postDate, count, productType, category);
    }
}