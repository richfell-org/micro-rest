/*
 */

package org.richfell.microrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The microrest application.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@SpringBootApplication(scanBasePackages = "org.richfell.microrest")
public class Application
{
    /**
     * Application entry point.  Launches a Spring Boot application.
     * 
     * @param args the command line arguments
     */
    static public void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
