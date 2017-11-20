
package org.richfell.microrest.controllers;

import java.util.Map;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.richfell.microrest.config.AppConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Test for the REST endpoint in {@link org.richfell.microrest.controllers.ExternalController} class.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
@TestPropertySource(locations="classpath:application.properties")
public class ExternalControllerTests
{
    /** The controller under test */
    @Tested(availableDuringSetup = true)
    private ExternalController controller;

    /** The base URL for requests to the external REST service */
    @Injectable
    String baseUrl = "https://jsonplaceholder.typicode.com";

    /** Mapping of the external entity names to collections of our types */
    @Injectable
    Map<String, ParameterizedTypeReference> externalEntityCollectionTypes = new AppConfig().externalEntityCollectionTypeBean();

    /** Mapping of the external entity names to our types */
    @Injectable
    Map<String, Class> externalEntityClasses = new AppConfig().externalEntityClassesBean();

    /** Our mocked MVC environment */
    private MockMvc mockMvc;

    /**
     * Set up the MockMvc.
     */
    @Before
    public void setup()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    /**
     * Test getting a collection of external entities.
     * 
     * @throws Exception if MockMvc throws
     */
    @Test
    public void getUsersTest()
    throws Exception
    {
        mockMvc
            .perform(get("/external/users").accept(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(status().isOk());
    }

    /**
     * Test getting an external entity by ID.
     * 
     * @throws Exception if MockMvc throws
     */
    @Test
    public void getUserByIdTest()
    throws Exception
    {
        mockMvc
            .perform(get("/external/users/2").accept(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(2)));
    }

    /**
     * Tests nested entities.
     * 
     * @throws Exception if MockMvc throws
     */
    @Test
    public void getPostCommentsTest()
    throws Exception
    {
        mockMvc
            .perform(get("/external/posts/2/comments").accept(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
    }
}
