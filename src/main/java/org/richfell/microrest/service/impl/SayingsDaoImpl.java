/*
 */

package org.richfell.microrest.service.impl;

import java.util.Collection;
import org.richfell.microrest.Saying;
import org.richfell.microrest.service.dao.SayingsDao;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the <code>SayingsDao</code> interface.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@Repository("sayingsDao")
public class SayingsDaoImpl
extends JpaDao<Saying, Integer>
implements SayingsDao
{
    public SayingsDaoImpl()
    {
    }

    @Override
    public Collection<Saying> findContaingText(String text)
    {
        return em.createNamedQuery("sayings.quote.contains", Saying.class)
            .setParameter(1, text)
            .getResultList();
    }
}
