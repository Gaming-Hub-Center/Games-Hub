package com.gameshub.controller;

import com.gameshub.controller.DTO.*;
import com.gameshub.model.product.*;
import com.gameshub.service.*;
import com.gameshub.utils.ProductMapper;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/physical/getdetails")
    public ResponseEntity<PhysicalProductDTO> getPhysicalProductByID(@RequestParam int ID) {
        PhysicalProductDTO productDTO = productService.getPhysicalByID(ID);
        return ResponseEntity.ok(productDTO);
    }

    /*@GetMapping("/physical/search")
    public ResponseEntity<List<PhysicalProductDTO>> searchPhysicalProduct(@RequestParam String key) {
        System.out.println("search");
        System.out.println(key);
        List<PhysicalProductDTO> productDTOS = productService.searchByTitle(key);
        List<PhysicalProductDTO> DTOList = new ArrayList<>();
        for (PhysicalProductDAO item: productDTOS)
            DTOList.add(productMapper.toPhysicalProductDTO(item));
        System.out.println(DTOList.size());
        return ResponseEntity.ok(DTOList.get(1));
    }*/

    @GetMapping("/digital/getdetails")
    public ResponseEntity<DigitalProductDTO> getDigitalProductByID(@RequestParam int ID) {
        DigitalProductDTO productDTO = productService.getDigitalByID(ID);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/physical/search")
    public ResponseEntity<List<ProductBriefDTO>> searchPhysicalProduct(@RequestParam String key) {
        System.out.println("search");
        System.out.println(key);
        List<ProductBriefDTO> productDTOS = productService.searchByTitle(key);
        System.out.println(productDTOS.size());
        return ResponseEntity.ok(productDTOS);
    }

    /*@PostMapping("/physical/save")
    public ResponseEntity<PhysicalProductDTO> savePhysicalProduct(@RequestBody PhysicalProductDTO physicalProductDTO) throws IOException {
        System.out.println("lklk");
        PhysicalProductDAO physicalProductDAO = productMapper.toPhysicalProductDAO(physicalProductDTO);
        System.out.println(physicalProductDAO.getId());
        List<PhysicalImageDAO> list = new ArrayList<>();
        File file = new File("C:/Users/Islam/Desktop/download.jpeg");
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        fis.read(buffer);
        PhysicalImageDAO obj = new PhysicalImageDAO();
        obj.setProduct_id(90);
        obj.setImage(buffer);
        list.add(obj);

        file = new File("C:/Users/Islam/Desktop/d.jpeg");
        fis = new FileInputStream(file);
        buffer = new byte[(int) file.length()];
        fis.read(buffer);
        obj = new PhysicalImageDAO();
        obj.setProduct_id(90);
        obj.setImage(buffer);
        list.add(obj);

        productService.save(physicalProductDAO, list);
        return ResponseEntity.ok(productMapper.toPhysicalProductDTO(physicalProductDAO));
    }*/

    /*@PostMapping("/physical/save2")
    public ResponseEntity<PhysicalProductDTO> save(@RequestBody PhysicalProductDTO physicalProductDTO) {
        PhysicalProductDAO physicalProductDAO = productMapper.toPhysicalProductDAO(physicalProductDTO);
        productService.save(physicalProductDAO);
        return ResponseEntity.ok(productMapper.toPhysicalProductDTO(physicalProductDAO));
    }*/

}
