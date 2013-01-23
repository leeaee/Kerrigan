package com.modoop.zerg.mamba.entity.admin;

import com.google.common.collect.Lists;
import com.modoop.zerg.taipan.core.constant.Constants;
import com.modoop.zerg.taipan.core.mapper.JsonMapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * @author: Genkyo Lee
 * @date: 4/28/12
 */
@XmlType
        (
                namespace = Constants.NS,
                propOrder =
                        {
                                "id", "name", "password", "trueName", "phone", "mobile", "email",
                                "state", "description", "lastModify", "createTime", "roles"
                        }
        )
@XmlRootElement(name = "admin")
public class Admin implements Serializable
{
    private static final long serialVersionUID = -8298838450565454455L;

    //Properties
    private Long id;
    private String name;
    private String password;
    private String trueName;
    private String phone;
    private String mobile;
    private String email;
    private Integer state;
    private String description;
    private Long lastModify;
    private Long createTime;

    private List<Role> roles = Lists.newArrayList();

    //Constructors
    public Admin()
    {
    }

    public Admin(String name)
    {
        this.name = name;
    }

    public Admin(Long id, String name)
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getTrueName()
    {
        return trueName;
    }

    public void setTrueName(String trueName)
    {
        this.trueName = trueName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getLastModify()
    {
        return lastModify;
    }

    public void setLastModify(Long lastModify)
    {
        this.lastModify = lastModify;
    }

    public Long getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }

    @XmlElementWrapper(name = "roles")
    @XmlElement(name = "role")
    public List<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }

    @Override
    public String toString()
    {
        return JsonMapper.buildNonNullMapper().toJson(this);
    }
} // end class
