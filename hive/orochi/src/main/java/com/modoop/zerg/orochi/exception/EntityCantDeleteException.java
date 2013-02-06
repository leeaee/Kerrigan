package com.modoop.zerg.orochi.exception;

import com.modoop.zerg.taipan.core.web.exception.WebException;

/**
 * @author: Genkyo Lee
 * @date: 6/10/12
 */
public class EntityCantDeleteException extends WebException
{
    private static final long serialVersionUID = -7898723804039235423L;

    //Properties
    private static final String BASE_KEY = "exception.entity.cantdelete";

    //Constructor
    public EntityCantDeleteException(String msg)
    {
        super(msg);
    }


    /**
     * <pre>
     * throw new EntityCantDeleteException("entity.user", "leeaee");
     * </pre>
     *
     * @param entityKey
     * @param entityValue
     */
    public EntityCantDeleteException(String entityKey, Object entityId)
    {
        super(BASE_KEY, new Object[]{"{" + entityKey + "}", entityId});
    }
} // end class
