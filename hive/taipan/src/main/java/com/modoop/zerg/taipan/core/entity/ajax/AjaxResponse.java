package com.modoop.zerg.taipan.core.entity.ajax;

import java.io.Serializable;

/**
 * Ajax Response
 *
 * @author: Genkyo Lee
 */
public class AjaxResponse implements Serializable
{
    private static final long serialVersionUID = 4164404666097115858L;

    private boolean success;
    private String message;
    private String html;
    private int code;

    public AjaxResponse()
    {
    }

    public AjaxResponse(boolean success)
    {
        this.success = success;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getHtml()
    {
        return html;
    }

    public void setHtml(String html)
    {
        this.html = html;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }
}
