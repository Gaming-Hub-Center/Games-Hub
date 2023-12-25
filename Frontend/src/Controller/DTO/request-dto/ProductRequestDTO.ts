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
}
