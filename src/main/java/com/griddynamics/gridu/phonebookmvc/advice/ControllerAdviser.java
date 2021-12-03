package com.griddynamics.gridu.phonebookmvc.advice;

import com.griddynamics.gridu.phonebookmvc.exception.ContactAlreadyExistException;
import com.griddynamics.gridu.phonebookmvc.exception.ContactNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdviser {

    @ResponseBody
    @ExceptionHandler(ContactNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String contactNotFoundHandler(ContactNotFoundException e) {
        return String.format("Error 404: '%s'", e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ContactAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String contactAlreadyExistHandler(ContactAlreadyExistException e) {
        return String.format("Error 403: '%s'", e.getMessage());
    }
}