package com.example.regionkommuner2.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice //for at controllerklasserne kan bruge denne.
//når spring starter laver den instans af denne klasse
public class ControllerExceptionHandler {

    //hver gang der kommer noget der nedarver fra exception kommer vi her ind:
    // det er i stedet for catch
    @ExceptionHandler(Exception.class) //alle exceptions der arver fra Exception kommer ind i denne metode, checked
    ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request){
        ErrorMessage message = new ErrorMessage( //laver instans af vores egen ErrorMessage klasse
                HttpStatus.INTERNAL_SERVER_ERROR.value(), //hardcoder en fejl 500, men vi kan gøre at det variere alt efter fejlen
                new Date(),
                ex.getMessage() + " xxxxxx", //giver javas egen exception desription med added xxxx for at se at vi selv kan ændre det
                request.getDescription(true)
        );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class) //bruger vores egen!
    ResponseEntity<ErrorMessage> resourceNotFoundException(Exception ex, WebRequest request){
        ErrorMessage message = new ErrorMessage( //laver instans af vores egen ErrorMessage klasse
                HttpStatus.NOT_FOUND.value(), //hardcoder en fejl 500, men vi kan gøre at det variere alt efter fejlen
                new Date(),
                ex.getMessage() + " yyyyyyy", //giver javas egen exception desription med added xxxx for at se at vi selv lan ændre det
                request.getDescription(true)
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    //hver endpoint kan smide en exception så her kan vi specificere hvad der skal ske ved en exception
    //vi gider ikke skrive exception handler kode i controllerne, derfor her

    //vi vil fange alle matematik fejl er:
    @ExceptionHandler(ArithmeticException.class) //man behøver ikke skrive public foran metoderne her da de kaldes automatisk
    ResponseEntity<ErrorMessage> globalArithmeticExceptionHandler(Exception ex, WebRequest request){
        //webrequest siger hvad for et endpoint der forårsagede exception, det er description:uri=/exp/div...
        // i alle metoder her laver vi RespnseEnttu af vores egen ErrorMessage. Bruger vores eget samme layout igennem
        ErrorMessage message = new ErrorMessage( //laver instans af vores egen ErrorMessage klasse
                HttpStatus.I_AM_A_TEAPOT.value(), //harcoder en fejl 500, men vi kan gøre at det variere alt efter fejlen
                new Date(),
                ex.getMessage() + " dividere med nul fx", //giver javas egen exception desription med added xxxx for at se at vi selv lan ændre det
                request.getDescription(true)
        );
        return new ResponseEntity<>(message, HttpStatus.I_AM_A_TEAPOT); //fjollet med teapot, det er bare for at gøre noget anderledes
    }//responeseentity body laves til json
    //i javascript kan man eventuelt hente repsonseentity ind og kigge på httpstatus og message

    //exception uden brug af egen exception. Returnerer bare en string
    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    ResponseEntity<String> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return new ResponseEntity<>(bodyOfResponse + "\n" + ex.getMessage(), HttpStatus.CONFLICT);
    }
}
