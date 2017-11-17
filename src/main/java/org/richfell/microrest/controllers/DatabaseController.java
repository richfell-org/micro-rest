/*
 */

package org.richfell.microrest.controllers;

import java.util.Collection;
import javax.annotation.Resource;
import org.richfell.microrest.Saying;
import org.richfell.microrest.service.SayingsService;
import org.richfell.microrest.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Richard Fellinger rich@richfell.org
 */
@RestController
@RequestMapping("/sayings")
public class DatabaseController
{
    static final Logger LOGGER = LoggerFactory.getLogger(DatabaseController.class);

    /** The sayings service */
    @Resource
    private SayingsService sayingsService;

    /**
     * Gets all <code>Saying</code> entities.
     * 
     * @return the collection of sayings
     */
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Collection<Saying> getSayings()
    {
        try
        {
            return sayingsService.findAll();
        }
        catch(Throwable e)
        {
            LOGGER.error("Error with SayingsService", e);
            throw e;
        }
    }

    /**
     * Gets all <code>Saying</code> entities which contain the given
     * text within their quotes.
     * 
     * @param contains the text to match
     * @return the collection of matching sayings
     */
    @RequestMapping(
        method=RequestMethod.GET,
        params={"contains"},
        produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Collection<Saying> getSayings(@RequestParam("contains") String contains)
    {
        return sayingsService.findQuotesContaining(Preconditions.checkNotNull(contains));
    }

    /**
     * Gets the <code>Saying</code> with the ID.
     * 
     * @param id the ID of the saying
     * @return the <code>Saying</code> with the ID
     */
    @RequestMapping(path="/{id:[0-9]+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Saying getSaying(@PathVariable("id") Integer id)
    {
        return RestPreconditions.checkFound(sayingsService.findOne(id));
    }

    /**
     * Create a new saying.
     * 
     * @param b the builder for the <code>Location</code> response header
     * @param saying the new saying to persist
     * @return an empty response body
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> createSaying(UriComponentsBuilder b, @RequestBody Saying saying)
    {
        // must have a non-null parameter
        Preconditions.checkNotNull(saying);

        LOGGER.info("Creating saying {}", saying);

        // persist the saying
        Integer newId = sayingsService.create(saying);

        // build the Location header value with the URL for the new saying
        UriComponents uriLocation = b.path("/sayings/{id}").buildAndExpand(newId);
        return ResponseEntity.created(uriLocation.toUri()).build();
    }

    /**
     * Updates an existing <code>Saying</code> with the given value.
     * 
     * @param id the ID of the saying
     * @param saying the new value for the saying
     */
    @RequestMapping(path="/{id:[0-9]+}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateSaying(@PathVariable("id") Integer id, @RequestBody Saying saying)
    {
        // verify the input
        Preconditions.checkNotNull(saying);
        RestPreconditions.checkFound(sayingsService.findOne(id));

        LOGGER.info("Updating saying {} with {}", id, saying);

        // force the ID to the one in the request path
        saying.setId(id);

        // update the saying
        sayingsService.update(saying);
    }

    /**
     * Delete a saying.
     * 
     * @param id the ID of the saying to delete
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/{id:[0-9]+}", method=RequestMethod.DELETE)
    public void deleteSaying(@PathVariable("id") Integer id)
    {
        LOGGER.info("Deleting saying with ID {}", id);
        sayingsService.deleteById(id);
    }
}
