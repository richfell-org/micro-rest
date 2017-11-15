/*
 */

package org.richfell.microrest.service.dao;

import java.util.Collection;
import org.richfell.microrest.Saying;

/**
 * A DAO for the <code>Saying</code> entity.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public interface SayingsDao
extends GenericDao<Saying, Integer>
{
    /**
     * Find sayings that have the given text within their quote.
     * 
     * @param text the text to match
     * @return the sayings which have quotes containing the given text
     */
    Collection<Saying> findContaingText(String text);
}
