/*
 */

package org.richfell.microrest.controllers.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception for REST resources which are not found.  Will cause and
 * HTTP status of NOT FOUND when thrown during the processing of an HTTP
 * request.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException
extends RuntimeException
{
    /**
     * Creates a <code>ResourceNotFoundException</code> without detail message.
     */
    public ResourceNotFoundException()
    {
        super();
    }

    /**
     * Creates a <code>ResourceNotFoundException</code> with a detail message and cause.
     * 
     * @param message the message for the exception
     * @param cause the exception which is a root cause
     */
    public ResourceNotFoundException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Constructs a <code>ResourceNotFoundException</code> with the specified detail message.
     * 
     * @param message the detail message.
     */
    public ResourceNotFoundException(final String message)
    {
        super(message);
    }

    /**
     * Creates a <code>ResourceNotFoundException</code> with a cause.
     * 
     * @param cause 
     */
    public ResourceNotFoundException(final Throwable cause)
    {
        super(cause);
    }
}
