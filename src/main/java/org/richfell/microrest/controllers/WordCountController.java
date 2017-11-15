/*
 */

package org.richfell.microrest.controllers;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.richfell.microrest.Paragraph;
import org.richfell.microrest.UniqueWord;
import org.richfell.microrest.controllers.errors.BadRequestException;
import org.richfell.microrest.util.Preconditions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Word count controller.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@RestController
@RequestMapping("/words")
public class WordCountController
{
    /**
     * Accepts a <code>Paragraph</code> and returns a collection of the unique words from the supplied
     * paragraph along with a count of the number of occurrences.
     * 
     * @param paragraph the paragraph to analyze
     * @return a collection of <code>UniqueWord</code>s
     */
    @RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Collection<UniqueWord> getUniqueWords(@RequestBody Paragraph paragraph)
    {
        Preconditions.checkNotNull(paragraph, BadRequestException.class);
        Preconditions.checkNotNull(paragraph.getText(), BadRequestException.class);
        return countUniqueWords(paragraph);
    }

    /**
     * 
     * @param paragraph
     * @return 
     */
    static private Collection<UniqueWord> countUniqueWords(Paragraph paragraph)
    {
        // map each word to its occurrence count
        Map<String, Long> wordMap = new java.util.HashMap<>();

        // replace all punctuation with spaces and then scan space delimited tokens
        java.util.Scanner wordScanner = new java.util.Scanner(
            paragraph.getText().replaceAll("[^\\p{L}]", " ")).useDelimiter(" +");

        // read each word and count the occurrences
        while(wordScanner.hasNext())
        {
            // get the next word
            String nextWord = wordScanner.next();

            // merge in the word, incrementing the count by one if it is already mapped
            wordMap.merge(nextWord.toLowerCase(), 1L, (a,b) -> a + b);
        }

        // create an SortedSet with UniqueWord instances
        Set<UniqueWord> wordSet = new java.util.TreeSet<UniqueWord>();
        for(Map.Entry<String, Long> e : wordMap.entrySet())
            wordSet.add(new UniqueWord(e.getKey(), e.getValue()));

        return wordSet;
    }
}
