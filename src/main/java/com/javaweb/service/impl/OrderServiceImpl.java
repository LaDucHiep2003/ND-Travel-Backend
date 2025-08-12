package com.javaweb.service.impl;

import com.javaweb.builder.OrderSearchBuilder;
import com.javaweb.converter.OrderDTOConverter;
import com.javaweb.converter.OrderSearchBuilderConverter;
import com.javaweb.model.OrderDTO;
import com.javaweb.model.OrderItemDTO;
import com.javaweb.model.response.ApiResponse;
import com.javaweb.repository.OrderRepository;
import com.javaweb.repository.TourRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.entity.OrderEntity;
import com.javaweb.repository.entity.OrderItemEntity;
import com.javaweb.repository.entity.TourEntity;
import com.javaweb.repository.entity.UserEntity;
import com.javaweb.service.OrderService;
import com.javaweb.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public List<OrderDTO> findAll(Map<String, Object> params) {
        OrderSearchBuilder orderSearchBuilder = orderSearchBuilderConverter.toOrderSearchBuilder(params);
        List<OrderEntity> orderEntities = orderRepository.findALl(orderSearchBuilder);
        List<OrderDTO> result = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            OrderDTO orderDTO = orderDTOConverter.toOrderDTO(orderEntity);
            orderDTO.setCustomer_name(orderEntity.getUser().getFullname());
            result.add(orderDTO);
        }
        return result;
    }

    @Override
    public OrderDTO findById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).get();
        OrderDTO orderDTO = orderDTOConverter.toOrderDTO(orderEntity);
        return orderDTO;
    }

    @Override
    public ApiResponse<OrderEntity> edit(OrderDTO dto) {
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
        orderRepository.save(orderEntity);

        return new ApiResponse<>(200, "Edit successfully", null);
    }

    @Override
    public ApiResponse<OrderEntity> order(OrderDTO order) {
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

        orderRepository.save(orderEntity);
        return new ApiResponse<>(201, "Order successfully", null);
    }

    @Override
    @Transactional
    public ApiResponse<OrderEntity> delete(List<Long> ids) {
        orderRepository.delete(ids);
        return new ApiResponse<>(200, "Delete successfully", null);
    }

    @Override
    @Transactional
    public ApiResponse<OrderEntity> confirm(List<Long> ids) {
        orderRepository.confirmOrders(ids);
        return new ApiResponse<>(200, "Confirm Orders successfully", null);
    }
}
