package com.diep.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class provides global exception handling for the application.
 * Account: DiepDP1
 * Birthday: 1998-02-27
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * Handles exceptions of type {@link Exception} and returns a ModelAndView for 500 internal server errors.
	 * 
	 * Account: DiepDP1
	 * Birthday: 1998-02-27
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
    public ModelAndView handle500Error(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error500"); 
        modelAndView.addObject("errorMessage", "An internal server error occurred.");
        return modelAndView;
    }
}
