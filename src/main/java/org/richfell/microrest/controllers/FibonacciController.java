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
     * 
     * @param N
     * @return 
     */
    @RequestMapping(method=RequestMethod.GET)
    public List<Integer> getFirstN(@RequestParam("n") Integer N)
    {
        Preconditions.checkNotNull(N, BadRequestException.class);
        Preconditions.checkArgument(N >= 0);
        return getFibonacciNumbers(N);
    }

    static List<Integer> getFibonacciNumbers(Integer firstN)
    {
        List<Integer> result = new java.util.ArrayList<>(firstN);
        for(int i = 0; i < firstN; ++i)
            result.add(fibonacci(i));
        return result;
    }

    static int fibonacci(int n)
    {
        // fibonacci(1) == 1 and fibonacci(0) == 0
        if(n < 2)
            return n;

        return fibonacci(n-1) + fibonacci(n-2);
    }
}
