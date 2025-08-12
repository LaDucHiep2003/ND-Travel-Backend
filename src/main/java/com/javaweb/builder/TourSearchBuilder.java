package com.javaweb.builder;

import java.time.LocalDate;

public class TourSearchBuilder {
    private final Long id;
    private final String title;
    private final String departure_from;
    private final String destination;
    private final String duration;
    private final Integer seats;
    private final String transport;
    private final LocalDate departure_date;
    private final Integer price_adultFrom;
    private final Integer price_adultTo;
    private final String status;
    private final LocalDate end_date;


    private TourSearchBuilder(Builder builder) {
        this.title = builder.title;
        this.departure_from = builder.departure_from;
        this.destination = builder.destination;
        this.duration = builder.duration;
        this.seats = builder.seats;
        this.transport = builder.transport;
        this.id = builder.id;
        this.departure_date = builder.departure_date;
        this.price_adultFrom = builder.price_adultFrom;
        this.price_adultTo = builder.price_adultTo;
        this.status = builder.status;
        this.end_date = builder.end_date;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDeparture_from() {
        return departure_from;
    }

    public String getDestination() {
        return destination;
    }

    public String getDuration() {
        return duration;
    }

    public Integer getSeats() {
        return seats;
    }

    public String getTransport() {
        return transport;
    }

    public LocalDate getDeparture_date() {
        return departure_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public Integer getPrice_adultFrom() {
        return price_adultFrom;
    }

    public Integer getPrice_adultTo() {
        return price_adultTo;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder {
        private Long id;
        private String title;
        private String departure_from;
        private String destination;
        private String duration;
        private Integer seats;
        private String transport;
        private LocalDate departure_date;
        private  Integer price_adultFrom;
        private  Integer price_adultTo;
        private String status;
        private LocalDate end_date;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDeparture_from(String departure_from) {
            this.departure_from = departure_from;
            return this;
        }

        public Builder setDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder setDuration(String duration) {
            this.duration = duration;
            return this;
        }

        public Builder setSeats(Integer seats) {
            this.seats = seats;
            return this;
        }

        public Builder setTransport(String transport) {
            this.transport = transport;
            return this;
        }

        public Builder setDeparture_date(LocalDate departure_date) {
            this.departure_date = departure_date;
            return this;
        }

        public Builder setPrice_adultFrom(Integer price_adultFrom) {
            this.price_adultFrom = price_adultFrom;
            return this;
        }

        public Builder setPrice_adultTo(Integer price_adultTo) {
            this.price_adultTo = price_adultTo;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setEnd_date(LocalDate end_date) {
            this.end_date = end_date;
            return this;
        }

        public TourSearchBuilder build() {
            return new TourSearchBuilder(this);
        }
    }
}
