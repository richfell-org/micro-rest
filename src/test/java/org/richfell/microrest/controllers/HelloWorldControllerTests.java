
package org.richfell.microrest.controllers;

import mockit.Tested;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.richfell.microrest.controllers.errors.MicrorestExceptionHandler;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the /hello-world endpoint.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class HelloWorldControllerTests
{
    /** The controller instance to test */
    @Tested(availableDuringSetup = true)
    private HelloWorldController controller;

    /** used to mock HTTP request */
    private MockMvc mockMvc;

    /**
     * Builds the MockMvc instance.
     */
    @Before
    public void setup()
    {
        mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .setControllerAdvice(MicrorestExceptionHandler.class)
            .build();
    }

    /**
     * Test the "Hello World" message.
     * 
     * @throws Exception if MockMvc throws
     */
    @Test
    public void testHelloWorldMessage()
    throws Exception
    {
        mockMvc
            .perform(get("/hello-world").accept(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content", is("Hello World")));
    }
}
