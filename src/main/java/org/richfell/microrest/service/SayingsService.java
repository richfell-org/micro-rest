
package org.richfell.microrest.service;

import java.util.Collection;
import org.richfell.microrest.Saying;

/**
 * Interface for a sayings service.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public interface SayingsService
{
    /**
     * Gets all of the <code>Saying</code> entities.
     * 
     * @return the <code>Saying</code> entities
     */
    Collection<Saying> findAll();

    /**
     * Gets all <code>Saying</code> entities which have the
     * given text within their quote.
     * 
     * @param text the text to match
     * @return the <code>Saying</code> entities containing <code>text</code> within their quotes
     */
    Collection<Saying> findQuotesContaining(String text);

    /**
     * Gets a <code>Saying</code> entity by its ID.
     * 
     * @param id the ID of the saying
     * @return the saying for the given ID, or <code>null</code> if not found
     */
    Saying findOne(Integer id);

    /**
     * Create a <code>Saying</code> entity.
     * 
     * @param saying the saying to persist
     * @return the ID of the created saying
     */
    Integer create(Saying saying);

    /**
     * Update the saying with the given value.
     * 
     * @param saying the saying to update
     */
    void update(Saying saying);

    /**
     * Deletes the saying with the given ID.
     * 
     * @param id the ID of the saying
     */
    void deleteById(Integer id);
}
