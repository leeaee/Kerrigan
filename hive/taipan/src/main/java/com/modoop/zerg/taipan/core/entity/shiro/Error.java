package com.modoop.zerg.taipan.core.entity.shiro;


import com.modoop.zerg.taipan.core.constant.Constants;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @author: Genkyo Lee
 * @date: 9/11/12
 */
@XmlType(namespace = Constants.NS, propOrder = { "code", "message" })
@XmlRootElement(name = "error")
public class Error implements Serializable
{
    private static final long serialVersionUID = 2102428013302466987L;
    private int code;
    private String message;

    public Error()
    {
    }

    public Error(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
