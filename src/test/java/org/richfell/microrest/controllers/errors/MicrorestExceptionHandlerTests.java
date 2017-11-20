
package org.richfell.microrest.controllers.errors;

import mockit.Mocked;
import mockit.Tested;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Tests for <code>MicrorestExceptionHandler</code>.
 *
 * @author Richard Fellinger rich@richfell.org
 */
public class MicrorestExceptionHandlerTests
{
    /** the subject under test */
    @Tested
    private MicrorestExceptionHandler meh = new MicrorestExceptionHandler();

    /** Mocked <code>ServletWebRequest</code> instance */
    @Mocked
    private ServletWebRequest servletWebRequest;

    /**
     * Tests <code>ResourceNotFoundException</code> handling.
     */
    @Test
    public void resourceNotFoundTest()
    {
        String SYSTEM_MSG = "SYSTEM MESSAGE";

        ResponseEntity<Object> result
            = meh.handleResourceNotFound(new ResourceNotFoundException(SYSTEM_MSG), servletWebRequest);
        Object body = result.getBody();

        assertThat(body, is(instanceOf(RestApiError.class)));

        RestApiError apiError = (RestApiError)body;
        assertThat(apiError.getStatus(), is(equalTo(HttpStatus.NOT_FOUND)));
        assertThat(apiError.getMessage(), is(equalTo(SYSTEM_MSG)));
        assertThat(apiError.getSystemMessage(), is(equalTo(SYSTEM_MSG)));
    }

    /**
     * Tests <code>IllegalArgumentException</code> handling.
     */
    @Test
    public void illegalArgumentTest()
    {
        String SYSTEM_MSG = "SYSTEM MESSAGE";

        ResponseEntity<Object> result
            = meh.handleIllegalArgumentException(new IllegalArgumentException(SYSTEM_MSG), servletWebRequest);
        Object body = result.getBody();

        assertThat(body, is(instanceOf(RestApiError.class)));

        RestApiError apiError = (RestApiError)body;
        assertThat(apiError.getStatus(), is(equalTo(HttpStatus.BAD_REQUEST)));
        assertThat(apiError.getMessage(), is(equalTo(SYSTEM_MSG)));
        assertThat(apiError.getSystemMessage(), is(equalTo(SYSTEM_MSG)));
    }

    /**
     * Tests <code>Throwable</code> handling.
     */
    @Test
    public void throwableTest()
    {
        String SYSTEM_MSG = "SYSTEM MESSAGE";

        ResponseEntity<Object> result
            = meh.handleThrowable(new Throwable(SYSTEM_MSG), servletWebRequest);
        Object body = result.getBody();

        assertThat(body, is(instanceOf(RestApiError.class)));

        RestApiError apiError = (RestApiError)body;
        assertThat(apiError.getStatus(), is(equalTo(HttpStatus.INTERNAL_SERVER_ERROR)));
        assertThat(apiError.getMessage(), is(equalTo("Unexpected error")));
        assertThat(apiError.getSystemMessage(), is(equalTo(SYSTEM_MSG)));
    }
}
