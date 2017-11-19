
package org.richfell.microrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A consumer class which is used in the "create 2 threads" REST endpoint.
 * 
 * In the run method, instances first lock producer1 and then wait a short amount of time
 * before locking producer2.  The consume method of each producer is then called.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class Consumer
implements Runnable
{
    /**
     * Crates a <code>Consumer</code> which consumes from the given producer resources.
     * 
     * @param producer1  the first producer
     * @param producer2  the second producer
     */
    public Consumer(Producer producer1, Producer producer2)
    {
        this.producer1 = producer1;
        this.producer2 = producer2;
    }

    /** producer resource 1 */
    private final Producer producer1;

    /** producer resource 2 */
    private final Producer producer2;

    /** logger instance */
    private final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @Override
    public void run()
    {
        synchronized(producer1)
        {
            delayFor(250);

            synchronized(producer2)
            {
                Integer value = producer1.produce() + producer2.produce();
                LOGGER.info("{}: my value is {}", Thread.currentThread().getName(), value);
            }
        }
    }

    /**
     * Sleep for the given number of milliseconds.
     * 
     * @param millis  the sleep time in milliseconds
     */
    static private void delayFor(long millis)
    {
        long startTime = java.util.Calendar.getInstance().getTimeInMillis();

        while(millis > 0)
        {
            try
            {
                Thread.currentThread().sleep(millis);
            }
            catch(InterruptedException e)
            {
            }

            millis -= java.util.Calendar.getInstance().getTimeInMillis() - startTime;
        }
    }
}
