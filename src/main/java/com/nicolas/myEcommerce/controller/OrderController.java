package com.nicolas.myEcommerce.controller;

import com.nicolas.myEcommerce.dto.order.OrderDTO;
import com.nicolas.myEcommerce.service.OrderService;
import com.nicolas.myEcommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/orders")
    public String getOrders(){
        return orderService.findAll().toString();
    }

   @PostMapping("/orders")
   @ResponseStatus(CREATED)
    public String createOrder(@RequestBody OrderDTO orderJson){
         return orderService.createOrder(orderJson).toString();
    }

    @GetMapping("/orders/{id}")
    public String getOrderById(@PathVariable("id") Long id){
        return orderService.getOrderById(id).toString();
    }

    @PutMapping("/orders/{id}")
    public String updateOrder(@RequestBody OrderDTO orderToUpdate, @PathVariable("id") Long id){
        return orderService.updateOrder(orderToUpdate, id).toString();
    }

    @DeleteMapping("/orders/{id}")
    public String deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
        return "Order with ID " + id + " deleted successfully";
    }

    @GetMapping("/orders/user/{userId}")
    public String getOrderByUser(@PathVariable("userId") Long userId){
        return orderService.findByUser(userId).toString();
    }

    @GetMapping("/orders/user/{userId}/address")
    public String getAddressByUser(@PathVariable("userId") Long userId){
        return userService.getAddressByUser(userId);
    }

    @GetMapping("/orders/user/{userId}/phone")
    public String getPhoneByUser(@PathVariable("userId") Long userId){
        return userService.getPhoneByUser(userId);
    }

    @GetMapping("/orders/payment/{paymentDetailsId}")
    public String getOrderByPaymentDetailsId(@PathVariable("paymentDetailsId") Long paymentDetailsId){
        return orderService.findByPaymentDetailsId(paymentDetailsId).toString();
    }
}
