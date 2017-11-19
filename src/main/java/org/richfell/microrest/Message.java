
package org.richfell.microrest;

/**
 * A simple message class.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class Message
{
    /**
     * Allocates a <code>Message</code> with the content set to the
     * result of the formatting operation.
     * 
     * @param format the format for the message
     * @param args the values to expand into the <code>format</code>'s place holders
     * @return a <code>Message</code> instance with the content set to formatted string
     */
    static public Message fromFormat(String format, Object... args)
    {
        return new Message(String.format(format, args));
    }

    /**
     * Creates a <code>Message</code> with the given content.
     * 
     * @param content the message content
     */
    public Message(String content)
    {
        this.content = content;
    }

    /**
     * Creates a <code>Message</code> with empty content.
     */
    public Message()
    {
        this("");
    }

    /** The message content */
    private String content;

    /**
     * Gets the message content.
     * 
     * @return the message content
     */
    public String getContent()
    {
        return content;
    }

    /**
     * Sets the message content.
     * 
     * @param content the message content
     */
    public void setContent(String content)
    {
        this.content = content;
    }
}
