
package org.richfell.microrest.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.richfell.microrest.service.dao.GenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A JPA implementation of the <code>GenericDao</code> interface.
 * 
 * @author Richard Fellinger rich@richfell.org
 * @param <E>  the entity type
 * @param <K>  the key type
 */
@Transactional(Transactional.TxType.REQUIRED)
public class JpaDao<E, K extends Serializable>
implements GenericDao<E, K>
{
    /** the logger instance */
    static private final Logger LOGGER = LoggerFactory.getLogger(JpaDao.class);

    JpaDao()
    {
        daoType = (Class<E>)((ParameterizedType)getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];
    }

    protected Class<? extends E> daoType;

    /** JPA entity manager */
    protected EntityManager em;

    /**
     * Sets the JPA <code>EntityManger</code> used by this DAO.
     * 
     * @param em  the entity manager
     */
    @PersistenceContext(unitName="microrest-persistence")
    public void setPersistenceContext(EntityManager em)
    {
        this.em = em;
    }

    @Override
    public void add(E entity)
    {
        em.persist(entity);
    }

    @Override
    public void update(E entity)
    {
        em.merge(entity);
    }

    @Override
    public void remove(E entity)
    {
        em.remove(entity);
    }

    @Override
    public E find(K key)
    {
        return em.find(daoType, key);
    }

    @Override
    public Collection<E> list()
    {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery(daoType);
        Root root = cq.from(daoType);
        return em.createQuery(cq.select(root)).getResultList();
    }
}
