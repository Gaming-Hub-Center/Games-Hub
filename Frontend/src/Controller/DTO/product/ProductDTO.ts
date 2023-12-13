export interface ProductDTO {
    id: number;
    price: number;
    description: string;
    title: string;
    count: number;
    sellerID: number;
    created_date: string;
    category: string;
    images: string[];
}