
package org.richfell.microrest.controllers;

import mockit.Tested;
import mockit.integration.junit4.JMockit;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the /words REST controller.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@SpringBootTest
@RunWith(JMockit.class)
public class WordCountControllerTests
{
    /** The controller under test */
    @Tested(availableDuringSetup = true)
    WordCountController wordCountController;

    /** Spring MVC mock */
    private MockMvc mockMvc;

    /**
     * Setup for the tests.
     */
    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(wordCountController).build();
    }

    /**
     * Tests using Middle High German using UTF-8.
     * 
     * @throws Exception if MockMvc throws
     */
    @Test
    public void paragraphTestUnicode()
    throws Exception
    {
        mockMvc
            .perform(
                post("/words")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content("{ \"text\": \"Sîne klâwen durh die wolken sint geslagen, er stîget ûf mit grôzer kraft\" }"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(13)))
            .andExpect(jsonPath("$.[*].word", contains("die", "durh", "er", "geslagen", "grôzer", "klâwen", "kraft", "mit", "sint", "stîget", "sîne", "wolken", "ûf")))
            .andExpect(jsonPath("$.[*].occurrences", contains(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)));
    }

    /**
     * Tests using English.
     * 
     * @throws Exception if MockMvc throws
     */
    @Test
    public void mvcParagraphTest()
    throws Exception
    {
        mockMvc
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
