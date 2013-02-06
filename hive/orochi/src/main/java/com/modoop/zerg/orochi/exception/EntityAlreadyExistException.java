package com.modoop.zerg.orochi.exception;

import com.modoop.zerg.taipan.core.web.exception.WebException;

/**
 * Exception thrown when some entity has already existed.
 *
 * @author: Genkyo Lee
 */
public class EntityAlreadyExistException extends WebException
{
    private static final long serialVersionUID = -8396495368045207838L;

    //Properties
    private static final String BASE_KEY = "exception.entity.exist";

    //Constructors
    public EntityAlreadyExistException(String msg)
    {
        super(msg);
    }

    /**
     * <pre>
     * throw new EntityAlreadyExistException("entity.user", "genkyo");
     * </pre>
     *
     * @param entityKey 实体的类型的字典key
     * @param entityId  实体的主键
     */
    public EntityAlreadyExistException(String entityKey, Object entityId)
    {
        super(BASE_KEY, new Object[]{"{" + entityKey + "}", entityId});
    }
} // end class
