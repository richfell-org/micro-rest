
package org.richfell.microrest;

/**
 * A paragraph of text.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class Paragraph
{
    /**
     * Creates a <code>Paragraph</code> with the given text.
     * 
     * @param text the paragraph text
     */
    public Paragraph(String text)
    {
        this.text = text;
    }

    /**
     * Creates a <code>Paragraph</code> with no text.
     * 
     */
    public Paragraph()
    {
        this(null);
    }

    /** The text of the paragraph */
    private String text;

    /**
     * Gets the <code>Paragraph</code>'s text.
     * 
     * @return the paragraph text
     */
    public String getText()
    {
        return text;
    }

    /**
     * Sets the <code>Paragraph</code>'s text.
     * 
     * @param text the paragraph text
     */
    public void setText(String text)
    {
        this.text = text;
    }
}
