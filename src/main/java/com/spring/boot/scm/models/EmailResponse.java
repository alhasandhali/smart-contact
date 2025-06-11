package com.spring.boot.scm.models;

public class EmailResponse {
    private String token;

    public EmailResponse() {
    }

    public EmailResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "EmailResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
