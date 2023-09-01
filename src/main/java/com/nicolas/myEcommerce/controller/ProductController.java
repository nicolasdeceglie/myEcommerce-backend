package com.nicolas.myEcommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicolas.myEcommerce.dto.ProductDTO;
import com.nicolas.myEcommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products", params =  "pageIndex")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAll(@RequestParam int pageIndex){
        return productService.findAll(pageIndex);
    }
    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getById(@PathVariable("id") long id){
        return productService.getById(id);
    }
    @GetMapping(value = "/products", params = "sort")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAllSortedByNameDescending(@RequestParam int pageIndex){
        return productService.findAllSortedByNameDescending(pageIndex);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> create(@RequestParam("product") String productJson, @RequestParam("image")MultipartFile imageFIle) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductDTO productDTO = objectMapper.readValue(productJson, ProductDTO.class);
            if (imageFIle.isEmpty()){
                productDTO = productService.createProductWithoutImage(productDTO);
            }else{
                productDTO = productService.createProductWithImage(productDTO, imageFIle);
            }
            return ResponseEntity.ok(productDTO);
        }catch (Exception e){
            System.out.println("Exception occurred due to " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating product with image");
        }
    }
    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO update(@RequestBody ProductDTO productToUpdate, @PathVariable("id") long id){
        return productService.update(productToUpdate, id);
    }
    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO delete(@PathVariable("id") long id){
        return productService.delete(id);
    }

    @PostMapping("/upload-image")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile imageFile){
        try{
            if (imageFile.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload");
            }
            productService.uploadImage(imageFile);
            return ResponseEntity.ok("Image uploaded successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }
}