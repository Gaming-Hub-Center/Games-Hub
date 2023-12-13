export class ProductDAO {
    constructor(
        public id: number,
        public sellerId: number,
        public title: string,
        // public imageURLs: string[],
        public price: number,
        public description: string,
        public postDate: number[],
        public count: number,
        public productType: string,
        public category: string
        ){
    }

}