package io.savioromario10.rest_spring_java.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import io.savioromario10.rest_spring_java.data.dto.security.TokenDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;

    @Value("${security.jwt.token.expire-lenght:3600000}")
    private long validityInMilliseconds;

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDto createAccessToken(String username, List<String> roles){

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        String accessToken = getAccessToken(username, roles, now, validity);
        String refreshToken = getRefreshToken(username, roles, now);

        return new TokenDto(accessToken, true, now, validity, refreshToken, username );
    }

    private String getRefreshToken(String username, List<String> roles, Date now) {

        return "";
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {

        return "";
    }
}