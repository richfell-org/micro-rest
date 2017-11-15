/*
 */

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
     * 
     * @param expression 
     */
    static public void checkFound(final boolean expression)
    {
        if(!expression)
            throw new ResourceNotFoundException();
    }

    /**
     * 
     * @param expression
     * @param message 
     */
    static public void checkFound(final boolean expression, Object message)
    {
        if(!expression)
            throw new ResourceNotFoundException(String.valueOf(message));
    }

    /**
     * 
     * @param <T>
     * @param resource
     * @return 
     */
    static public <T> T checkFound(final T resource)
    {
        if(resource == null)
            throw new ResourceNotFoundException();

        return resource;
    }

    /**
     * 
     * @param <T>
     * @param resource
     * @param message
     * @return 
     */
    static public <T> T checkFound(final T resource, Object message)
    {
        if(resource == null)
            throw new ResourceNotFoundException(String.valueOf(message));

        return resource;
    }
}
