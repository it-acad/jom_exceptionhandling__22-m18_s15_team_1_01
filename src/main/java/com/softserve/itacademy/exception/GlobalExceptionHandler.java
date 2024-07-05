package com.softserve.itacademy.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView handleEntityNotFoundException(EntityNotFoundException exception) {
        return getModelAndView(HttpStatus.NOT_FOUND, exception, "not-found");
    }

    private ModelAndView getModelAndView(HttpStatus httpStatus, Exception exception, String viewName) {
        logger.error("An exception '{}' was raised", exception.getMessage());
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("code", httpStatus.value());
        modelAndView.addObject("phrase", httpStatus.getReasonPhrase());
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }
}