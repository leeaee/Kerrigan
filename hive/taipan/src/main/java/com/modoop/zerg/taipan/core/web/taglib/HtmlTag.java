package com.modoop.zerg.taipan.core.web.taglib;

import javax.servlet.jsp.tagext.TagSupport;
import java.util.Locale;

/**
 * @author: Genkyo Lee
 */
public class HtmlTag extends TagSupport
{

    private static final long serialVersionUID = 3624864486436296150L;

    //Properties
    protected String id;
    protected String name;
    protected String cssClass;
    protected String cssStyle;
    protected String decorate;
    protected Locale locale;
    protected String localeRef;

    //Constructor
    protected HtmlTag()
    {
    }

    //Methods

    public String getId()
    {
        return id;
    }

    public void setId(String id)
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

    public String getCssClass()
    {
        return cssClass;
    }

    public void setCssClass(String cssClass)
    {
        this.cssClass = cssClass;
    }

    public String getCssStyle()
    {
        return cssStyle;
    }

    public void setCssStyle(String cssStyle)
    {
        this.cssStyle = cssStyle;
    }

    public String getDecorate()
    {
        return decorate;
    }

    public void setDecorate(String decorate)
    {
        this.decorate = decorate;
    }

    public Locale getLocale()
    {
        return locale;
    }

    public void setLocale(Locale locale)
    {
        this.locale = locale;
    }

    public String getLocaleRef()
    {
        return localeRef;
    }

    public void setLocaleRef(String localeRef)
    {
        this.localeRef = localeRef;
    }

    protected Locale getCurrentLocale()
    {
        // decide the locale to translate texts
        if (this.locale == null)
        {
            if (localeRef != null && localeRef.length() > 0)
            {
                locale = (Locale) pageContext.getSession().getAttribute(localeRef);
            }
            else
            {
                locale = Locale.getDefault();
            }
        }

        return locale;
    }
}