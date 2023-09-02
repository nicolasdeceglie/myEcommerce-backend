package com.nicolas.myEcommerce.repository;

import com.nicolas.myEcommerce.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUser(Long user);

    Order findByPaymentDetailsId(Long id);

    Order findByUserAndPaymentDetailsId(Long user, Long id);

    Order findByUserAndId(Long user, Long id);

    Order findByUserAndPaymentDetailsIdAndId(Long user, Long paymentId, Long id);

    Order findByUserAndPaymentDetailsIdAndIdAndOrderItemsId(Long user, Long paymentId, Long id, Long itemId);

    Order findByUserAndIdAndOrderItemsId(Long user, Long id, Long itemId);


}
