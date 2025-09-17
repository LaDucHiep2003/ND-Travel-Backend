package com.javaweb.service.impl;

import com.javaweb.builder.OrderSearchBuilder;
import com.javaweb.converter.OrderDTOConverter;
import com.javaweb.converter.OrderSearchBuilderConverter;
import com.javaweb.model.OrderDTO;
import com.javaweb.model.OrderItemDTO;
import com.javaweb.model.ApiResponse;
import com.javaweb.model.request.OrderRequest;
import com.javaweb.model.response.OrderResponse;
import com.javaweb.repository.OrderRepository;
import com.javaweb.repository.TourRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.entity.OrderEntity;
import com.javaweb.repository.entity.OrderItemEntity;
import com.javaweb.repository.entity.TourEntity;
import com.javaweb.repository.entity.UserEntity;
import com.javaweb.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderSearchBuilderConverter orderSearchBuilderConverter;

    @Autowired
    private OrderDTOConverter orderDTOConverter;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<OrderResponse> findAll(Map<String, Object> params) {
        OrderSearchBuilder orderSearchBuilder = orderSearchBuilderConverter.toOrderSearchBuilder(params);
        List<OrderEntity> orderEntities = orderRepository.findALl(orderSearchBuilder);
        List<OrderResponse> result = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            OrderResponse orderDTO = orderDTOConverter.toOrderDTO(orderEntity);
            orderDTO.setCustomer_name(orderEntity.getUser().getFullname());
            result.add(orderDTO);
        }
        return result;
    }

    @Override
    public OrderResponse findById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).get();
        OrderResponse orderDTO = orderDTOConverter.toOrderDTO(orderEntity);
        return orderDTO;
    }

    @Override
    public OrderResponse edit(OrderRequest dto) {
        OrderEntity orderEntity = orderRepository.findById(dto.getId()).get();

        orderEntity.setStatus(dto.getStatus());
        orderEntity.setPaymentMethod(dto.getPaymentMethod());
        orderEntity.setNote(dto.getNote());

        BigDecimal totalPrice = BigDecimal.ZERO;

        orderEntity.getOrderItems().clear();
        if (dto.getOrderItems() != null && !dto.getOrderItems().isEmpty()) {
            for (OrderItemDTO itemDTO : dto.getOrderItems()) {
                OrderItemEntity itemEntity = new OrderItemEntity();
                itemEntity.setOrder(orderEntity);

                // Lấy TourEntity từ DB
                TourEntity tourEntity = tourRepository.findById(itemDTO.getTourId())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy tour"));

                itemEntity.setTour(tourEntity);
                itemEntity.setQuantity(itemDTO.getQuantity());
                itemEntity.setUnitPrice(itemDTO.getUnitPrice());
                itemEntity.setSubtotal(itemDTO.getUnitPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity())));

                orderEntity.getOrderItems().add(itemEntity);

                BigDecimal subtotal = itemDTO.getUnitPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
                totalPrice = totalPrice.add(subtotal);
            }
        }
        orderEntity.setTotalPrice(totalPrice);
        OrderEntity result = orderRepository.save(orderEntity);

        return orderDTOConverter.toOrderDTO(result);
    }

    @Override
    public OrderResponse order(OrderRequest order) {
        OrderEntity orderEntity = new OrderEntity();

        UserEntity user = userRepository.findById(order.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        orderEntity.setUser(user);
        orderEntity.setTotalPrice(order.getTotalPrice());
        orderEntity.setStatus("PENDING");
        orderEntity.setPaymentMethod(order.getPaymentMethod());
        orderEntity.setNote(order.getNote());

        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItemEntity> orderItems = new ArrayList<>();

        for (OrderItemDTO itemDTO : order.getOrderItems()) {
            TourEntity tour = tourRepository.findById(itemDTO.getTourId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tour ID: " + itemDTO.getTourId()));

            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setOrder(orderEntity);
            orderItem.setTour(tour);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setUnitPrice(itemDTO.getUnitPrice());

            BigDecimal subtotal = itemDTO.getUnitPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            orderItem.setSubtotal(subtotal);

            totalPrice = totalPrice.add(subtotal);

            orderItems.add(orderItem);
        }
        orderEntity.setOrderItems(orderItems);
        orderEntity.setTotalPrice(totalPrice);

        OrderEntity result = orderRepository.save(orderEntity);
        return orderDTOConverter.toOrderDTO(result);
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        orderRepository.delete(ids);
    }

    @Override
    @Transactional
    public void confirm(List<Long> ids) {
        orderRepository.confirmOrders(ids);
    }
}
