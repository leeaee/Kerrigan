package com.modoop.zerg.mamba.entity.admin;

import com.modoop.zerg.taipan.core.mapper.JsonMapper;

import java.io.Serializable;

/**
 * @author: Genkyo Lee
 * @date: 5/24/12
 */
public class Permission implements Serializable
{
    private static final long serialVersionUID = -1299710613733122211L;

    //Properties
    private Long id;
    private String permission;
    private String description;
    private Long createTime;

    //Constructors
    public Permission()
    {
    }

    public Permission(Long id, String permission)
    {
        this.id = id;
        this.permission = permission;
    }

    //Methods

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getPermission()
    {
        return permission;
    }

    public void setPermission(String permission)
    {
        this.permission = permission;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }

    @Override
    public String toString()
    {
        return JsonMapper.buildNonNullMapper().toJson(this);
    }
} // end class
