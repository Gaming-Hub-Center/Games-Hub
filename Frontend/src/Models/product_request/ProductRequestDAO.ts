export class ProductRequestDAO {
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
        public status: string,
        public requestType: string
        ){
    }

}