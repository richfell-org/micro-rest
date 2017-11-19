
package org.richfell.microrest.controllers;

import org.richfell.microrest.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the "Hello World" REST endpoint.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@RestController
@RequestMapping("/hello-world")
class HelloWorldController
{
    /**
     * Gets a "Hello World" message.
     * 
     * @return a <code>Message</code> with "Hello World" as the content.
     */
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Message getGreeting()
    {
        return new Message("Hello World");
    }
}
