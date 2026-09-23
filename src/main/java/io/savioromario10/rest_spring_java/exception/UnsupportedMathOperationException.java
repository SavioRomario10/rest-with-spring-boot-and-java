package io.savioromario10.rest_spring_java.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException{

  public UnsupportedMathOperationException(String msg){
    super(msg);
  }
}