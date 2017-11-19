/*
 */

package org.richfell.microrest.externalentities;

/**
 * A class for JSONPlaceholder album entities.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class ExternalAlbum
extends ExternalEntity
{
    /**
     * Creates a <code>ExternalAlbum</code>.
     */
    public ExternalAlbum()
    {
    }

    /**
     * Creates a <code>ExternalAlbum</code> with the given ID.
     * 
     * @param id  the ID for the album
     */
    public ExternalAlbum(Long id)
    {
        super(id);
    }

    /** The ID of the user */
    private Long userId;

    /**
     * Gets the ID of the user whom made the post.
     * 
     * @return  the user's ID
     */
    public Long getUserId()
    {
        return userId;
    }

    /**
     * Sets the ID of the user whom made the post.
     * 
     * @param userId  the user's ID
     */
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    /** The title of the album */
    private String title;

    /**
     * Gets the album title.
     * 
     * @return  the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the album title.
     * 
     * @param title  the title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
}
