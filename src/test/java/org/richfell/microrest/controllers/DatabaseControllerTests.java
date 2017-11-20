/*
 */

package org.richfell.microrest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.richfell.microrest.Saying;
import org.richfell.microrest.service.SayingsService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

/**
 * Test for the /sayings REST controller.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class DatabaseControllerTests
{
    /** The controller being tested */
    @Tested(availableDuringSetup = true)
    DatabaseController databaseController;

    /** The mocked sayings service */
    @Injectable
    @Mocked
    SayingsService sayingsService;

    @Rule
    public ExpectedException apiExceptionRule = ExpectedException.none();

    private MockMvc mockMvc;

    /**
     * Writes an <code>Object</code> to a JSON string representation.
     * 
     * @param o the object to write
     * @return a JSON string representation of the object
     */
    private String jsonSringify(Object o)
    throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(o);
    }

    @Before
    public void setup()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(databaseController).build();
    }

    /**
     * Getting all sayings.
     * 
     * @throws Exception 
     */
    @Test
    public void getAllSayings()
    throws Exception
    {
        java.util.List<Saying> sayingsList = new java.util.ArrayList<>();
        sayingsList.add(new Saying(1, "Bob Hope", "Middle age is when your age starts to show around your middle."));
        sayingsList.add(new Saying(2, "Jay Unet", "Unit testing is my business."));

        new Expectations() {{
            sayingsService.findAll(); result = sayingsList;
        }};

        mockMvc
            .perform(get("/sayings").accept(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$.[0].name", is("Bob Hope")))
            .andExpect(jsonPath("$.[0].quote", is("Middle age is when your age starts to show around your middle.")))
            .andExpect(jsonPath("$.[1].name", is("Jay Unet")))
            .andExpect(jsonPath("$.[1].quote", is("Unit testing is my business.")));
    }

    /**
     * Tests adding a new <code>Saying</code>.
     * 
     * @throws Exception 
     */
    @Test
    public void createSayingHeaderTest()
    throws Exception
    {
        Saying newSaying = new Saying();
        newSaying.setName("Bob Hope");
        newSaying.setQuote("Middle age is when your age starts to show around your middle.");
        
        new Expectations() {{
            sayingsService.create((Saying)any); result = Integer.valueOf(1);
        }};

        mockMvc
            .perform(
                post("/sayings")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(jsonSringify(newSaying)))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", Matchers.endsWith("sayings/1")));
    }

    /**
     * Test deleting a saying.
     * 
     * @throws Exception if MockMvc throws
     */
    @Test
    public void deleteSayingTest()
    throws Exception
    {
        mockMvc
            .perform(delete("/sayings/1"))
            .andDo(print())
            .andExpect(status().is2xxSuccessful());
            
    }
}
