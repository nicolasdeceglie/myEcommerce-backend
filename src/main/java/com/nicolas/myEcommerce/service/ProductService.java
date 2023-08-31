package com.nicolas.myEcommerce.service;

import com.nicolas.myEcommerce.dto.ProductDTO;
import com.nicolas.myEcommerce.exception.IdNotFoundException;
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
    public ProductDTO createProductWithImage(ProductDTO productDTO, MultipartFile imageFile) throws IOException {
        byte[] imageBytes = imageFile.getBytes();
        String imageId = saveImageToStorage(imageBytes, imageFile.getOriginalFilename());
        productDTO.setImage(imageId);
        productDTO.setCreatedAt(new Date(System.currentTimeMillis()));
        Product product = repository.save(modelMapper.map(productDTO, Product.class));
        return modelMapper.map(product, ProductDTO.class);
    }

    private String saveImageToStorage(byte[] imageBytes, String fileName) throws IOException {
        Path imagesDirectory = Paths.get("/Users/nicolasdeceglie/Desktop/images");
        Path imagePath = imagesDirectory.resolve(fileName);
        Files.write(imagePath, imageBytes);
        return fileName;
    }
    public ProductDTO createProductWithoutImage(ProductDTO productDTO) {
        Product productToSave = modelMapper.map(productDTO, Product.class);
        productToSave.setCreatedAt(new Date(System.currentTimeMillis()));
        productToSave = repository.save(productToSave);
        return modelMapper.map(productToSave, ProductDTO.class);
    }
    @Transactional
    public ProductDTO uploadImage(MultipartFile imageFile) throws IOException {
        String imageID = saveImageToStorage(imageFile.getBytes(), imageFile.getOriginalFilename());
        Product productToSave = new Product();
        productToSave.setImage(imageID);
        productToSave.setCreatedAt(new Date(System.currentTimeMillis()));
        productToSave = repository.save(productToSave);
        return modelMapper.map(productToSave, ProductDTO.class);
    }
}
