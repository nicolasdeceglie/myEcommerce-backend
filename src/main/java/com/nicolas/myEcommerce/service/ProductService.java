package com.nicolas.myEcommerce.service;

import com.nicolas.myEcommerce.dto.ProductDTO;
import com.nicolas.myEcommerce.exception.IdNotFoundException;
import com.nicolas.myEcommerce.model.product.Image;
import com.nicolas.myEcommerce.model.product.Product;
import com.nicolas.myEcommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> findAll(int pageIndex){
        final int pageSize = 10;
        return repository.findAll(PageRequest.of(pageIndex, pageSize))
                .stream()
                .map(item -> modelMapper.map(item, ProductDTO.class))
                .toList();
    }
    public List<ProductDTO> findAllSortedByNameDescending(int pageIndex){
        final int pageSize = 10;
        return repository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by("name").descending()))
                .stream()
                .map(item-> modelMapper.map(item, ProductDTO.class))
                .collect(Collectors.toList());
    }
    public ProductDTO getById(long id){
        Product product = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Product with ID " + id + " not found"));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Transactional
    public ProductDTO createProductWithImage(ProductDTO productDTO, MultipartFile imageFile) {
        Product product = modelMapper.map(productDTO, Product.class);
        product = save(imageFile, product);
        return modelMapper.map(product, ProductDTO.class);
    }
    String saveImageToStorage(byte[] imageBytes, String fileName) {
        try{
            Path imagesDirectory = Paths.get("/Users/nicolasdeceglie/Desktop/images");
            Path imagePath = imagesDirectory.resolve(fileName);
            Files.write(imagePath, imageBytes);
        }catch (IOException e){
            System.out.println("Exception occurred due to " + e.getMessage());
        }
        return fileName;
    }
    void deleteImageFromStorage(String fileName) {
        try{
            Path imagesDirectory = Paths.get("/Users/nicolasdeceglie/Desktop/images");
            Path imagePath = imagesDirectory.resolve(fileName);
            Files.delete(imagePath);
        }catch (IOException e){
            System.out.println("Exception occurred due to " + e.getMessage());
        }
    }
    private Product save(MultipartFile imageFile, Product productToSave) {
        Image image = new Image();
        try {
            String imageID = saveImageToStorage(imageFile.getBytes(), imageFile.getOriginalFilename());
            image.setUrl(imageID);
            image.setName(imageFile.getOriginalFilename());
            image.setProduct(productToSave);

            productToSave.getImages().add(image);
            productToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
            productToSave = repository.save(productToSave);
        } catch (IOException e) {
            System.out.println("Exception occurred due to " + e.getMessage());
        }
        return productToSave;
    }
    @Transactional
    public ProductDTO createProductWithoutImage(ProductDTO productDTO) {
        Product productToSave = modelMapper.map(productDTO, Product.class);
        productToSave.setCreatedAt(new Date(System.currentTimeMillis()));
        productToSave = repository.save(productToSave);
        return modelMapper.map(productToSave, ProductDTO.class);
    }
    @Transactional
    public ProductDTO uploadImage(MultipartFile imageFile, long id) {
        if (getById(id) == null) {
            throw new IdNotFoundException("Product with ID " + id + " not found");
        }
        Product productToSave = modelMapper.map(getById(id), Product.class);
        productToSave = save(imageFile, productToSave);
        return modelMapper.map(productToSave, ProductDTO.class);
    }
    @Transactional
    public ProductDTO update(ProductDTO productToUpdate, long id) {
        if (getById(id) == null) {
            throw new IdNotFoundException("Product with ID " + id + " not found");
        }
        Product product = modelMapper.map(productToUpdate, Product.class);
        product.setUpdatedAt(new Date(System.currentTimeMillis()));
        product = repository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }
    @Transactional
    public ProductDTO delete(long id) {
        ProductDTO productToDelete = getById(id);
        repository.deleteById(productToDelete.getId());
        deleteImageFromStorage(productToDelete.getImage());
        return productToDelete;
    }
}
