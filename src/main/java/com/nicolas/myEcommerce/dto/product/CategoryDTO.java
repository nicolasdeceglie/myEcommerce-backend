package com.nicolas.myEcommerce.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private List<ProductDTO> products;
}