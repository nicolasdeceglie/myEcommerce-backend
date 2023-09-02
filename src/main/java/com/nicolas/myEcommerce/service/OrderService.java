package com.nicolas.myEcommerce.service;


import com.nicolas.myEcommerce.dto.OrderDTO;
import com.nicolas.myEcommerce.exception.IdNotFoundException;
import com.nicolas.myEcommerce.model.order.Order;
import com.nicolas.myEcommerce.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        order = orderRepository.save(order);
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Order with ID " + id + " not found"));
        return modelMapper.map(order, OrderDTO.class);
    }

    @Transactional
    public OrderDTO updateOrder(OrderDTO orderDTO, Long id) {
        if (getOrderById(id) == null) {
            throw new IdNotFoundException("Order with ID " + id + " not found");
        }
        Order order = modelMapper.map(orderDTO, Order.class);
        order.setId(id);
        order = orderRepository.save(order);
        return modelMapper.map(order, OrderDTO.class);
    }

    @Transactional
    public void deleteOrder(Long id) {
        if (getOrderById(id) == null) {
            throw new IdNotFoundException("Order with ID " + id + " not found");
        }
        orderRepository.deleteById(id);
    }

    public OrderDTO findByUser(Long userId) {
        Order order = orderRepository.findByUser(userId);
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO findByPaymentDetailsId(Long paymentDetailsId) {
        Order order = orderRepository.findByPaymentDetailsId(paymentDetailsId);
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO findByUserAndPaymentDetailsId(Long userId, Long paymentDetailsId) {
        Order order = orderRepository.findByUserAndPaymentDetailsId(userId, paymentDetailsId);
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO findByUserAndId(Long userId, Long id) {
        Order order = orderRepository.findByUserAndId(userId, id);
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO findByUserAndPaymentDetailsIdAndId(Long userId, Long paymentDetailsId, Long id) {
        Order order = orderRepository.findByUserAndPaymentDetailsIdAndId(userId, paymentDetailsId, id);
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO findByUserAndPaymentDetailsIdAndIdAndOrderItemsId(Long userId, Long paymentDetailsId, Long id, Long itemId) {
        Order order = orderRepository.findByUserAndPaymentDetailsIdAndIdAndOrderItemsId(userId, paymentDetailsId, id, itemId);
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO findByUserAndIdAndOrderItemsId(Long userId, Long id, Long itemId) {
        Order order = orderRepository.findByUserAndIdAndOrderItemsId(userId, id, itemId);
        return modelMapper.map(order, OrderDTO.class);
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }
}
