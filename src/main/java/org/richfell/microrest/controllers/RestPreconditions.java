
package org.richfell.microrest.controllers;

import org.richfell.microrest.controllers.errors.ResourceNotFoundException;

/**
 * Common checking and handling of conditions for REST services.
 * 
 * @author Richard Fellinger <rich@richfell.org>
 */
public final class RestPreconditions
{
    private RestPreconditions()
    {
        throw new AssertionError();
    }

    /**
     * Throws a <code>ResourceNotFoundException</code> exception if the given
     * expression is false.
     * 
     * @param expression  the boolean result to verify
     * @throws ResourceNotFoundException if expression is false
     */
    static public void checkFound(final boolean expression)
    throws ResourceNotFoundException
    {
        if(!expression)
            throw new ResourceNotFoundException();
    }

    /**
     * Throws a <code>ResourceNotFoundException</code> exception if <code>expression</code> is false.
     * The <code>message</code> is used for the exception message if needed.
     * 
     * @param expression  the boolean result to verify
     * @param message  the error message for the exception
     * @throws ResourceNotFoundException if expression is false
     */
    static public void checkFound(final boolean expression, Object message)
    throws ResourceNotFoundException
    {
        if(!expression)
            throw new ResourceNotFoundException(String.valueOf(message));
    }

    /**
     * Throws a <code>ResourceNotFoundException</code> exception if <code>expression</code> is false.
     * 
     * @param expression  the boolean result to verify
     * @param message  the error message for the exception
     * @param msgArgs  the template expansion values for message
     * @throws ResourceNotFoundException if expression is false
     */
    static public void checkFound(final boolean expression, Object message, Object... msgArgs)
    throws ResourceNotFoundException
    {
        if(!expression)
            throw new ResourceNotFoundException(buildMessage(message, msgArgs));
    }

    /**
     * Verifies that <code>resource</code> is not null.
     * Throws a <code>ResourceNotFoundException</code> exception if <code>resource</code> is null.
     * 
     * @param <T>  the type of the given resource
     * @param resource  the resource instance, may be null
     * @return the <code>resource</code> passed in
     * @throws ResourceNotFoundException if the resource is null
     */
    static public <T> T checkFound(final T resource)
    throws ResourceNotFoundException
    {
        if(resource == null)
            throw new ResourceNotFoundException();

        return resource;
    }

    /**
     * Verifies that <code>resource</code> is not null.
     * 
     * @param <T>  the type of the given resource
     * @param resource  the resource instance, may be null
     * @param message
     * @return the <code>resource</code> passed in
     * @throws ResourceNotFoundException if the resource is null
     */
    static public <T> T checkFound(final T resource, Object message)
    throws ResourceNotFoundException
    {
        if(resource == null)
            throw new ResourceNotFoundException(String.valueOf(message));

        return resource;
    }

    /**
     * Verifies that <code>resource</code> is not null.
     * 
     * @param <T>  the type of the given resource
     * @param resource  the resource instance, may be null
     * @param message
     * @param msgArgs
     * @return the <code>resource</code> passed in
     * @throws ResourceNotFoundException if the resource is null
     */
    static public <T> T checkFound(final T resource, Object message, Object... msgArgs)
    throws ResourceNotFoundException
    {
        if(resource == null)
            throw new ResourceNotFoundException(buildMessage(message, msgArgs));

        return resource;
    }

    /**
     * Builds a message from a template and values used for expansion of placehoders in the template.
     * 
     * @param fmt  the message template
     * @param args  the values for parameter expansion
     * @return the formatted message
     */
    static private String buildMessage(Object fmt, Object[] args)
    {
        return String.format(String.valueOf(fmt), args);
    }
}
