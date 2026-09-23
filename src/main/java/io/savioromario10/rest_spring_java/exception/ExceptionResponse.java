package io.savioromario10.rest_spring_java.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {

}