package org.example.usuedormitory.Payloads.Response;

import org.springframework.http.ResponseCookie;

public class JwtResponse {
    private ResponseCookie token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String role;

    public JwtResponse(ResponseCookie accessToken, Long id, String username, String role) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public ResponseCookie getAccessToken() {
        return token;
    }

    public void setAccessToken(ResponseCookie accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
