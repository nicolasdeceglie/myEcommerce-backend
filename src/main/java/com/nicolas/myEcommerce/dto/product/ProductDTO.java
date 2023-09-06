package com.nicolas.myEcommerce.dto.product;

import com.nicolas.myEcommerce.dto.order.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private boolean isAvailable;
    private Integer quantity;
    private String createdAt;
    private String updatedAt;
    private List<ImageDTO> images;
    private ItemDTO orderItems;
    private DiscountDTO discount;
    private CategoryDTO category;
}

