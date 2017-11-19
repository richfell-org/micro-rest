
package org.richfell.microrest.controllers.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handling for the REST API errors.  This enables errors to
 * be returned to the API callers in a common JSON.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MicrorestExceptionHandler
extends ResponseEntityExceptionHandler
{
    /** logger instance */
    static private final Logger LOGGER = LoggerFactory.getLogger(MicrorestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        LOGGER.error("Input not readable for {}", request.getDescription(false));
        LOGGER.debug("Caused by {}", ex.getClass().getSimpleName(), ex);
        return buildApiErrorEntity(new RestApiError(HttpStatus.BAD_REQUEST, "Invalid JSON data", ex));
    }

    /**
     * Handles response for entity not found.
     * 
     * @param ex the exception thrown from the REST API
     * @param request the request
     * @return the response entity for a <code>RestApiError</code>
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, ServletWebRequest request)
    {
        LOGGER.error(
            "[{} {}]: Entity not found - {}",
            request.getHttpMethod(), request.getDescription(false), ex.getLocalizedMessage());

        LOGGER.debug("Caused by {}", ex.getClass().getSimpleName(), ex);
        return buildApiErrorEntity(new RestApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex));
    }

    /**
     * 
     * @param ex
     * @param request
     * @return 
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, ServletWebRequest request)
    {
        LOGGER.error(
            "[{} {}]: illegal argument - {}",
            request.getHttpMethod(), request.getDescription(false), ex.getLocalizedMessage());

        LOGGER.debug("Caused by {}", ex.getClass().getSimpleName(), ex);
        return buildApiErrorEntity(new RestApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex));
    }

    /**
     * General handler for all other exceptions not explicitly handled.
     * 
     * @param ex the exception
     * @param request the request being handled when the exception occured
     * @return the response entity for a <code>RestApiError</code>
     */
    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> handleThrowable(Throwable ex, ServletWebRequest request)
    {
        LOGGER.error("[{} {}]: {}", request.getHttpMethod(), request.getDescription(false), ex.getMessage());
        LOGGER.debug("Caused by {}", ex.getClass().getSimpleName(), ex);
        return buildApiErrorEntity(new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex));
    }

    /**
     * Builds a <code>ResponseEntity</code> from an <code>RestApiError</code>.
     * 
     * @param error the <code>RestApiError</code>
     * @return a <code>ResponseEntity</code> for the given <code>error</code>
     */
    private ResponseEntity<Object> buildApiErrorEntity(RestApiError error)
    {
        return new ResponseEntity<>(error, error.getStatus());
    }
}
