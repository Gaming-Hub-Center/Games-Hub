export interface ProductRequestDTO {
    id?: number;
    dateReceived: string;
    status: string;
    requestType: string;
    title: string;
    price: number;
    description: string;
    postDate: string;
    count: number;
    sellerId: number;
    category: string;
    images: string[]; // Array of strings (assuming these are URLs)
}
