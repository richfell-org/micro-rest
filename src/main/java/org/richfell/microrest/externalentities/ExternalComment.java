/*
 */

package org.richfell.microrest.externalentities;

/**
 * A class for comment entities from JSONPlaceholder.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class ExternalComment
extends ExternalEntity
{
    /**
     * Creates an <code>ExternalComment</code>.
     */
    public ExternalComment()
    {
    }

    /**
     * Creates an <code>ExternalComment</code> setting this ID to the given value.
     * 
     * @param id  the ID for the comment
     */
    public ExternalComment(Long id)
    {
        super(id);
    }

    /** The ID of the post to which this comment applies */
    private Long postId;

    /**
     * Gets the ID of the post to which the comment applies.
     * 
     * @return  the post ID
     */
    public Long getPostId()
    {
        return postId;
    }

    /**
     * Sets the ID of the post to which the comment applies.
     * 
     * @param postId  the post ID
     */
    public void setPostId(Long postId)
    {
        this.postId = postId;
    }

    /** The name/title of the comment */
    private String name;

    /**
     * Gets the commenter name.
     * 
     * @return  the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the commenter name.
     * 
     * @param name  the name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /** email address of the commenter */
    private String email;

    /**
     * Gets the commenter email address.
     * 
     * @return the email address
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets the commenter email address.
     * 
     * @param email  the email address
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /** The body of the comment */
    private String body;

    /**
     * Gets the content of the comment.
     * 
     * @return the content body
     */
    public String getBody()
    {
        return body;
    }

    /**
     * Sets the content of the comment.
     * 
     * @param body  the comment body
     */
    public void setBody(String body)
    {
        this.body = body;
    }
}
