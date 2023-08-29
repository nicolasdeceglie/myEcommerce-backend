package com.nicolas.myEcommerce.model.order;

import com.nicolas.myEcommerce.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "order_details")
@Data
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double total;
    @OneToOne(mappedBy = "orderDetail", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(mappedBy = "orderDetail", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "payment_id")
    private PaymentDetails payment;
    @OneToMany(mappedBy = "orderDetail", cascade = CascadeType.REMOVE)
    private List<Item> orderItems;
}