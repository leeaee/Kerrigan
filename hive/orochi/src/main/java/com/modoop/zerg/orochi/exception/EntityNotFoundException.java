package com.modoop.zerg.orochi.exception;

import com.modoop.zerg.taipan.core.jersey.JerseyException;

import javax.ws.rs.core.Response.Status;

/**
 * @author: Genkyo Lee
 * @date: 4/29/12
 */
public class EntityNotFoundException extends JerseyException
{
    private static final long serialVersionUID = -1732421582718005304L;

    //Properties
    private static final String BASE_MSG = "{0} {1} was not found!";
    public static final Status NOT_FOUND = Status.NOT_FOUND;

    //Constructor
    public EntityNotFoundException(String msg)
    {
        super(NOT_FOUND, msg);
    }

    /**
     * <pre>
     * throw new EntityNotFoundException("entity.user", "genkyo");
     * </pre>
     *
     * @param entityKey
     * @param entityValue
     */
    public EntityNotFoundException(String entityKey, Object entityValue)
    {
        super(NOT_FOUND, BASE_MSG, new Object[]{entityKey, entityValue});
    }
} // end class