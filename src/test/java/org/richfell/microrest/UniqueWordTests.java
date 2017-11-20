
package org.richfell.microrest;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;

/**
 * Tests for the {@link org.richfell.microrest.UniqueWord} class.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class UniqueWordTests
{
    /**
     * Test the field setters and getters.
     */
    @Test
    public void getterAndSetterTests()
    {
        UniqueWord uniqueWord = new UniqueWord();

        // set/get the word
        uniqueWord.setWord("test");
        assertThat("test", is(equalTo(uniqueWord.getWord())));

        // set/get occurrence count
        uniqueWord.setOccurrences(42L);
        assertThat(42L, is(equalTo(uniqueWord.getOccurrences())));
    }

    /**
     * Test the equals method.
     */
    @Test
    public void equalsTest()
    {
        UniqueWord uniqueWord1 = new UniqueWord("one", 1L);
        UniqueWord uniqueWord2 = new UniqueWord("two", 1L);

        // equals self
        assertThat(uniqueWord1, equalTo(uniqueWord1));

        // equal by value
        assertThat(uniqueWord1, equalTo(new UniqueWord("one", 1L)));

        // not equal situations
        assertThat(uniqueWord2, not(equalTo(uniqueWord1)));
        assertThat(uniqueWord1, not(equalTo(null)));
        assertThat(uniqueWord1, not(equalTo(new Message())));
    }
}
