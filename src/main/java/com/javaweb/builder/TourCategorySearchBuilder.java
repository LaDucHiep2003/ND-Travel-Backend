package com.javaweb.builder;

public class TourCategorySearchBuilder {
    private final Integer subCategory;
    private final Integer parent_id;

    public TourCategorySearchBuilder(Builder builder) {
        this.subCategory = builder.subCategory;
        this.parent_id = builder.parent_id;
    }

    public Integer getSubCategory() {
        return subCategory;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public static class Builder{
        private Integer subCategory;
        private Integer parent_id;

        public Builder setSubCategory(Integer subCategory) {
            this.subCategory = subCategory;
            return this;
        }
        public Builder setParent_id(Integer parent_id) {
            this.parent_id = parent_id;
            return this;
        }

        public TourCategorySearchBuilder build(){
            return new TourCategorySearchBuilder(this);
        }
    }
}
