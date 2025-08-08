package com.javaweb.builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderSearchBuilder {
    private final Long id;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final BigDecimal totalPrice;

    public OrderSearchBuilder(Builder builder) {
        this.id = builder.id;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.totalPrice = builder.totalPrice;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public static class Builder{
        private Long id;
        private String status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private BigDecimal totalPrice;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder setTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public OrderSearchBuilder build(){
            return new OrderSearchBuilder(this);
        }
    }
}
