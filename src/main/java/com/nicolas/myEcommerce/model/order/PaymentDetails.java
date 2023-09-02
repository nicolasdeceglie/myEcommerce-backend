package com.nicolas.myEcommerce.model.order;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payment_details")
@Data
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @OneToOne(mappedBy = "paymentDetails", fetch = FetchType.LAZY)
    private Order order;
}
