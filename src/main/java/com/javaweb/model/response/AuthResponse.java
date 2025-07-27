package com.javaweb.model.response;

public class AuthResponse {
    private String token;
    private String description;
    private int status;
    private String role;

    public AuthResponse(int status, String description,  String token, String role) {
        this.status = status;
        this.description = description;
        this.token = token;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
