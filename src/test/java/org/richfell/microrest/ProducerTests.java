
package org.richfell.microrest;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

/**
 * Tests for the @{link org.richfell.microrest.Producer} class.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class ProducerTests
{
    /**
     * Tests that the <code>getDelta</code> method returns the expected value.
     */
    @Test
    public void checkExpectedDeltaValueTest()
    {
        Producer dfltProducer = new Producer();
        Producer customProducer = new Producer(5, -1);
        assertThat(1, is(equalTo(dfltProducer.getDelta())));
        assertThat(-1, is(equalTo(customProducer.getDelta())));
    }

    /**
     * Tests that the <code>produce</code> method returns the expected values.
     */
    @Test
    public void checkProducedValueTest()
    {
        Producer producer = new Producer(5, -1);
        int p1 = producer.produce();
        int p2 = producer.produce();
        assertThat(5, is(equalTo(p1)));
        assertThat(4, is(equalTo(p2)));
    }

    /**
     * Tests for <code>Producer</code> constructors result in the expected values.
     */
    @Test
    public void checkProducerConstructionTest()
    {
        Producer producer = new Producer();
        assertThat(1, is(equalTo(producer.getDelta())));
        assertThat(0, is(equalTo(producer.produce())));

        producer = new Producer(99, -5);
        assertThat(-5, is(equalTo(producer.getDelta())));
        assertThat(99, is(equalTo(producer.produce())));

        producer = new Producer(74);
        assertThat(1, is(equalTo(producer.getDelta())));
        assertThat(74, is(equalTo(producer.produce())));
    }
}
