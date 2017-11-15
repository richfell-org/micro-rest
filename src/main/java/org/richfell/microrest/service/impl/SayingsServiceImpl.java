/*
 */

package org.richfell.microrest.service.impl;

import java.util.Collection;
import javax.annotation.Resource;
import org.richfell.microrest.Saying;
import org.richfell.microrest.service.SayingsService;
import org.richfell.microrest.service.dao.SayingsDao;
import org.springframework.stereotype.Service;

/**
 *
 * @author Richard Fellinger rich@richfell.org
 */
@Service("sayingsService")
public class SayingsServiceImpl
implements SayingsService
{
    /** The DAO for <codee>Saying</code> entities */
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
    public Integer create(Saying saying)
    {
        sayingsDao.add(saying);
        return saying.getId();
    }

    @Override
    public void update(Saying saying)
    {
        sayingsDao.update(saying);
    }

    @Override
    public void deleteById(Integer id)
    {
        Saying saying = sayingsDao.find(id);
        if(saying != null)
            sayingsDao.remove(saying);
    }
}
