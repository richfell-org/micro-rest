/*
 */

package org.richfell.microrest.service.dao;

import java.util.Collection;

/**
 * Generic entity DAO interface.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public interface GenericDao<E, K>
{
    /**
     * Persist the entity to the underlying repository.
     * 
     * @param entity the entity to add to the repository
     */
    void add(E entity);

    /**
     * Update the existing entity in the underlying repository.
     * 
     * @param entity the entity to use for the update
     */
    void update(E entity);

    /**
     * Delete the entity from the underlying repository.
     * 
     * @param entity the entity to delete from the repository
     */
    void remove(E entity);

    /**
     * Gets an entity from the underlying repository by its key.
     * 
     * @param key the key to the entity
     * @return the entity with the given key, or <code>null</code> if not found.
     */
    E find(K key);

    /**
     * Get all of the persisted entities.
     * 
     * @return A collection of all persisted entities.
     */
    Collection<E> list();
}
