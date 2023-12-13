import React, {useEffect, useState} from "react";
import {httpRequest} from "../../Controller/HttpProxy";
import {PhysicalProductDTO} from "../../Controller/DTO/product/PhysicalProductDTO";
import {DigitalProductDTO} from "../../Controller/DTO/product/DigitalProductDTO";
import {ProductBriefDTO} from "../../Controller/DTO/product/ProductBriefDTO";

const ViewProduct: () => void = () => {

    // const productId = 3;
    // const productType = 'physical';
    //const [productDetails, setProductDetails] = useState(null);
    let physicalProductDTO: PhysicalProductDTO = null;
    let digitalProductDTO: DigitalProductDTO = null;
    let briefDTOs: ProductBriefDTO[] = null

    const getProductDetails = async (productId: number, productType: string) => {
        try {
            const response = await httpRequest("GET", `/product/${productType}/getdetails?ID=${productId}`)
            // setProductDetails(response.data);
            physicalProductDTO = response.data as PhysicalProductDTO
            console.log(physicalProductDTO.count)
            console.log(physicalProductDTO.category)
        } catch (error) {
            console.error("Error fetching product details:", error);
        }
    };
    const searchProducts = async (key: string, productType: string) => {
        try {
            const response = await httpRequest("GET", `/product/${productType}/search?key=${key}`)
            briefDTOs = response.data as ProductBriefDTO[]
            console.log(briefDTOs.length)
            console.log(briefDTOs[0]?.price)
        } catch (error) {
            console.error(`Error searching product with key ${key}:`, error);
        }
    };

    const filterProducts = async (category: string, lowerBound: number, upperBound: number, productType: string) => {
        let url = `/product/${productType}/filter?`
        if(category != null) url += `category=${category}&`
        if(lowerBound != null) url += `lowerBound=${lowerBound}&`
        if(upperBound != null) url += `upperBound=${upperBound}`
        try {
            const response = await httpRequest("GET", url)
            briefDTOs = response.data as ProductBriefDTO[]
            console.log(briefDTOs.length)
            console.log(briefDTOs[0]?.price)
        } catch (error) {
            console.error(`Error filtering product with category ${category}, lowerBound ${lowerBound}. upperBound ${upperBound}:`, error);
        }
    };

    const sortProducts = async (ascending: boolean, productType: string) => {
        const url = `/product/${productType}/sort?ascending=${ascending}`;
        try {
            const response = await httpRequest("GET", url)
            briefDTOs = response.data as ProductBriefDTO[]
            console.log(briefDTOs.length)
            console.log(briefDTOs[1]?.price)
        } catch (error) {
            console.error(`Error sorting product`, error);
        }
    };


    useEffect(() => {
        // getProductDetails(3, "physical");
        // getProductDetails(3, "digital")

        // filterProducts("laptop", null, null,"digital")

        // searchProducts("h", "physical")
        sortProducts(true, "physical")

    }, [3, 3]);


    // return (
    //     <div>
    //         {physicalProductDTO ? (
    //             <div>
    //                 <h2>{physicalProductDTO.title}</h2>
    //                 <p>{physicalProductDTO.description}</p>
    //                 <div>
    //                     {physicalProductDTO.images.map((image, index) => (
    //                         <img key={index}
    //                              src={`data:image/jpeg;base64,${image}`}
    //                              alt={`Product Image ${index + 1}`}
    //                         />
    //                     ))}
    //                 </div>
    //                 {/* Display other product details as needed */}
    //             </div>
    //         ) : (
    //             <p>Loading...</p>
    //         )}
    //     </div>
    // );


};

export default ViewProduct;