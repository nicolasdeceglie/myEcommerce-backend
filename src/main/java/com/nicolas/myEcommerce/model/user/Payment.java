package com.nicolas.myEcommerce.model.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paymentType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}