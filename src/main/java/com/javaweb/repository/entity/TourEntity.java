package com.javaweb.repository.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tours")
public class TourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "images")
    private String images;

    @Column(name = "departure_from")
    private String departure_from;

    @Column(name = "destination")
    private String destination;

    @Column(name = "duration")
    private String duration;

    @Column(name = "seats")
    private Integer seats;

    @Column(name = "transport")
    private String transport;

    @Column(name = "description")
    private String description;

    @Column(name = "itinerary")
    private String itinerary;

    @Column(name = "location")
    private String location;

    @Column(name = "price_adult", nullable = false)
    private Integer price_adult;

    @Column(name = "price_child", nullable = false)
    private Integer price_child;

    @Column(name = "price_infant", nullable = false)
    private Integer price_infant;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.active;

    @Column(name = "departure_date")
    private LocalDate departure_date;

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY)
    List<TourCategoryMapping> tour_category_mappings = new ArrayList<>();

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(LocalDate departure_date) {
        this.departure_date = departure_date;
    }

    public List<TourCategoryMapping> getTour_category_mappings() {
        return tour_category_mappings;
    }

    public void setTour_category_mappings(List<TourCategoryMapping> tour_category_mappings) {
        this.tour_category_mappings = tour_category_mappings;
    }
}
