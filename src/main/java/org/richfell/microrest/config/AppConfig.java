
package org.richfell.microrest.config;

import java.util.Collection;
import java.util.Map;
import org.richfell.microrest.Producer;
import org.richfell.microrest.externalentities.ExternalAlbum;
import org.richfell.microrest.externalentities.ExternalComment;
import org.richfell.microrest.externalentities.ExternalPhoto;
import org.richfell.microrest.externalentities.ExternalPost;
import org.richfell.microrest.externalentities.ExternalTodo;
import org.richfell.microrest.externalentities.ExternalUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;

/**
 * Application beans.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@Configuration
public class AppConfig
{
    /**
     * A resource used in the "create 2 threads" REST endpoint.
     * 
     * @return a <code>Producer</code> resource
     */
    @Bean("resource1")
    public Producer resource1Bean()
    {
        return new Producer();
    }

    /**
     * A resource used in the "create 2 threads" REST endpoint.
     * 
     * @return a <code>Producer</code> resource
     */
    @Bean("resource2")
    public Producer resource2Bean()
    {
        return new Producer(1000, -2);
    }

    /**
     * A map of JSONPlaceholder entity names to their Java types.
     * 
     * @return a map of the entity names to their type
     */
    @Bean(name="externalEntityClasses")
    public Map<String, Class> externalEntityClassesBean()
    {
        Map<String, Class> bean = new java.util.HashMap<>();
        bean.put("posts", ExternalPost.class);
        bean.put("comments", ExternalComment.class);
        bean.put("photos", ExternalPhoto.class);
        bean.put("albums", ExternalAlbum.class);
        bean.put("users", ExternalUser.class);
        bean.put("todos", ExternalTodo.class);
        return bean;
    }

    /**
     * A map of JSONPlaceholder entity names to their generic collection Java types.
     * The <code>ParameterizedTypeReference</code> instances are user with <code>RestTemplate</code>.
     * 
     * @return a map of the entity names to their generic collection type
     */
    @Bean(name="externalEntityCollectionTypes")
    public Map<String, ParameterizedTypeReference> externalEntityCollectionTypeBean()
    {
        Map<String, ParameterizedTypeReference> bean = new java.util.HashMap<>();
        bean.put("posts", new ParameterizedTypeReference<Collection<ExternalPost>>() {});
        bean.put("comments", new ParameterizedTypeReference<Collection<ExternalComment>>() {});
        bean.put("photos", new ParameterizedTypeReference<Collection<ExternalPhoto>>() {});
        bean.put("albums", new ParameterizedTypeReference<Collection<ExternalAlbum>>() {});
        bean.put("users", new ParameterizedTypeReference<Collection<ExternalUser>>() {});
        bean.put("todos", new ParameterizedTypeReference<Collection<ExternalTodo>>() {});
        return bean;
    }
}
