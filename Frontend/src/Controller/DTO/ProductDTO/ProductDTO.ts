export type ProductDTO = {
  id: number;
  price: number;
  description: string;
  title: string;
  count: number;
  sellerID: number;
  created_date: Date;
  images: Blob[];
};
