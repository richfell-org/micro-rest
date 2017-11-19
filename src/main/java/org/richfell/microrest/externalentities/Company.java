/*
 */

package org.richfell.microrest.externalentities;

/**
 * A company for JSONPlaceholder user entity compamy fields.
 * 
 *  https://jsonplaceholder.typicode.com/
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class Company
{
    /**
     * Creates a <code>Company</code>.
     */
    public Company()
    {
    }

    /** The company name */
    String name;

    /**
     * Gets the company's name.
     * 
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the company's name.
     * 
     * @param name the name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /** The company's catch phrase */
    String catchPhrase;

    /**
     * Gets the company's catch phrase.
     * 
     * @return the catch phrase
     */
    public String getCatchPhrase()
    {
        return catchPhrase;
    }

    /**
     * Sets the company's catch phrase.
     * 
     * @param catchPhrase the catch phrase
     */
    public void setCatchPhrase(String catchPhrase)
    {
        this.catchPhrase = catchPhrase;
    }

    /** the company's bs */
    String bs;

    /**
     * Gets the company's bs.
     * 
     * @return the bs
     */
    public String getBs()
    {
        return bs;
    }

    /**
     * Sets the company's bs.
     * 
     * @param bs the bs
     */
    public void setBs(String bs)
    {
        this.bs = bs;
    }
}
