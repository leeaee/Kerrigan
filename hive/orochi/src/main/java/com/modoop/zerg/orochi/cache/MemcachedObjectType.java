package com.modoop.zerg.orochi.cache;

/**
 * @author: Genkyo Lee
 * @date: 8/18/12
 */
public enum MemcachedObjectType
{
    DEFAULT("app-default:", 0),
    ADMIN("app-admin:", 60 * 60 * 1),
    ADMIN_ROLE("app-admin-role:", 60 * 60 * 1),
    SHIRO_AUTHENTICATION("app-shiro-authentication:", 30 * 60 * 1),
    SHIRO_AUTHORIZATION("app-shiro-authorization:", 30 * 60 * 1);

    //Properties
    private String prefix;
    private int expiredTime;

    //Methods
    MemcachedObjectType(String prefix, int expiredTime)
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
