/*
 */

package org.richfell.microrest.controllers;

import mockit.Tested;
import mockit.integration.junit4.JMockit;
import static org.hamcrest.Matchers.contains;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.richfell.microrest.Application;
import org.richfell.microrest.config.PersistenceConfig;
import org.richfell.microrest.controllers.errors.MicrorestExceptionHandler;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the /fibonacci-numbers controller.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = { Application.class, PersistenceConfig.class })
@RunWith(JMockit.class)
public class FibonacciControllerTests
{
    @Tested(availableDuringSetup = true)
    FibonacciController controller;

    @Rule
    public ExpectedException illegalArgExceptionRule = ExpectedException.none();

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .setControllerAdvice(MicrorestExceptionHandler.class)
            .build();
    }

    /**
     * Test for the first 7 Fibonacci numbers.
     * 
     * @throws Exception 
     */
    @Test
    public void firstNRequestTest()
    throws Exception
    {
        mockMvc
            .perform(get("/fibonacci-numbers?n=7").accept(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.[*]", contains(0,1,1,2,3,5,8)));
    }

    /**
     * Test for the first N Fibonacci numbers without providing a
     * value for N but including the parameter "n" in the request.
     * 
     * @throws Exception 
     */
    @Test
    public void firstNRequestWithNoValueTest()
    throws Exception
    {
        illegalArgExceptionRule.expectCause(org.hamcrest.Matchers.any(IllegalArgumentException.class));

        mockMvc
            .perform(get("/fibonacci-numbers?n=").accept(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }

    /**
     * Passes an invalid value for N.
     * 
     * @throws Exception 
     */
    @Test
    public void firstNRequestWithInvalidNValueTest()
    throws Exception
    {
        illegalArgExceptionRule.expectCause(org.hamcrest.Matchers.any(IllegalArgumentException.class));

        mockMvc
            .perform(get("/fibonacci-numbers?n=-8").accept(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }
}
