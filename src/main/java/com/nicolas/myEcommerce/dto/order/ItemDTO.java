package com.nicolas.myEcommerce.dto.order;

import com.nicolas.myEcommerce.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private Integer quantity;
    private OrderDTO order;
    private ProductDTO product;
}