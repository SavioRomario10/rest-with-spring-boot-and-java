package io.savioromario10.rest_spring_java.data.dto.security;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    public TokenDto() {}

    public TokenDto(String accessToken, Boolean authenticated, Date created, Date expiration, String refreshToken, String username) {
        this.accessToken = accessToken;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.refreshToken = refreshToken;
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public Boolean getAuthenticated() {
        return authenticated;
    }
    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getExpiration() {
        return expiration;
    }
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TokenDto tokenDto)) return false;
        return Objects.equals(getUsername(), tokenDto.getUsername()) &&
                Objects.equals(getPassword(), tokenDto.getPassword()) &&
                Objects.equals(getAuthenticated(), tokenDto.getAuthenticated()) &&
                Objects.equals(getCreated(), tokenDto.getCreated()) &&
                Objects.equals(getExpiration(), tokenDto.getExpiration()) &&
                Objects.equals(getAccessToken(), tokenDto.getAccessToken()) &&
                Objects.equals(getRefreshToken(), tokenDto.getRefreshToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getAuthenticated(), getCreated(), getExpiration(),
                getAccessToken(), getRefreshToken());
    }
}