package io.savioromario10.rest_spring_java.controller;

import io.savioromario10.rest_spring_java.data.dto.request.EmailRequestDTO;

import io.savioromario10.rest_spring_java.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping
    @Operation(
        summary = "Send an Email",
        description = "Send an Emails by providing details",
        tags = {"Email"},
        responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO request){

        service.sendSimpleEmail(request);

        return new ResponseEntity<>("email sent with success", HttpStatus.OK);
    }

    @PostMapping(
            value = "/withAttachment",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
        summary = "Send an Email with Attachment",
        description = "Send an Emails by providing details",
        tags = {"Email"},
        responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<String> sendEmailWithAttachment(
            @RequestParam("emailRequest") String emailRequestJson,
            @RequestParam("attachment") MultipartFile multFile){

        service.setEmailWithAttachment(emailRequestJson, multFile);

        return new ResponseEntity<>("email with attachment sent successfully", HttpStatus.OK);
    }
}