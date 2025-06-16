package com.javaweb.builder;

public class RoleSearchBuilder {
    private final Long id;
    private final String name;
    private final String code;

    public RoleSearchBuilder(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.code = builder.code;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String code;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public RoleSearchBuilder build() {
            return new RoleSearchBuilder(this);
        }
    }
}
