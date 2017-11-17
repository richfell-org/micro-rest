/*
 */

package org.richfell.microrest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Richard Fellinger rich@richfell.org
 */
@Entity
@Table(name="sayings")
@NamedQueries({
    @NamedQuery(name="sayings.quote.contains", query="from Saying s where s.quote like CONCAT('%', ?1, '%')")
})
public class Saying
implements java.io.Serializable
{
    static final long serialVersionUID = 42L;

    public Saying()
    {
    }

    public Saying(Integer id)
    {
        this.id = id;
    }

    public Saying(Integer id, String name, String quote)
    {
        this(id);
        this.name = name;
        this.quote = quote;
    }

    /** The DB table record ID */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    /**
     * Gets the record ID.
     * 
     * @return the record ID
     */
    public Integer getId()
    {
        return id;
    }

    /***
     * Sets the record ID.
     * 
     * @param id the record ID
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /** The name of the person */
    @Column
    private String name;

    /**
     * Gets the person's name.
     * 
     * @return the name of the person
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the person.
     * 
     * @param name the name of the person
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /** The quote */
    @Column
    private String quote;

    /**
     * Gets the quote.
     * 
     * @return the quote
     */
    public String getQuote()
    {
        return quote;
    }

    /**
     * Sets the quote.
     * 
     * @param quote the quote
     */
    public void setQuote(String quote)
    {
        this.quote = quote;
    }
}
