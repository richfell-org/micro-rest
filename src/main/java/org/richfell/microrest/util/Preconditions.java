/*
 */

package org.richfell.microrest.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Richard Fellinger <rich@richfell.org>
 */
public final class Preconditions
{
    private Preconditions() {}

    /**
     * 
     * @param expression 
     */
    public static void checkArgument(boolean expression)
    {
        if(!expression)
            throw new IllegalArgumentException();
    }

    /**
     * 
     * @param expression a boolean expression
     * @param errorMessage The exception message to use
     */
    public static void checkArgument(boolean expression, Object errorMessage)
    {
        if(!expression)
            throw new IllegalArgumentException(String.valueOf(errorMessage));
    }

    /**
     * 
     * @param <T>
     * @param reference
     * @return 
     */
    public static <T> T checkNotNull(T reference)
    {
        if(reference == null)
          throw new NullPointerException();

        return reference;
    }

    /**
     * 
     * @param <T>
     * @param reference
     * @param errorMessage
     * @return 
     */
    public static <T> T checkNotNull(T reference, Object errorMessage)
    {
        if(reference == null)
          throw new NullPointerException(String.valueOf(errorMessage));

        return reference;
    }

    /**
     * 
     * @param <T>
     * @param <E>
     * @param reference
     * @param errorClass
     * @return 
     */
    public static <T, E extends RuntimeException> T checkNotNull(T reference, Class<E> errorClass)
    {
        if(reference == null)
            throw errorInstance(errorClass);

        return reference;
    }

    /**
     * 
     * @param <T>
     * @param <E>
     * @param reference
     * @param errorMessage
     * @param errorClass
     * @return 
     */
    public static <T, E extends RuntimeException> T checkNotNull(
        T reference,
        Object errorMessage,
        Class<E> errorClass)
    {
        if(reference == null)
            throw errorInstance(errorClass, errorMessage);

        return reference;
    }

    /**
     * 
     * @param <E>
     * @param clazz
     * @return 
     */
    static private <E extends RuntimeException> RuntimeException errorInstance(Class<E> clazz)
    {
        try { return clazz.newInstance(); }
        catch(InstantiationException | IllegalAccessException e)
        {
            return new NullPointerException();
        }
    }

    /**
     * 
     * @param <E>
     * @param clazz
     * @param errorMessage
     * @return 
     */
    static private <E extends RuntimeException> RuntimeException errorInstance(Class<E> clazz, Object errorMessage)
    {
        try
        {
            Constructor<E> ctor = clazz.getDeclaredConstructor(String.class);
            return ctor.newInstance(String.valueOf(errorMessage));
        }
        catch(InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
            return new NullPointerException();
        }
    }
}