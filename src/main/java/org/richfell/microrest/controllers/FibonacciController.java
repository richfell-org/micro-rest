/*
 */

package org.richfell.microrest.controllers;

import java.util.List;
import org.richfell.microrest.controllers.errors.BadRequestException;
import org.richfell.microrest.util.Preconditions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Richard Fellinger rich@richfell.org
 */
@RestController
@RequestMapping("/fibonacci")
public class FibonacciController
{
    /**
     * Makes a list of the first N number of Fibonacci numbers.  The value for
     * N is given with a query parameter named "n".
     * 
     * @param N the number of Fibonacci numbers to add to the list
     * @return the list of Fibonacci numbers
     */
    @RequestMapping(method=RequestMethod.GET)
    public List<Integer> getFirstN(@RequestParam("n") Integer N)
    {
        Preconditions.checkNotNull(N, BadRequestException.class);
        Preconditions.checkArgument(N >= 0);
        return getFibonacciNumbers(N);
    }

    /**
     * Gets a list of the first N Fibonacci numbers.
     * 
     * @param firstN the number of Fibonacci numbers to list
     * @return a list with the first <code>firstN</code> Fibonacci numbers
     */
    static List<Integer> getFibonacciNumbers(Integer firstN)
    {
        List<Integer> result = new java.util.ArrayList<>(firstN);
        for(int i = 0; i < firstN; ++i)
            result.add(fibonacci(i));
        return result;
    }

    /**
     * Calculates the <code>n</code>th Fibonacci number using recursion.  This
     * way of calculating the number has a time complexity of O(2^<code>n</code>).
     * 
     * @param n the Fibonacci number to calculate
     * @return the <code>n</code>th Fibonacci number
     */
    static int fibonacci(int n)
    {
        // fibonacci(1) == 1 and fibonacci(0) == 0
        if(n < 2)
            return n;

        return fibonacci(n-1) + fibonacci(n-2);
    }
}
