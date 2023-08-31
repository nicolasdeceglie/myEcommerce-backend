package com.nicolas.myEcommerce.model.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String city;
    @Column(name = "postal_cod")
    private String postalCod;
    private String country;
    private String telephone;
    private String mobile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}