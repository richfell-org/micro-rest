
package org.richfell.microrest.controllers;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.richfell.microrest.Paragraph;
import org.richfell.microrest.UniqueWord;
import org.richfell.microrest.util.RestPreconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Word count controller.
 * A REST endpoint that accepts a JSON object containing a paragraph of text and returns a JSON array of objects.
 * The returned objects represent the unique words from the supplied paragraph along with a count of the number
 * of occurrences.
 * The array of objects is sorted alphabetically.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@RestController
@RequestMapping("/words")
class WordCountController
{
    /** logger instance */
    static private final Logger LOGGER = LoggerFactory.getLogger(WordCountController.class);

    /**
     * Accepts a <code>Paragraph</code> and returns a collection of the unique words from the supplied
     * paragraph along with a count of the number of occurrences.
     * 
     * @param paragraph  the paragraph to analyze
     * @return a collection of <code>UniqueWord</code>s
     */
    @RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Collection<UniqueWord> getUniqueWords(@RequestBody Paragraph paragraph)
    {
        // validate the input
        RestPreconditions.checkContentNotNull(paragraph, "Paragraph object is required!");
        RestPreconditions.checkArgument(paragraph.getText() != null, "Missing paragraph text!");

        LOGGER.trace("Processing paragraph: {}", paragraph.getText());

        return countUniqueWords(paragraph);
    }

    /**
     * Finds the set of unique words used in the <code>paragraph</code> and the occurrence of each.
     * 
     * @param paragraph  the source of the paragraph text
     * @return a <code>UniqueWord</code> for each unique word in the paragraph
     */
    static private Collection<UniqueWord> countUniqueWords(Paragraph paragraph)
    {
        // map each word to its occurrence count
        Map<String, Long> wordMap = new java.util.HashMap<>();

        // replace all punctuation with spaces and then scan space delimited tokens
        java.util.Scanner wordScanner = new java.util.Scanner(
            paragraph.getText().replaceAll("[^\\p{L}]", " ")).useDelimiter("\\s+");

        // read each word and count the occurrences
        while(wordScanner.hasNext())
        {
            // get the next word
            String nextWord = wordScanner.next();

            // merge in the word, incrementing the count by one if it is already mapped
            wordMap.merge(nextWord.toLowerCase(), 1L, (count,inc) -> count + inc);
        }

        // create an SortedSet with UniqueWord instances
        Set<UniqueWord> wordSet = new java.util.TreeSet<>();
        for(Map.Entry<String, Long> e : wordMap.entrySet())
            wordSet.add(new UniqueWord(e.getKey(), e.getValue()));

        return wordSet;
    }
}
