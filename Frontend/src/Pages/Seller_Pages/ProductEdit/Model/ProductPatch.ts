import { ProductPatchDTO } from "../../../../Controller/DTO/ProductPatchDTO"

export class ProductPatch{
    constructor(
        public title: string,
        public description: string
    ){}

    getType(): ProductPatchDTO{
        const type: ProductPatchDTO = {
            title: this.title,
            description: this.description
        }
        return type
    }
}