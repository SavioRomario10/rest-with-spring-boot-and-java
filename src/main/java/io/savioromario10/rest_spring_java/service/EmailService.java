package io.savioromario10.rest_spring_java.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.savioromario10.rest_spring_java.config.EmailConfig;
import io.savioromario10.rest_spring_java.data.dto.request.EmailRequestDTO;
import io.savioromario10.rest_spring_java.mail.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private EmailConfig emailConfig;

    public void sendSimpleEmail(EmailRequestDTO request){

        emailSender
            .to(request.getTo())
            .withSubject(request.getSubject())
            .withMesage(request.getBody())
            .send(emailConfig);
    }

    public void setEmailWithAttachment(String emailRequestJson, MultipartFile attachment){

        File tempFile = null;

        try {
            EmailRequestDTO request = new ObjectMapper().readValue(emailRequestJson, EmailRequestDTO.class);
            tempFile = File.createTempFile("attachment", attachment.getOriginalFilename());
            attachment.transferTo(tempFile);

            emailSender
                    .to(request.getTo())
                    .withSubject(request.getSubject())
                    .withMesage(request.getBody())
                    .attach(tempFile.getAbsolutePath())
                    .send(emailConfig);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsong email request", e);
        } catch (IOException e) {
            throw new RuntimeException("Error processing the attachment", e);
        } finally {
            if(tempFile != null && tempFile.exists()) tempFile.delete();
        }
    }
}