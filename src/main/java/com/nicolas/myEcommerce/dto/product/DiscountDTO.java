package com.nicolas.myEcommerce.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDTO {
    private Long id;
    private String name;
    private Double discountPercent;
    private boolean isActive;
    private Set<ProductDTO> products;
}