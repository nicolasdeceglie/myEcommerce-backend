package com.nicolas.myEcommerce.dto.order;

import com.nicolas.myEcommerce.dto.UserDTO;
import com.nicolas.myEcommerce.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Double total;
    private UserDTO user;
    private List<ProductDTO> products;
    private PaymentDetailsDTO paymentDetails;
    private String userAddress;
    private String userPhoneNumber;
}