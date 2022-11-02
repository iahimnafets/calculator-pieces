package com.calculator.exception;

import com.calculator.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {

    @ExceptionHandler( value = {ApiRequestException.class} )
    public final ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        Response responseEx = Response.builder()
                .status(badRequest)
                .statusCode(badRequest.value())
                .message( e.getMessage() )
                .timeStamp( LocalDateTime.now() )
                .build();

        return new ResponseEntity<>(responseEx,
                     badRequest);
    }

    @ExceptionHandler ( Exception.class )
    public final ResponseEntity<Object> handleAllExceptions( Exception e )
    {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        Response responseEx = Response.builder()
                .status(badRequest)
                .statusCode(badRequest.value())
                .message( e.getMessage() )
                .timeStamp( LocalDateTime.now() )
                .build();

        return new ResponseEntity<>(responseEx,
                badRequest);
    }

}
