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

    public List<ProductDTO> getAll(@RequestParam int pageIndex){
        return productService.findAll(pageIndex);
    }
    public ProductDTO getById(@PathVariable("id") long id){
        return productService.getById(id);
    }




    @PostMapping(value = "/create-with-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> createProductWithImage(@RequestParam("product") String productJson, @RequestParam("image")MultipartFile imageFIle) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductDTO productDTO = objectMapper.readValue(productJson, ProductDTO.class);
            if (imageFIle.isEmpty()){
                productDTO = productService.createProductWithoutImage(productDTO);
                return ResponseEntity.ok(productDTO);
            }else if(!productJson.isEmpty() && !imageFIle.isEmpty()){
                productDTO = productService.createProductWithImage(productDTO, imageFIle);
                return ResponseEntity.ok(productDTO);
            }else{
                 productDTO = productService.uploadImage(imageFIle);
                return ResponseEntity.ok(productDTO);
            }
        }catch (Exception e){
            System.out.println("Exception occurred due to " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating product with image");
        }
    }

}