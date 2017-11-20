package org.richfell.microrest;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

/**
 * Tests for the @{link org.richfell.microrest.Message} class.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class MessageTests
{
    /**
     * Tests that the message text set with <code>setContent</code> is returned
     * by <code>getContent</code>.
     * 
     */
	@Test
	public void setGetContentTest()
	{
		String text = "This is the text";
		Message message = new Message();
		message.setContent(text);
		assertThat(text, is(equalTo(message.getContent())));
	}

    /**
     * Tests that creating a <code>Message</code> from the static method <code>fromFormat</code>
     * results in a <code>Message</code> with the expected content.
     */
    @Test
    public void fromFormatTest()
    {
        Message message = Message.fromFormat("This is a %s message, %d", "formatted", 1);
        assertThat("This is a formatted message, 1", is(equalTo(message.getContent())));
    }
}

