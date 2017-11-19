
package org.richfell.microrest.controllers;

import org.richfell.microrest.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoint that creates two threads that become deadlocked with each other and
 * monitors the two threads to detect the deadlock.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@RestController
@RequestMapping("/threads")
public class DeadlockedThreadsController
{
    /** logger instance */
    static private final Logger LOGGER = LoggerFactory.getLogger(DeadlockedThreadsController.class);

    /**
     * 
     * @return 
     */
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Message startThreads()
    {
        LOGGER.trace("starting deadlock threads.");
        return new Message("Started");
    }
}
