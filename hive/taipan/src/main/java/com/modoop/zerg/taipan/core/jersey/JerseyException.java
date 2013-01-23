package com.modoop.zerg.taipan.core.jersey;

import javax.ws.rs.core.Response.Status;
import java.text.MessageFormat;

/**
 * @author: Genkyo Lee
 * @date: 4/29/12
 */
public class JerseyException extends Exception
{
    private static final long serialVersionUID = 5820174701987155877L;
    private Object[] params;
    private Status status;

    /**
     * Create a JerseyException with a throwable cause.
     *
     * @param cause
     */
    public JerseyException(Throwable cause)
    {
        super(cause.getMessage(), cause);
    }

    /**
     * Create a exception with response http status and message.
     *
     * @param status
     * @param msg
     */
    public JerseyException(Status status, String msg)
    {
        super(msg);
        this.status = status;
    }

    /**
     * Create a Exception with response HTTP status, message and message's format parameters.
     *
     * @param status
     * @param msg
     * @param params
     */
    public JerseyException(Status status, String msg, Object[] params)
    {
        super(msg);
        this.params = params;
        this.status = status;
    }

    /**
     * Create a Exception with a message and root cause.
     * For <code>root</code>,
     * <Ul>
     * <Li>root is an instanceof I18NException, root's getMessage() will return as message's parameters.
     * <Li>root is't an instanceof I18NException, root's message will return as message's parameters.
     * </Ul>
     *
     * @param msg
     * @param root
     */
    public JerseyException(String msg, Throwable root)
    {
        super(msg, root);
    }

    /**
     * Create a Exception with message, its parameters and root cause.
     *
     * @param msg
     * @param params
     * @param root
     */
    public JerseyException(String msg, Object[] params, Throwable root)
    {
        super(msg, root);
        this.params = params;
    }

    // Methods

    /**
     * Return the Exception format message's parameters.
     *
     * @return Object array.
     */
    public Object[] getParams()
    {
        return params;
    }

    /**
     * Return the Exception's response http status.
     *
     * @return HTTP status.
     */
    public int getStatus()
    {
        return status.getStatusCode();
    }

    /**
     * Override the getMessage method using for transfering and formatting messages.
     *
     * @return message string.
     */
    @Override
    public String getMessage()
    {
        if (this.params != null && this.params.length > 0)
        {
            return MessageFormat.format(super.getMessage(), this.params);
        }
        else if (this.getCause() != null)
        {
            if (this.getCause() instanceof JerseyException)
            {
                JerseyException cause = (JerseyException) this.getCause();
                return MessageFormat.format(super.getMessage(), cause.getMessage());
            }
            else
            {
                return MessageFormat.format(super.getMessage(), this.getCause().getMessage());
            }
        }
        else
        {
            return super.getMessage();
        }
    }
} // end class
