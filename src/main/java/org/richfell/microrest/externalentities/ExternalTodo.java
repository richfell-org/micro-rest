/*
 */

package org.richfell.microrest.externalentities;

/**
 * A todo entity from JSONPlaceholder.
 * 
 *  https://jsonplaceholder.typicode.com/
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class ExternalTodo
extends ExternalEntity
{
    /**
     * Creates the <code>ExternalTodo</code>.
     */
    public ExternalTodo()
    {
    }

    /**
     * Creates the <code>ExternalTodo</code> with the given ID.
     * 
     * @param id the todo's ID
     */
    public ExternalTodo(Long id)
    {
        super(id);
    }

    /** The ID of the user */
    private Long userId;

    /**
     * Gets the ID of the user for this todo.
     * 
     * @return the user's ID
     */
    public Long getUserId()
    {
        return userId;
    }

    /**
     * Sets the ID of the user for this todo.
     * 
     * @param userId the user's ID
     */
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    /** The title of the todo */
    private String title;

    /**
     * Gets the todo's title.
     * 
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the todo's title.
     * 
     * @param title the title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /** The completed flag */
    private Boolean completed;

    /**
     * Gets the completed status of this todo.
     * 
     * @return the completed status
     */
    public Boolean isCompleted()
    {
        return completed;
    }

    /**
     * Sets the completed status of this todo.
     * 
     * @param completed the completed status
     */
    public void setCompleted(Boolean completed)
    {
        this.completed = completed;
    }
}
