package com.nicolas.myEcommerce.dto.product;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nicolas.myEcommerce.dto.order.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator.class, property = "id", scope = ProductDTO.class)
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean isAvailable;
    private Integer quantity;
    private String createdAt;
    private String updatedAt;
    private List<ImageDTO> images;
    private DiscountDTO discount;
    private CategoryDTO category;
}