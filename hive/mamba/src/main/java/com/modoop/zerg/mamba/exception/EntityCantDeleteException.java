package com.modoop.zerg.mamba.exception;

import com.modoop.zerg.taipan.core.jersey.JerseyException;

import javax.ws.rs.core.Response;

/**
 * @author: Genkyo Lee
 * @date: 6/10/12
 */
public class EntityCantDeleteException extends JerseyException
{
    private static final long serialVersionUID = -7898723804039235423L;

    //Properties
    private static final String BASE_MSG = "{0} {1} can't be deleted!";
    public static final Response.Status CANT_DELETE = Response.Status.CONFLICT;

    //Constructor
    public EntityCantDeleteException(String msg)
    {
        super(CANT_DELETE, msg);
    }


    /**
     * <pre>
     * throw new EntityCantDeleteException("entity.user", "leeaee");
     * </pre>
     *
     * @param entityKey
     * @param entityValue
     */
    public EntityCantDeleteException(String entityKey, Object entityValue)
    {
        super(CANT_DELETE, BASE_MSG, new Object[]{entityKey, entityValue});
    }
} // end class
