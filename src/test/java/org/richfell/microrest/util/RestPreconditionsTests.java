
package org.richfell.microrest.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.richfell.microrest.Paragraph;
import org.richfell.microrest.controllers.errors.MissingContentException;
import org.richfell.microrest.controllers.errors.ResourceNotFoundException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

/**
 * Test for the {@link org.richfell.microrest.util.RestPreconditions} class.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class RestPreconditionsTests
{
    /** rule for checking expected exceptions */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test for an <code>IllegalArgumentException</code> when <code>checkArgument</code>
     * is called with a false expression value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkArgumentsTest_FalseExpression()
    {
        RestPreconditions.checkArgument(false);
    }

    /**
     * Test for an <code>IllegalArgumentException</code> when <code>checkArgNotNull</code>
     * is called with a null reference value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkArgumentsTest_NullReference()
    {
        Paragraph paragraph = null;
        RestPreconditions.checkArgNotNull(paragraph);
    }

    /**
     * Test for the reference resulting from <code>checkArgNotNull</code> is called with a non-null
     * reference value.
     */
    @Test
    public void checkArgumentsTest_ValidReference()
    {
        Paragraph paragraph = new Paragraph("content");
        assertThat(paragraph, is(equalTo(RestPreconditions.checkArgNotNull(paragraph))));
    }

    /**
     * Test for an <code>MissingContentException</code> when <code>checkContentNotNull</code>
     * is called with a null reference value.
     */
    @Test(expected = MissingContentException.class)
    public void checkContentTest_NullReference()
    {
        Paragraph paragraph = null;
        RestPreconditions.checkContentNotNull(paragraph);
    }

    /**
     * Test for the reference resulting from <code>checkContentNotNull</code> is called with a non-null
     * reference value.
     */
    @Test
    public void checkContentWithMessageTest_ValidReference()
    {
        Paragraph paragraph = new Paragraph("content");
        assertThat(paragraph, is(equalTo(RestPreconditions.checkContentNotNull(paragraph, "error message"))));
    }

    /**
     * Test for an <code>ResourceNotFoundException</code> when <code>checkFound</code>
     * is called with a false expression.
     */
    @Test(expected = ResourceNotFoundException.class)
    public void checkFoundTest_FalseExpression()
    {
        RestPreconditions.checkFound(false);
    }

    /**
     * Test for an <code>ResourceNotFoundException</code>, with the given message, when <code>checkFound</code>
     * is called with a false expression.
     */
    @Test
    public void checkFoundWithMessageTest_FalseExpression()
    {
        String errorMsg = "Error message";

        thrown.expect(ResourceNotFoundException.class);
        thrown.expectMessage(errorMsg);

        RestPreconditions.checkFound(false, errorMsg);
    }

    /**
     * Test for an <code>ResourceNotFoundException</code> when <code>checkFound</code>
     * is called with a null reference value.
     */
    @Test(expected = ResourceNotFoundException.class)
    public void checkFoundTest_NullReference()
    {
        Paragraph paragraph = null;
        RestPreconditions.checkFound(paragraph);
    }

    /**
     * Test for an <code>ResourceNotFoundException</code> when <code>checkFound</code>, with a message,
     * is called with a null reference value.
     */
    @Test
    public void checkFoundWithMessageTest_NullReference()
    {
        thrown.expect(ResourceNotFoundException.class);
        thrown.expectMessage("Error message");

        Paragraph paragraph = null;
        RestPreconditions.checkFound(paragraph, "Error message");
    }

    /**
     * Test for an <code>ResourceNotFoundException</code> when <code>checkFound</code>
     * is called with a null reference value.
     */
    @Test
    public void checkFoundWithMessageArgsTest_NullReference()
    {
        thrown.expect(ResourceNotFoundException.class);
        thrown.expectMessage("Error message");

        Paragraph paragraph = null;
        RestPreconditions.checkFound(paragraph, "Error %s", "message");
    }
}
