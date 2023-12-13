import { ProductDAO } from "../product/ProductDAO"

export class ProductRequestDAO extends ProductDAO{
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
        public status: string,
        public requestType: string
        ){
            super(id, sellerId, title, price, description, postDate, count, productType, category)
    }

}