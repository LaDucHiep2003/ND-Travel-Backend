package com.javaweb.repository.entity;


import jakarta.persistence.*;

@Entity
@Table(name="tour_category_mapping")
public class TourCategoryMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private TourEntity tour;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private TourCategoryEntity category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TourEntity getTour() {
        return tour;
    }

    public void setTour(TourEntity tour) {
        this.tour = tour;
    }

    public TourCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(TourCategoryEntity category) {
        this.category = category;
    }
}
