package com.modoop.zerg.mamba.exception;

import com.modoop.zerg.taipan.core.jersey.JerseyException;

import javax.ws.rs.core.Response;

/**
 * @author: Genkyo Lee
 * @date: 6/1/12
 */
public class EntityAlreadyExistException extends JerseyException
{
    private static final long serialVersionUID = -8396495368045207838L;

    //Properties
    private static final String BASE_MSG = "{0} {1} already exist!";
    public static final Response.Status ALREADY_EXISTED = Response.Status.CONFLICT;

    //Constructors
    public EntityAlreadyExistException(String msg)
    {
        super(ALREADY_EXISTED, msg);
    }

    /**
     * <pre>
     * throw new EntityAlreadyExistException("entity.user", "genkyo");
     * </pre>
     *
     * @param entityKey
     * @param entityValue
     */
    public EntityAlreadyExistException(String entityKey, Object entityValue)
    {
        super(ALREADY_EXISTED, BASE_MSG, new Object[]{entityKey, entityValue});
    }
} // end class
