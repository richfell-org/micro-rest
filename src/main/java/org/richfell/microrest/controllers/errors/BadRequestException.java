/*
 */

package org.richfell.microrest.controllers.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Error thrown for requests with incomplete or erroneous inputs.
 * 
 * @author Richard Fellinger <rich@richfell.org>
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException
extends RuntimeException
{
    /**
     * Creates a new instance of <code>BadRequestException</code> without detail message.
     */
    public BadRequestException()
    {
        super();
    }

    /**
     * 
     * @param message
     * @param cause 
     */
    public BadRequestException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Constructs an instance of <code>BadRequestException</code> with the specified detail message.
     * @param message the detail message.
     */
    public BadRequestException(final String message)
    {
        super(message);
    }

    /**
     * 
     * @param cause 
     */
    public BadRequestException(final Throwable cause)
    {
        super(cause);
    }
}
