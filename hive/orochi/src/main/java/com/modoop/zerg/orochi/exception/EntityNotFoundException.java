package com.modoop.zerg.orochi.exception;

import com.modoop.zerg.taipan.core.web.exception.WebException;

/**
 * @author: Genkyo Lee
 * @date: 4/29/12
 */
public class EntityNotFoundException extends WebException
{
    private static final long serialVersionUID = -1732421582718005304L;

    //Properties
    private static final String BASE_KEY = "exception.entity.missing";

    //Constructor
    public EntityNotFoundException(String msg)
    {
        super(msg);
    }

    /**
     * 指明没有找到的实体的类型key和具体的实体主键. 如：
     *
     * <pre>
     * throw new EntityNotFoundException("entity.user", "genkyo");
     * </pre>
     *
     * @param entityKey
     * @param entityValue
     */
    public EntityNotFoundException(String entityKey, Object entityId)
    {
        super(BASE_KEY, new Object[]{"{" + entityKey + "}", entityId});
    }
} // end class