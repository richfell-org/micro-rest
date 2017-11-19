
package org.richfell.microrest.controllers;

import java.util.Collection;
import java.util.Map;
import javax.annotation.Resource;
import org.richfell.microrest.externalentities.ExternalEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

/**
 * Queries an external REST service using Spring <code>RestTemplate</code>.
 * 
 * <p>The external service used is <a href="https://jsonplaceholder.typicode.com">JSONPlaceholder</a>.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@RestController
@RequestMapping("/external")
class ExternalController
{
    /** logger instance */
    static private final Logger logger = LoggerFactory.getLogger(ExternalController.class);

    /** The base URL for the external REST service */
    @Value("${external.base.url}")
    String baseUrl;

    /** external entity name to parameterized collection type mapping */
    @Resource
    Map<String, ParameterizedTypeReference> externalEntityCollectionTypes;

    /**
     * Gets the collection of requested entities from the external REST service.
     * 
     * @param restTemplate  the object used to request from the external REST service
     * @param entityName  the name of the external REST service's entity
     * @param params  captures any query parameters in the request URL
     * @return the results from the external REST service
     */
    @RequestMapping(
        path="/{entity}",
        method=RequestMethod.GET,
        produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<? extends ExternalEntity>> getExternalEntities(
        RestTemplate restTemplate,
        @PathVariable("entity") String entityName,
        @RequestParam Map<String, String> params)
    {
        logger.trace("Request for all \"{}\" entities", entityName);

        // get the collection type
        ParameterizedTypeReference typeRef = externalEntityCollectionTypes.get(entityName);
        if(typeRef == null)
        {
            logger.error("Unknown external entity \"{}\" requested", entityName);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // generate the URL for the external REST service
        String url = addQueryParams(String.format("%s/%s", baseUrl, entityName), params);
        logger.trace("External request: {}", url);

        // return the results from the external REST service
        return restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
    }

    /**
     * Gets the collection of entities which are the "many" of a one-to-many relationship
     * with another entity.  For example, fetching the comments for a specific blog post:
     * 
     *  http://HOST:port/external/posts/1/comments
     * 
     * @param <T>  the type being fetched from the external REST service
     * @param restTemplate  the object used to request from the external REST service
     * @param entityName  the name of the "one" entity
     * @param id  the ID of the entity named in <code>entityName<code>
     * @param nestedEntityName  the name of the "many" entity 
     * @param params  a capture of the request's query parameters
     * @return the collection of entities returned by the external REST service
     */
    @RequestMapping(
        path="/{entity}/{id:[0-9]+}/{nestedentity}",
        method=RequestMethod.GET,
        produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<? extends ExternalEntity>> getExternalSubEntities(
        RestTemplate restTemplate,
        @PathVariable("entity") String entityName,
        @PathVariable("id") Integer id,
        @PathVariable("nestedentity") String nestedEntityName,
        @RequestParam Map<String, String> params)
    {
        logger.trace("Request for sub-entity \"{}\" of \"{}\" with ID {}", nestedEntityName, entityName, id);
        
        ParameterizedTypeReference typeRef = externalEntityCollectionTypes.get(nestedEntityName);
        if(typeRef == null)
        {
            logger.error("Unknown external sub-entity \"{}\" requested", nestedEntityName);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // generate the URL for the external REST service
        String url = addQueryParams(String.format("%s/%s/%d/%s", baseUrl, entityName, id, nestedEntityName), params);
        logger.trace("External request: {}", url);

        // return the results from the external REST service
        return restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
    }

    /** external entity name to Java class mapping */
    @Resource
    Map<String, Class> externalEntityClasses;

    /**
     * Gets an entity from the external REST service by the entity's ID.  Any query parameters
     * in this request are passed on to the external REST service.
     * 
     * @param <T>  the type of the entity
     * @param restTemplate  the object used to request from the external REST service
     * @param entityName  the name of the entity on the external REST service
     * @param id  the ID of the entity being requested
     * @param params  a capture of the request query parameters
     * @return the entity returned by the external REST service
     */
    @RequestMapping(
        path="/{entity}/{id:[0-9]+}",
        method=RequestMethod.GET,
        produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public <T extends ExternalEntity> ResponseEntity<T> getExternalEntity(
        RestTemplate restTemplate,
        @PathVariable("entity") String entityName,
        @PathVariable("id") Integer id,
        @RequestParam Map<String, String> params)
    {
        logger.trace("Request for entity \"{}\" with ID {}", entityName, id);

        // lookup the class for the entity
        Class type = externalEntityClasses.get(entityName);
        if(type == null)
        {
            logger.error("Unknown external entity \"{}\" requested", entityName);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // generate the URL for the external REST service
        String url = addQueryParams(String.format("%s/%s/%d", baseUrl, entityName, id), params);
        logger.trace("External request: {}", url);

        // request from the external REST service and return the result
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, null, type);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    /**
     * Add the given URL query parameters to the URL string.  If the parameter
     * map is empty then the URL itself is returned.
     * 
     * @param url  the base URL
     * @param params  the map of query parameters
     * @return the URL with the query parameters appended, the URL itself if <code>params</code> is empty
     */
    private String addQueryParams(String url, Map<String, String> params)
    {
        String result = url;
        if(!params.isEmpty())
        {
            StringBuilder builder = new StringBuilder(result);
            builder.append('?');
            params.forEach((n,v) -> {
                builder.append(String.format("%s=%s&", n, encodeQueryParam(v)));
            });

            result = builder.substring(0, builder.length() - 1);
        }

        return result;
    }

    /**
     * Encode a query parameter.
     * 
     * @param param  the parameter value
     * @return the encoded query parameter value
     */
    private String encodeQueryParam(String param)
    {
        try
        {
            return UriUtils.encodeQueryParam(param, "UTF8");
        }
        catch(java.io.UnsupportedEncodingException e)
        {
            logger.error("UTF8 is an unsupported query parameter encoding", e);
            return param;
        }
    }
}
