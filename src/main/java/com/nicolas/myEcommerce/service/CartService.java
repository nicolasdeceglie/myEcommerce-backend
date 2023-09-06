package com.nicolas.myEcommerce.service;

import com.nicolas.myEcommerce.model.cart.CartItem;
import com.nicolas.myEcommerce.model.product.Product;
import com.nicolas.myEcommerce.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Transactional
    public void addProduct(Product product, int quantity) {
        CartItem item = cartRepository.findByProduct(product);
        if (item == null) {
            item = new CartItem();
            item.setProduct(product);
        }
        item.setQuantity(quantity);
        cartRepository.save(item);
    }
    @Transactional
    public void removeProduct(Product product) {
        CartItem item = cartRepository.findByProduct(product);
        if (item != null) {
            cartRepository.delete(item);
        }
    }

    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }
    @Transactional
    public void clearCart() {
        cartRepository.deleteAll();
    }

    public double getTotal() {
        double total = 0;
        List<CartItem> cartItems = cartRepository.findAll();
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }
}
