/*
 */

package org.richfell.microrest.externalentities;

/**
 *
 * @author Richard Fellinger rich@richfell.org
 */
public class ExternalComment
extends ExternalEntity
{
    public ExternalComment()
    {
    }

    public ExternalComment(Long id)
    {
        super(id);
    }

    /** The ID of the post to which this comment applies */
    private Long postId;

    /**
     * Gets the ID of the post to which the comment applies.
     * 
     * @return the post ID
     */
    public Long getPostId()
    {
        return postId;
    }

    /**
     * Sets the ID of the post to which the comment applies.
     * 
     * @param postId the post ID
     */
    public void setPostId(Long postId)
    {
        this.postId = postId;
    }

    /** The name/title of the comment */
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /** email address of the commenter */
    private String email;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    /** The body of the comment */
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
