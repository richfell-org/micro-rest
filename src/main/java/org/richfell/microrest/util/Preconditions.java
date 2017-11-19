
package org.richfell.microrest.util;

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
     * @param expression a boolean expression
     * @param errorMessage The exception message to use
     */
    public static void checkArgument(boolean expression, Object errorMessage, Object... msgArgs)
    {
        if(!expression)
            throw new IllegalArgumentException(buildMessage(errorMessage, msgArgs));
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
     * @param reference
     * @param errorMessage
     * @return 
     */
    public static <T> T checkNotNull(T reference, Object errorMessage, Object... msgArgs)
    {
        if(reference == null)
          throw new NullPointerException(buildMessage(errorMessage, msgArgs));

        return reference;
    }

    /**
     * 
     * @param fmt
     * @param args
     * @return 
     */
    static private String buildMessage(Object fmt, Object[] args)
    {
        return String.format(String.valueOf(fmt), args);
    }
}