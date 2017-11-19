
package org.richfell.microrest.util;

import org.richfell.microrest.controllers.errors.MissingContentException;
import org.richfell.microrest.controllers.errors.ResourceNotFoundException;

/**
 * Common checking and handling of conditions for REST services.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public final class RestPreconditions
{
    private RestPreconditions()
    {
        throw new AssertionError();
    }

    /**
     * Checks if the passed reference is not null.
     * If it is found to be null then an <code>IllegalArgumentException</code> is thrown.
     * 
     * @param <T>  the type of the reference being checked
     * @param reference  the instance reference to check
     * @return the value of <code>reference</code>
     * @throws IllegalArgumentException if <code>expression</code> is null
     */
    public static <T> T checkArgNotNull(T reference)
    throws IllegalArgumentException
    {
        if(reference == null)
          throw new IllegalArgumentException();

        return reference;
    }

    /**
     * Checks if the passed reference is not null.
     * If it is found to be null then an <code>IllegalArgumentException</code> is thrown.
     * 
     * @param <T>  the type of the reference being checked
     * @param reference  the instance reference to check
     * @param errorMessage  if needed, the message for the exception
     * @return the value of <code>reference</code>
     * @throws IllegalArgumentException if <code>expression</code> is null
     */
    public static <T> T checkArgNotNull(T reference, Object errorMessage)
    throws IllegalArgumentException
    {
        if(reference == null)
          throw new IllegalArgumentException(String.valueOf(errorMessage));

        return reference;
    }

    /**
     * Checks if the passed reference is not null.
     * If it is found to be null then an <code>IllegalArgumentException</code> is thrown.
     * 
     * @param <T>  the type of the reference being checked
     * @param reference  the instance reference to check
     * @param errorMessage  if needed, the message for the exception
     * @param msgArgs  the expansion values for the message template
     * @return the value of <code>reference</code>
     * @throws IllegalArgumentException if <code>expression</code> is null
     */
    public static <T> T checkArgNotNull(T reference, Object errorMessage, Object... msgArgs)
    throws IllegalArgumentException
    {
        if(reference == null)
          throw new IllegalArgumentException(buildMessage(errorMessage, msgArgs));

        return reference;
    }

    /**
     * 
     * @param expression 
     * @throws IllegalArgumentException if <code>expression</code> is false
     */
    public static void checkArgument(boolean expression)
    throws IllegalArgumentException
    {
        if(!expression)
            throw new IllegalArgumentException();
    }

    /**
     * 
     * @param expression  a boolean expression
     * @param errorMessage  if needed, the exception message
     * @throws IllegalArgumentException if <code>expression</code> is false
     */
    public static void checkArgument(boolean expression, Object errorMessage)
    throws IllegalArgumentException
    {
        if(!expression)
            throw new IllegalArgumentException(String.valueOf(errorMessage));
    }

    /**
     * 
     * @param expression  a boolean expression
     * @param errorMessage  if needed, the exception message template
     * @param msgArgs  the expansion values for the message template
     * @throws IllegalArgumentException if <code>expression</code> is false
     */
    public static void checkArgument(boolean expression, Object errorMessage, Object... msgArgs)
    throws IllegalArgumentException
    {
        if(!expression)
            throw new IllegalArgumentException(buildMessage(errorMessage, msgArgs));
    }

    /**
     * Checks that the given value is not null.
     * If it is found to be null then an <code>MissingContentException</code> is thrown.
     * 
     * @param <T>  the type of the given reference
     * @param reference  
     * @return the <code>reference</code>
     * @throws MissingContentException if <code>reference</code> is null
     */
    static public <T> T checkContentNotNull(T reference)
    throws MissingContentException
    {
        if(reference == null)
            throw new MissingContentException();

        return reference;
    }

    /**
     * Checks that the given value is not null.
     * If it is found to be null then an <code>MissingContentException</code> is thrown.
     * 
     * @param <T>  the type of the given reference
     * @param reference  
     * @param errorMessage  if needed, the exception message template
     * @return the <code>reference</code>
     * @throws MissingContentException if <code>reference</code> is null
     */
    static public <T> T checkContentNotNull(T reference, Object errorMessage)
    throws MissingContentException
    {
        if(reference == null)
            throw new MissingContentException(String.valueOf(errorMessage));

        return reference;
    }

    /**
     * Checks that the given value is not null.
     * If it is found to be null then an <code>MissingContentException</code> is thrown.
     * 
     * @param <T>  the type of the given reference
     * @param reference  
     * @param errorMessage  if needed, the exception message template
     * @param msgArgs  the expansion values for the message template
     * @return the <code>reference</code>
     * @throws MissingContentException if <code>reference</code> is null
     */
    static public <T> T checkContentNotNull(T reference, Object errorMessage, Object... msgArgs)
    throws MissingContentException
    {
        if(reference == null)
            throw new MissingContentException(buildMessage(errorMessage, msgArgs));

        return reference;
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
     * @param message  if needed, the message for the exception
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
     * @param message  if needed, the message template for the exception
     * @param msgArgs  expansion values for the message template
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
