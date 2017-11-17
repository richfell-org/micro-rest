/*
 */

package org.richfell.microrest.controllers;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Common controller configuration and error handling for
 * microrest controllers.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@ControllerAdvice
public class MicrorestControllerAdvice
{
    /** logger instance */
    static Logger LOGGER = LoggerFactory.getLogger(MicrorestControllerAdvice.class);

    /**
     * Handle <code>IllegalArgumentException</code>s.
     * 
     * @param request the HTTP request being handled when the exception was thrown
     * @param e the exception that was thrown
     */
    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class })
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid or missing parameter")
    public void handle(HttpServletRequest request, Throwable e)
    {
        LOGGER.error("Illegal argument error for request {}", request.getRequestURL());
        LOGGER.trace("Stack trace", e);
    }

    /**
     * Handler <code>DataIntegrityViolationException</code>.
     * 
     * @param request the HTTP request being handled when the exception was thrown
     * @param e the exception that was thrown
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid data")
    public void handleDataIntegrity(HttpServletRequest request, Throwable e)
    {
        LOGGER.error("DataIntegrityViolationException for request {} - {}", request.getRequestURL(), e.getLocalizedMessage());
        LOGGER.trace("Stack trace", e);
    }
}
