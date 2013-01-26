package com.modoop.zerg.orochi.entity;

import java.io.Serializable;

public class Category implements Serializable
{
    private static final long serialVersionUID = -8853947133551025368L;

    private Long id;
    private Long parent;
    private String name;
    private String tag;

    public Category()
    {
    }

    public Category(String name)
    {
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getParent()
    {
        return parent;
    }

    public void setParent(Long parent)
    {
        this.parent = parent;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTag()
    {
        return tag;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }
}
