package com.nicolas.myEcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private boolean isAvailable;
    private Integer quantity;
    private Date createdAt;
    private Long categoryId;
    private Long discountId;
}