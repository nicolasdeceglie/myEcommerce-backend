package com.nicolas.myEcommerce.dto.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator.class, property = "id", scope = ImageDTO.class)
public class ImageDTO implements Serializable {
    private Long id;
    private String name;
    private String url;
    private ProductDTO product;
}