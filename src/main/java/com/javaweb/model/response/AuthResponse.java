package com.javaweb.model.response;

public class AuthResponse {
    private String token;
    private String description;
    private int status;

    public AuthResponse(int status, String description,  String token) {
        this.status = status;
        this.description = description;
        this.token = token;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
