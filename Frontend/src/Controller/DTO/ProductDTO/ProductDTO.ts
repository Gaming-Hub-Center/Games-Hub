export interface ProductDTO {
  id: number;
  dateReceived: string; // Change to match LocalDate format in Java (e.g., 'yyyy-MM-dd')
  status: string;
  requestType: string;
  title: string;
  price: number;
  description: string;
  postDate: string; // Change to match LocalDate format in Java (e.g., 'yyyy-MM-dd')
  count: number;
  sellerId: number;
  category: string;
  urls: string[];
  code: string;
}