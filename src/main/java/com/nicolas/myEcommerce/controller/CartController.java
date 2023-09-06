package com.nicolas.myEcommerce.controller;

import com.nicolas.myEcommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CartController {
    @Autowired
    private CartService cartService;

}