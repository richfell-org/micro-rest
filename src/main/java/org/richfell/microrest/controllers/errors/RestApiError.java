
package org.richfell.microrest.controllers.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

/**
 * A class to represent API errors.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
public class RestApiError
{
    /**
     * Creates a <code>RestApiError</code>.
     */
    private RestApiError()
    {
        timestamp = LocalDateTime.now();
    }

    /**
     * Creates a <code>RestApiError</code> setting the HTTP status.
     * 
     * @param status  the HTTP status
     */
    public RestApiError(HttpStatus status)
    {
        this();
        this.status = status;
    }

    /**
     * Creates a <code>RestApiError</code> setting the HTTP status and the system message from the given <code>Throwable</code>.
     * 
     * @param status  the HTTP status
     * @param e  the root cause exception
     */
    public RestApiError(HttpStatus status, Throwable e)
    {
        this(status);
        message = "Unexpected error";
        systemMessage = e.getLocalizedMessage();
    }

    /**
     * Creates a <code>RestApiError</code> setting the HTTP status, the user-friendly message and the system message
     * from the given <code>Throwable</code>
     * 
     * @param status
     * @param message
     * @param e 
     */
    public RestApiError(HttpStatus status, String message, Throwable e)
    {
        this(status);
        this.message = message;
        systemMessage = e.getLocalizedMessage();
    }

    /** The HTTP status for this error */
    private HttpStatus status;

    /**
     * Gets the HTTP status corresponding this error.
     * 
     * @return the HTTP status
     */
    public HttpStatus getStatus()
    {
        return status;
    }

    /**
     * Sets the HTTP status corresponding to this error.
     * 
     * @param status  the HTTP status
     */
    public void setStatus(HttpStatus status)
    {
        this.status = status;
    }

    /** The timestamp for the error */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;

    /**
     * Gets the timestamp for this error.
     * 
     * @return the error timestamp
     */
    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    /**
     * Sets the timestamp of this error.
     * 
     * @param timestamp  the error timestamp
     */
    public void setTimestamp(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
    }

    /** Human friendly message for the error */
    private String message;

    /**
     * Gets the human-friendly error message.
     * 
     * @return the human-friendly message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Sets the human-friendly error message.
     * 
     * @param message   the human-friendly message
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /** system generated message */
    private String systemMessage;

    /**
     * Gets the system level error message.
     * 
     * @return the system error message
     */
    public String getSystemMessage()
    {
        return systemMessage;
    }

    /**
     * Sets the system level error message.
     * 
     * @param systemMessage  the system error message
     */
    public void setSystemMessage(String systemMessage)
    {
        this.systemMessage = systemMessage;
    }
}
