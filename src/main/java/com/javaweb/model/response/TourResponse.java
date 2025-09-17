package com.javaweb.model.response;

import java.time.LocalDate;

public class TourResponse {
    private Long id;
    private String title;
    private String thumbnail;
    private String images;
    private String departure_from;
    private String destination;
    private String duration;
    private Integer seats;
    private String transport;
    private String description;
    private String itinerary;
    private String location;
    private Integer price_adult;
    private Integer price_child;
    private Integer price_infant;
    private String status;
    private LocalDate departure_date;
    private LocalDate end_date;
    private Double discount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDeparture_from() {
        return departure_from;
    }

    public void setDeparture_from(String departure_from) {
        this.departure_from = departure_from;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getPrice_adult() {
        return price_adult;
    }

    public void setPrice_adult(Integer price_adult) {
        this.price_adult = price_adult;
    }

    public Integer getPrice_child() {
        return price_child;
    }

    public void setPrice_child(Integer price_child) {
        this.price_child = price_child;
    }

    public Integer getPrice_infant() {
        return price_infant;
    }

    public void setPrice_infant(Integer price_infant) {
        this.price_infant = price_infant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(LocalDate departure_date) {
        this.departure_date = departure_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
