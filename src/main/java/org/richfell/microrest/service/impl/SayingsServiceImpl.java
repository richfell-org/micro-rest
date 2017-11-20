
package org.richfell.microrest.service.impl;

import java.util.Collection;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.richfell.microrest.Saying;
import org.richfell.microrest.service.SayingsService;
import org.richfell.microrest.service.dao.SayingsDao;
import org.springframework.stereotype.Service;

/**
 * An implementation of the {@link org.richfell.microrest.service.SayingsService}
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@Service("sayingsService")
public class SayingsServiceImpl
implements SayingsService
{
    /** The DAO for <code>Saying</code> entities */
    @Resource
    private SayingsDao sayingsDao;

    @Override
    public Collection<Saying> findAll()
    {
        return sayingsDao.list();
    }

    @Override
    public Collection<Saying> findQuotesContaining(String text)
    {
        return sayingsDao.findContaingText(text);
    }

    @Override
    public Saying findOne(Integer id)
    {
        return sayingsDao.find(id);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Integer create(Saying saying)
    {
        sayingsDao.add(saying);
        return saying.getId();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void update(Saying saying)
    {
        sayingsDao.update(saying);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteById(Integer id)
    {
        Saying saying = sayingsDao.find(id);
        if(saying != null)
            sayingsDao.remove(saying);
    }
}
