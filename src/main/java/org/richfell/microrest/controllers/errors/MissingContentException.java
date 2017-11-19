
package org.richfell.microrest.controllers.errors;

/**
 * Exception when REST API did not receive a required content in the request body.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class MissingContentException
extends RuntimeException
{
    public MissingContentException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MissingContentException(String message)
    {
        this(message, null);
    }

    public MissingContentException(Throwable cause)
    {
        this(null, cause);
    }

    public MissingContentException()
    {
        super();
    }
}
