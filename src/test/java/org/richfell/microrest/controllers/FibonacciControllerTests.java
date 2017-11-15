/*
 */

package org.richfell.microrest.controllers;

import java.util.List;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.richfell.microrest.controllers.errors.BadRequestException;

/**
 *
 * @author Richard Fellinger rich@richfell.org
 */
@RunWith(JMockit.class)
public class FibonacciControllerTests
{
    @Tested
    FibonacciController controller;

    @Test(expected = BadRequestException.class)
    public void firstNTestWithNullParam()
    {
        controller.getFirstN(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void firstNTestWithInvalidN()
    {
        controller.getFirstN(-3);
    }

    @Test
    public void firstNTest()
    {
        List<Integer> numbers = controller.getFirstN(5);
        Assert.assertNotNull("Result is null", numbers);
        Assert.assertEquals(5, numbers.size());

        List<Integer> expectedNumbers = new java.util.ArrayList<>(5);
        expectedNumbers.add(0);
        expectedNumbers.add(1);
        expectedNumbers.add(1);
        expectedNumbers.add(2);
        expectedNumbers.add(3);
        Assert.assertEquals(expectedNumbers, numbers);
    }
}
