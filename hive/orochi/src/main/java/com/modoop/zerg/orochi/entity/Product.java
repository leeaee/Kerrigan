package com.modoop.zerg.orochi.entity;

import java.io.Serializable;

public class Product implements Serializable
{
    private static final long serialVersionUID = -7680342206542299706L;

    private Long id;
    private Long categoryId;
    private String serial;
    private String name;
    private String brief;
    private Float price;
    private Integer inventory;
    private String information;
    private String specification;
    private String saleService;

    public Product()
    {
    }

    public Product(String name)
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

    public Long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getSerial()
    {
        return serial;
    }

    public void setSerial(String serial)
    {
        this.serial = serial;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getBrief()
    {
        return brief;
    }

    public void setBrief(String brief)
    {
        this.brief = brief;
    }

    public Float getPrice()
    {
        return price;
    }

    public void setPrice(Float price)
    {
        this.price = price;
    }

    public Integer getInventory()
    {
        return inventory;
    }

    public void setInventory(Integer inventory)
    {
        this.inventory = inventory;
    }

    public String getInformation()
    {
        return information;
    }

    public void setInformation(String information)
    {
        this.information = information;
    }

    public String getSpecification()
    {
        return specification;
    }

    public void setSpecification(String specification)
    {
        this.specification = specification;
    }

    public String getSaleService()
    {
        return saleService;
    }

    public void setSaleService(String saleService)
    {
        this.saleService = saleService;
    }
}
