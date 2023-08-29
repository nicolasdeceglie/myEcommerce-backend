package com.nicolas.myEcommerce.model.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_address")
@Data
public class Address {
    private Long id;
    private String address;
    private String city;
    private String postalCod;
    private String country;
    private String telephone;
    private String mobile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}