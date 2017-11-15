/*
 */

package org.richfell.microrest.controllers;

import javax.annotation.Resource;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.richfell.microrest.Application;
import org.richfell.microrest.config.PersistenceConfig;
import org.richfell.microrest.service.SayingsService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Richard Fellinger rich@richfell.org
 */
@SpringBootTest
@ContextConfiguration(classes = { Application.class, PersistenceConfig.class })
@RunWith(JMockit.class)
public class DatabaseControllerTests
{
    @Tested(availableDuringSetup = true)
    DatabaseController databaseController;

    @Resource
    @Injectable
    SayingsService sayingsService;

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(databaseController).build();
    }

    @Test
    public void getAllSayings()
    throws Exception
    {
        mockMvc
            .perform(get("/sayings").accept(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
    }
}
