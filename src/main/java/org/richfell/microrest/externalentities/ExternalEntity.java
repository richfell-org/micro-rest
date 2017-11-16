/*
 */

package org.richfell.microrest.externalentities;

/**
 * Base class for the entities found at JSONPlaceholder
 * 
 *  https://jsonplaceholder.typicode.com
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class ExternalEntity
{
    /**
     * Creates a <code>ExternalEntity</code>.
     */
    protected ExternalEntity()
    {
    }

    /**
     * Creates a <code>ExternalEntity</code> with the given ID value.
     * 
     * @param id the entity ID
     */
    protected ExternalEntity(Long id)
    {
        this.id = id;
    }

    /** The entity ID */
    private Long id;

    /**
     * Gets the entity ID.
     * 
     * @return the ID
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Sets the entity's ID.
     * 
     * @param id the ID
     */
    public void setId(Long id)
    {
        this.id = id;
    }
}
