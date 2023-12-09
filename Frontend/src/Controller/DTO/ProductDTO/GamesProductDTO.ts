import { ProductDTO } from "./ProductDTO";

export type GamesProductDTO = ProductDTO & {
    serialCode: string;
};
