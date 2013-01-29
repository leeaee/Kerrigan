package com.modoop.zerg.taipan.core.shiro;

/**
 * @author: Genkyo Lee
 * @date: 8/18/12
 */
public enum ShiroMemcachedObjectType
{
    SHIRO_AUTH("shiro-auth:", 30 * 60 * 1);

    //Properties
    private String prefix;
    private int expiredTime;

    //Methods
    ShiroMemcachedObjectType(String prefix, int expiredTime)
    {
        this.prefix = prefix;
        this.expiredTime = expiredTime;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public int getExpiredTime()
    {
        return expiredTime;
    }
} // end class
