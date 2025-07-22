package com.javaweb.model.response;

public class AuthResponse {
    private String token;
    private String description;

    public AuthResponse(String token, String description) {
        this.token = token;
        this.description = description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
