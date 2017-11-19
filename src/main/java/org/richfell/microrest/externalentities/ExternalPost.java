
package org.richfell.microrest.externalentities;

/**
 * A class for JSONPlaceholder post entities.
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
     * Creates an <code>ExternalPost</code> and sets the ID to the given value.
     * 
     * @param id  the post ID
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
     * @param userId  the user's ID
     */
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    /** The title of the post */
    private String title;

    /**
     * Gets the title of the post.
     * 
     * @return the post title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the title of the post.
     * 
     * @param title  the post title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /** The body of the post */
    private String body;

    /**
     * Gets the body of the post.
     * 
     * @return the post body
     */
    public String getBody()
    {
        return body;
    }

    /**
     * Sets the body of the post.
     * 
     * @param body  the post body
     */
    public void setBody(String body)
    {
        this.body = body;
    }
}
