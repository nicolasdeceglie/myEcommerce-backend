package com.nicolas.myEcommerce.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private Long id;
    private String name;
    private String url;
    private ProductDTO product;
}
