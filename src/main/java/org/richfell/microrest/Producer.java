
package org.richfell.microrest;

/**
 * A producer class which is used in the "create 2 threads" REST endpoint.
 * 
 * It produces integer values through its <code>produce</code> method by
 * applying a delta to an initial value.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class Producer
{
    /**
     * Creates a <code>Producer</code> with an initial value and a delta.
     * 
     * @param initialValue  the initial value to produce
     * @param delta  the amount to adjust the value after producing a value
     */
    public Producer(int initialValue, int delta)
    {
        value = initialValue;
        this.delta = delta;
    }

    /**
     * Creates a <code>Producer</code> with an initial value and a delta of 1.
     * 
     * @param initialValue  the initial value to produce
     */
    public Producer(int initialValue)
    {
        this(initialValue, 1);
    }

    /**
     * Creates a <code>Producer</code>.  It will have an initial value of 0 and a delta of 1.
     * 
     */
    public Producer()
    {
        this(0, 1);
    }

    /** The current value */
    private int value;

    /**
     * Produces the current value.  The current value is then adjusted by the delta.
     * 
     * @return the current value
     */
    public int produce()
    {
        int result = value;
        value += delta;
        return result;
    }

    /** The amount to adjust the value after each call to produce  */
    private final int delta;

    /**
     * Gets the delta.
     * 
     * @return the delta
     */
    public int getDelta()
    {
        return delta;
    }
}
