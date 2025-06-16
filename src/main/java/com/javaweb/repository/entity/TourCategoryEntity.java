package com.javaweb.repository.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tour_category")
public class TourCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TourCategoryEntity parent_id;

    @Column(name = "image")
    private String image;

    @Column(name = "deleted")
    private boolean deleted;

    @ManyToMany(mappedBy = "tourCategories")
    private List<TourEntity> tours = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TourCategoryEntity getParent_id() {
        return parent_id;
    }

    public void setParent_id(TourCategoryEntity parent_id) {
        this.parent_id = parent_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<TourEntity> getTours() {
        return tours;
    }

    public void setTours(List<TourEntity> tours) {
        this.tours = tours;
    }
}
