
package org.richfell.microrest;

import java.util.Objects;

/**
 * A class to track a unique word from a paragraph of text along with its occurrence count.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class UniqueWord
implements Comparable<UniqueWord>
{
    /**
     * Creates a <code>UniqueWord</code> with <code>word</code> as the
     * unique word with <code>occurrences</code> occurrences.
     * 
     * @param word the unique work
     * @param occurrences the number of occurrences of the unique word
     */
    public UniqueWord(String word, Long occurrences)
    {
        this.word = word;
        this.occurrences = occurrences;
    }

    /**
     * Creates a <code>UniqueWord</code> with <code>word</code> as the
     * unique word and a zero occurrences count.
     * 
     * @param word the unique word
     */
    public UniqueWord(String word)
    {
        this(word, 0L);
    }

    /**
     * Creates a <code>UniqueWord</code> with an empty unique
     * word and a zero occurrences count.
     * 
     */
    public UniqueWord()
    {
        this(null);
    }

    /** The unique word */
    private String word;

    /**
     * Gets the unique word.
     * 
     * @return the unique word
     */
    public String getWord()
    {
        return word;
    }

    /**
     * Sets the unique word.
     * 
     * @param word  the unique word
     */
    public void setWord(String word)
    {
        this.word = word;
    }

    /** The number of occurrences of the unique word */
    private Long occurrences;

    /**
     * Gets the number of occurrences of the unique word.
     * 
     * @return the number of occurrences of the unique word
     */
    public Long getOccurrences()
    {
        return occurrences;
    }

    /**
     * Sets the number of occurrences of the unique word.
     * 
     * @param occurrences  the number of occurrences of the unique word
     */
    public void setOccurrences(Long occurrences)
    {
        this.occurrences = occurrences;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.word);
        hash = 53 * hash + Objects.hashCode(this.occurrences);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(obj == null)
            return false;

        if(getClass() != obj.getClass())
            return false;

        final UniqueWord other = (UniqueWord)obj;
        return Objects.equals(this.word, other.word) && Objects.equals(this.occurrences, other.occurrences);
    }

    @Override
    public int compareTo(UniqueWord o)
    {
        if(word == null)
            return o.word == null ? 0 : -1;

        if(o.word == null)
            return 1;

        int result = word.compareTo(o.word);
        if(result == 0)
            result = (int)(occurrences - o.occurrences);
        return result;
    }
}
