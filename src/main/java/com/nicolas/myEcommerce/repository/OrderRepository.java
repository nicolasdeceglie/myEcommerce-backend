package com.nicolas.myEcommerce.repository;

import com.nicolas.myEcommerce.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserId(Long userId);

    Order findByPaymentDetailsId(Long id);

}
