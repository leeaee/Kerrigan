package com.modoop.zerg.orochi.entity.admin;

import com.modoop.zerg.taipan.core.mapper.JsonMapper;

import java.io.Serializable;

/**
 * @author: Genkyo Lee
 * @date: 5/4/12
 */
public class Role implements Serializable
{
    private static final long serialVersionUID = 520695627881046030L;

    //Properties
    private Long id;
    private String name;
    private String description;
    private Long createTime;

    //Constructors
    public Role()
    {
    }

    public Role(Long id)
    {
        this.id = id;
    }

    public Role(String name)
    {
        this.name = name;
    }

    public Role(Long id, String name)
    {
        this.id = id;
        this.name = name;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
} //end class