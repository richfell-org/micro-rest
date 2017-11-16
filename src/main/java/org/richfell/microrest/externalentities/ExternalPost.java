/*
 */

package org.richfell.microrest.externalentities;

/**
 *
 * @author Richard Fellinger rich@richfell.org
 */
public class ExternalPost
extends ExternalEntity
{
    /**
     * Creates an <code>ExternalPost</code>.
     */
    public ExternalPost()
    {
    }

    /**
     * 
     * @param id 
     */
    public ExternalPost(Long id)
    {
        super(id);
    }

    /** The ID of the user */
    private Long userId;

    /**
     * Gets the ID of the user whom made the post.
     * 
     * @return the user's ID
     */
    public Long getUserId()
    {
        return userId;
    }

    /**
     * Sets the ID of the user whom made the post.
     * 
     * @param userId the user's ID
     */
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    /** The title of the post */
    private String title;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    /** The body of the post */
    private String body;

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }
}
