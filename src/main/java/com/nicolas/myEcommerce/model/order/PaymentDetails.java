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
    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Detail orderDetail;
}
