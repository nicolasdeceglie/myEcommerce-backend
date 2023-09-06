package com.nicolas.myEcommerce.repository;


import com.nicolas.myEcommerce.model.cart.CartItem;
import com.nicolas.myEcommerce.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {


    CartItem findByProduct(Product product);
}
