
package org.richfell.microrest.controllers;

import javax.annotation.Resource;
import org.richfell.microrest.Consumer;
import org.richfell.microrest.Message;
import org.richfell.microrest.Producer;
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

    @Resource
    private Producer resource1;

    @Resource
    private Producer resource2;

    /**
     * 
     * @return 
     */
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Message startThreads()
    {
        LOGGER.trace("starting deadlock threads.");

        Thread thread1 = new Thread(new Consumer(resource2, resource1), "Thread1");
        Thread thread2 = new Thread(new Consumer(resource1, resource2), "Thread2");

        thread1.start();
        thread2.start();

        String message = "Started";

        try
        {
            thread2.join(1000);
            if(thread2.isAlive())
                message = "Deadlocked";
        }
        catch(InterruptedException e)
        {
            message = "Interrupted";
        }

        return new Message(message);
    }
}
