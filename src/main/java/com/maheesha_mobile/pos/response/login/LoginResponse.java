package com.maheesha_mobile.pos.response.login;

public class LoginResponse {
    private String message;
    private String newAccessToken;
    private String newRefreshToken;
    private String username;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewAccessToken() {
        return newAccessToken;
    }

    public void setNewAccessToken(String newAccessToken) {
        this.newAccessToken = newAccessToken;
    }

    public String getNewRefreshToken() {
        return newRefreshToken;
    }

    public void setNewRefreshToken(String newRefreshToken) {
        this.newRefreshToken = newRefreshToken;
    }
}
