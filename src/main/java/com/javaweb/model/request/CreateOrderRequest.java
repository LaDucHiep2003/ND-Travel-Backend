package com.javaweb.model.request;

import java.math.BigDecimal;
import java.util.List;

public class CreateOrderRequest {
    private Long userId;
    private String paymentMethod;
    private String note;
    private List<OrderItemRequest> orderItems;

    public static class OrderItemRequest {
        private Long tourId;
        private Integer quantity;

        public Long getTourId() {
            return tourId;
        }

        public void setTourId(Long tourId) {
            this.tourId = tourId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<OrderItemRequest> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemRequest> orderItems) {
        this.orderItems = orderItems;
    }
}
