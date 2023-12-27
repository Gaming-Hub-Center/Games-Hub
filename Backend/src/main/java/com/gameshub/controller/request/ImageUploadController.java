//package com.gameshub.controller.request;
//
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api")
//public class ImageUploadController {
//
//    private final Cloudinary cloudinary;
//    public ImageUploadController(Cloudinary cloudinary) {
//        this.cloudinary = cloudinary;
//    }
//
//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {
//        Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
//        return ResponseEntity.ok(uploadResult);
//    }
//}
