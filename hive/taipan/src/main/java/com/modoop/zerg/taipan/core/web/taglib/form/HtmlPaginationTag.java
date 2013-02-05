package com.modoop.zerg.taipan.core.web.taglib.form;

import com.modoop.zerg.taipan.core.constant.Constants;
import com.modoop.zerg.taipan.core.entity.page.Page;
import com.modoop.zerg.taipan.core.i18n.I18NDictionary;
import com.modoop.zerg.taipan.core.util.Servlets;
import com.modoop.zerg.taipan.core.util.Strings;
import com.modoop.zerg.taipan.core.web.taglib.HtmlTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

/**
 * @author: Genkyo Lee
 */
public class HtmlPaginationTag<T> extends HtmlTag
{
    // Properties
    private static final long serialVersionUID = -2747842035854074161L;

    public static final String MSG_KEY_DISPLAY_RANGE = "tag.navigator.listrange";

    public static final String MSG_KEY_BTN_FIRST = "tag.navigator.btn.first";

    public static final String MSG_KEY_BTN_PREV = "tag.navigator.btn.prev";

    public static final String MSG_KEY_BTN_NEXT = "tag.navigator.btn.next";

    public static final String MSG_KEY_BTN_LAST = "tag.navigator.btn.last";


    private Page<T> page;

    private String excludedParams = "";

    private int show = 10;


    // Constructor
    public HtmlPaginationTag()
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

    public int getShow()
    {
        return show;
    }

    public void setShow(int show)
    {
        this.show = show;
    }

    public String getExcludedParams()
    {
        return excludedParams;
    }

    public void setExcludedParams(String excludedParams)
    {
        this.excludedParams = excludedParams;
        this.excludedParams = this.excludedParams.replaceAll("\\*", ".*");
        this.excludedParams = this.excludedParams.replaceAll("\\?", ".");
    }

    @Override
    public int doStartTag() throws JspException
    {

        JspWriter out = pageContext.getOut();
        HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
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

        // add other attributs set by 'decorator'
        if (this.decorate != null && this.decorate.length() > 0)
        {
            html.append(' ').append(this.decorate);
        }

        html.append(">\n");
        html.append("   <ul>\n");

        String queryString = getQueryString(req);

        int prevPageIndex = page.getPageIndex() <= 1 ? 1 : page.getPageIndex() - 1;
        int nextPageIndex = page.getPageIndex() >= page.getPageCount() ? page.getPageCount() : page.getPageIndex() + 1;
        if (page.isHasPre())
        {
            html.append("       <li><a href=\"?").append(queryString).append("1").append("\">").append(I18NDictionary.getMessage(MSG_KEY_BTN_FIRST, curLocale)).append("</a></li>\n");
            html.append("       <li><a href=\"?").append(queryString).append(prevPageIndex).append("\">").append(I18NDictionary.getMessage(MSG_KEY_BTN_PREV, curLocale)).append("</a></li>\n");
        }
        else
        {
            html.append("       <li class=\"disabled\"><a href=\"javascript:void(0)\">").append(I18NDictionary.getMessage(MSG_KEY_BTN_FIRST, curLocale)).append("</a></li>\n");
            html.append("       <li class=\"disabled\"><a href=\"javascript:void(0)\">").append(I18NDictionary.getMessage(MSG_KEY_BTN_PREV, curLocale)).append("</a></li>\n");
        }


        int start, end;
        int round = Math.round(show / 2);

        if (page.getPageCount() <= (round + 1))
        {
            start = 1;
            end = page.getPageCount();
        }
        else
        {
            // calculate the start value
            start = page.getPageIndex() - round;
            if (start < 1)
            {
                start = 1;
            }
            else
            {
                if (start <= page.getPageCount() - show)
                {
                    start = page.getPageIndex() - round;
                }
                else
                {
                    start = page.getPageCount() - (show - 1);
                }
            }
            // calculate the end value
            end = page.getPageIndex() + (show%2 == 0 ? (round - 1) : round);
            if (end > show)
            {
                if (end <= page.getPageCount())
                {
                    end = page.getPageIndex() + (show%2 == 0 ? (round - 1) : round);
                }
                else
                {
                    end = page.getPageCount();
                }
            }
            else
            {
                end = show;
            }
        }

        // Page
        for (int i = start; i <= end; i++)
        {
            if (i == page.getPageIndex())
            {
                html.append("       <li class=\"active\"><a href=\"?").append(queryString).append(i).append("\">").append(i).append("</a></li>\n");
            }
            else
            {
                html.append("       <li><a href=\"?").append(queryString).append(i).append("\">").append(i).append("</a></li>\n");
            }
        }

        if (page.isHasNext())
        {
            html.append("       <li><a href=\"?").append(queryString).append(nextPageIndex).append("\">").append(I18NDictionary.getMessage(MSG_KEY_BTN_NEXT, curLocale)).append("</a></li>\n");
            html.append("       <li><a href=\"?").append(queryString).append(page.getPageCount()).append("\">").append(I18NDictionary.getMessage(MSG_KEY_BTN_LAST, curLocale)).append("</a></li>\n");
        }
        else
        {
            html.append("       <li class=\"disabled\"><a href=\"javascript:void(0)\">").append(I18NDictionary.getMessage(MSG_KEY_BTN_NEXT, curLocale)).append("</a></li>\n");
            html.append("       <li class=\"disabled\"><a href=\"javascript:void(0)\\").append(queryString).append(page.getPageCount()).append("\">").append(I18NDictionary.getMessage(MSG_KEY_BTN_LAST, curLocale)).append("</a></li>\n");
        }

        html.append("   </ul>\n");
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

    protected String getQueryString(HttpServletRequest req)
    {
        // Deal with query string
        StringBuilder newQuery = new StringBuilder();
        String paramValue;

        String[] patterns = this.excludedParams.split(" ");

        Enumeration paramNames = req.getParameterNames();

        while (paramNames != null && paramNames.hasMoreElements())
        {
            String paramName = (String) paramNames.nextElement();

            // Parameter 'page-index' does not need to build.
            if (Constants.QUERY_PAGE_INDEX.equals(paramName))
            {
                continue;
            }

            // paramNames matches excludedParams
            boolean matched = false;
            for (String pattern : patterns)
            {
                if (paramName.matches(pattern))
                {
                    matched = true;
                    break;
                }
            }

            if (matched)
            {
                continue;
            }

            newQuery.append(paramName).append('=');

            paramValue = req.getParameter(paramName);

            if (!Strings.isAscii(paramValue))
            {
                newQuery.append(Servlets.urlEncode(paramValue));
            }
            else
            {
                newQuery.append(paramValue);
            }

            newQuery.append('&');
        }

        return newQuery.append(Constants.QUERY_PAGE_INDEX).append('=').toString();
    }
}
