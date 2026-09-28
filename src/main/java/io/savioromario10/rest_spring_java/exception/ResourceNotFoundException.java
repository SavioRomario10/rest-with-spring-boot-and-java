package io.savioromario10.rest_spring_java.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

  public ResourceNotFoundException(String msg){
    super(msg);
  }
}