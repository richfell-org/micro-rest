/*
 */

package org.richfell.microrest.controllers;

import java.util.Collection;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.richfell.microrest.Paragraph;
import org.richfell.microrest.UniqueWord;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Richard Fellinger rich@richfell.org
 */
@SpringBootTest
@RunWith(JMockit.class)
public class WordCountControllerTests
{
    @Tested
    WordCountController wordCountController;

    Collection<UniqueWord> paragraphWords;
    Collection<UniqueWord> paragraphWordsUnicode;

    private MockMvc mockMvc;

    private MockMvc mockMvc()
    {
        if(mockMvc == null)
            mockMvc = MockMvcBuilders.standaloneSetup(wordCountController).build();
        return mockMvc;
    }

    @Before
    public void setUp()
    {
        paragraphWords = new java.util.TreeSet<>();
        paragraphWords.add(new UniqueWord("this", 2L));
        paragraphWords.add(new UniqueWord("is", 2L));
        paragraphWords.add(new UniqueWord("a", 2L));
        paragraphWords.add(new UniqueWord("paragraph", 2L));
        paragraphWords.add(new UniqueWord("thank", 1L));
        paragraphWords.add(new UniqueWord("you", 1L));
        paragraphWords.add(new UniqueWord("for", 1L));
        paragraphWords.add(new UniqueWord("your", 1L));
        paragraphWords.add(new UniqueWord("attention", 1L));

        paragraphWordsUnicode = new java.util.TreeSet<>();
        paragraphWordsUnicode.add(new UniqueWord("Σὲ", 1L));
        paragraphWordsUnicode.add(new UniqueWord("γνωρίζω", 1L));
        paragraphWordsUnicode.add(new UniqueWord("ἀπὸ", 1L));
        paragraphWordsUnicode.add(new UniqueWord("τὴν", 1L));
        paragraphWordsUnicode.add(new UniqueWord("κόψη", 1L));
    }

    @Test
    public void paragraphTest()
    {
        Paragraph paragraph = new Paragraph("This is a paragraph. This is a paragraph. Thank you for your attention.");
        Collection<UniqueWord> words = wordCountController.getUniqueWords(paragraph);
        Assert.assertEquals(paragraphWords, words);
    }

    /*
    @Test
    public void paragraphTestUnicode()
    {
        Paragraph paragraph = new Paragraph("Σὲ γνωρίζω, ἀπὸ τὴν. κόψη");
        Collection<UniqueWord> words = wordCountController.getUniqueWords(paragraph);
        Assert.assertEquals(paragraphWordsUnicode, words);
    }
    */

    @Test
    public void mvcParagraphTest()
    throws Exception
    {
        mockMvc()
            .perform(
                post("/words")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    .content("{ \"text\": \"This is a paragraph. Have a nice day.\" }"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(7)))
            .andExpect(jsonPath("$.[*]word", contains("a", "day", "have", "is", "nice", "paragraph", "this")))
            .andExpect(jsonPath("$.[*].occurrences", contains(2, 1, 1, 1, 1, 1, 1)));
    }
}
