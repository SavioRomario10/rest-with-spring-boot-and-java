package io.savioromario10.rest_spring_java.service;

import io.savioromario10.rest_spring_java.data.dto.security.AccountCredentialsDto;
import io.savioromario10.rest_spring_java.data.dto.security.TokenDto;
import io.savioromario10.rest_spring_java.repository.UserRepository;
import io.savioromario10.rest_spring_java.security.jwt.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<TokenDto> signIn(AccountCredentialsDto data){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        data.getUsername(),
                        data.getPassword()
                )
        );

        var user = userRepository.findByUsername(data.getUsername());

        if(user == null)
            throw new UsernameNotFoundException("Username " + data.getUsername() + " not found");

        var tokenResponse = jwtTokenProvider.createAccessToken(
            data.getUsername(),
            user.getRoles()
        );

        return ResponseEntity.ok(tokenResponse);
    }
}