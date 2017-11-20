
package org.richfell.microrest.controllers.errors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.Test;
import org.springframework.http.HttpStatus;

/**
 * Tests the {@link org.richfell.microrest.controllers.errors.RestApiError} class.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class RestApiErrorTests
{
    /**
     * Tests that the <code>RestApiError</code> constructors produce the expected result.
     */
    @Test
    public void restApiErrorConstructionTest()
    {
        final String SYSTEM_MSG = "SYSTEM MESSAGE";
        final String ERROR_MSG = "ERROR MESSAGE";

        // check construction with a status
        RestApiError apiError = new RestApiError(HttpStatus.BAD_REQUEST);
        assertThat(apiError.getTimestamp(), is(notNullValue()));
        assertThat(HttpStatus.BAD_REQUEST, is(equalTo(apiError.getStatus())));

        // check construction with status, human-friendly message and a RuntimeException with a message
        apiError = new RestApiError(HttpStatus.BAD_REQUEST, ERROR_MSG, new RuntimeException(SYSTEM_MSG));
        assertThat(apiError.getTimestamp(), is(notNullValue()));
        assertThat(HttpStatus.BAD_REQUEST, is(equalTo(apiError.getStatus())));
        assertThat(SYSTEM_MSG, is(equalTo(apiError.getSystemMessage())));
        assertThat(ERROR_MSG, is(equalTo(apiError.getMessage())));

        // check construction with status and a RuntimeException with a message
        apiError = new RestApiError(HttpStatus.BAD_REQUEST, new RuntimeException(SYSTEM_MSG));
        assertThat(apiError.getTimestamp(), is(notNullValue()));
        assertThat(HttpStatus.BAD_REQUEST, is(equalTo(apiError.getStatus())));
        assertThat(SYSTEM_MSG, is(equalTo(apiError.getSystemMessage())));
        assertThat("Unexpected error", is(equalTo(apiError.getMessage())));
    }

    /**
     * Tests the field getter and setters.
     */
    @Test
    public void restApiErrorGetterSetterTest()
    {
        final String SYSTEM_MSG = "SYSTEM MESSAGE";
        final String ERROR_MSG = "ERROR MESSAGE";

        // create a RestApiError instance to test
        RestApiError apiError = new RestApiError(HttpStatus.OK);

        // set/get status
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        assertThat(HttpStatus.BAD_REQUEST, is(equalTo(apiError.getStatus())));

        // set/get system message
        apiError.setSystemMessage(SYSTEM_MSG);
        assertThat(SYSTEM_MSG, is(equalTo(apiError.getSystemMessage())));

        // set/get human-friendly message
        apiError.setMessage(ERROR_MSG);
        assertThat(ERROR_MSG, is(equalTo(apiError.getMessage())));
    }
}
