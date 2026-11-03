package io.savioromario10.rest_spring_java.controller;

import io.savioromario10.rest_spring_java.data.dto.security.AccountCredentialsDto;
import io.savioromario10.rest_spring_java.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Operation(summary = "Authenticate a user and return a token")
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody AccountCredentialsDto credentials){

        if(credentialsIsInvalid(credentials)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }

        var token = authService.signIn(credentials);

        if(token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        return ResponseEntity.ok().body(token);
    }

    @Operation(summary = "Authenticate a user and return a refresh token")
    @PutMapping("/refresh/{username}")
    public ResponseEntity<?> refresh(@PathVariable("username") String username,
                                     @RequestHeader("Authorization") String refreshToken){

        if(parametersAreInvalid(username, refreshToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }

        var token = authService.refreshToken(username, refreshToken);

        if(token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        return ResponseEntity.ok().body(token);
    }

    private boolean parametersAreInvalid(String username, String refreshToken) {
        return StringUtils.isBlank(username) || StringUtils.isBlank(refreshToken);
    }

    private static boolean credentialsIsInvalid(AccountCredentialsDto credentials) {
        return credentials == null || StringUtils.isBlank(credentials.getPassword()) ||
                StringUtils.isBlank(credentials.getUsername());
    }
}