package com.modoop.zerg.taipan.core.web.taglib.form;

import com.modoop.zerg.taipan.core.entity.page.Page;
import com.modoop.zerg.taipan.core.i18n.I18NDictionary;
import com.modoop.zerg.taipan.core.web.taglib.HtmlTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.Locale;

/**
 * @author: Genkyo Lee
 */
public class HtmlPageinfoTag<T> extends HtmlTag
{
    // Properties
    private static final long serialVersionUID = -2747842035854074161L;

    public static final String MSG_KEY_DISPLAY_RANGE = "tag.navigator.listrange";

    private Page<T> page;


    // Constructor
    public HtmlPageinfoTag()
    {
    }

    // Methods
    public Page<T> getPage()
    {
        return page;
    }

    public void setPage(Page<T> page)
    {
        this.page = page;
    }

    @Override
    public int doStartTag() throws JspException
    {

        JspWriter out = pageContext.getOut();
        Locale curLocale = getCurrentLocale();

        StringBuffer html = new StringBuffer("<div");

        // add attribute 'class'
        if (this.cssClass != null && this.cssClass.length() > 0)
        {
            html.append(" class=\"").append(this.cssClass).append('\"');
        }

        if (this.cssStyle != null && this.cssStyle.length() > 0)
        {
            html.append(" style=\"").append(this.cssStyle).append('\"');
        }

        html.append(">");

        html.append(I18NDictionary.getMessage(MSG_KEY_DISPLAY_RANGE, new Object[]{page.getEntryCount(), page.getEntryFromIndex(), page.getEntryToIndex()}, curLocale));
        html.append("</div>");

        try
        {
            out.println(html.toString());
        }
        catch (IOException e)
        {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
