package com.javaweb.builder;

public class UserSearchBuilder {
    private final String username;
    private final String email;
    private final String fullname;
    private final Long role;

    public UserSearchBuilder(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.fullname = builder.fullname;
        this.role = builder.role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public Long getRole() {
        return role;
    }

    public static class Builder {
        private String username;
        private String email;
        private String fullname;
        private Long role;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setFullname(String fullname) {
            this.fullname = fullname;
            return this;
        }

        public Builder setRole(Long role) {
            this.role = role;
            return this;
        }

        public UserSearchBuilder build() {
            return new UserSearchBuilder(this);
        }
    }
}
