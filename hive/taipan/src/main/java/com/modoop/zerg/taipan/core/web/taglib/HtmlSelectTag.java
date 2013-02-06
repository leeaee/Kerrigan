package com.modoop.zerg.taipan.core.web.taglib;

import org.apache.commons.beanutils.PropertyUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.util.List;

/**
 * @author: Genkyo Lee
 */
public class HtmlSelectTag extends HtmlTag
{
    private static final long serialVersionUID = 1466284751065612573L;

    //Properties
    private List items;
    private List selected;
    private int size = 1;
    private boolean hasBlankOption = false;
    private boolean hasNaOption = false;
    private String naOptionText = "option.state.na";
    private String itemValue;
    private String itemLabel;

    //Constructor
    public HtmlSelectTag()
    {
    }

    //Methods

    public List getItems()
    {
        return items;
    }

    public void setItems(List items)
    {
        this.items = items;
    }

    public String getItemValue()
    {
        return itemValue;
    }

    public void setItemValue(String itemValue)
    {
        this.itemValue = itemValue;
    }

    public String getItemLabel()
    {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel)
    {
        this.itemLabel = itemLabel;
    }

    public List getSelected()
    {
        return selected;
    }

    public void setSelected(List selected)
    {
        this.selected = selected;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public boolean isHasNaOption()
    {
        return hasNaOption;
    }

    public boolean isHasBlankOption()
    {
        return hasBlankOption;
    }

    public void setHasBlankOption(boolean hasBlankOption)
    {
        this.hasBlankOption = hasBlankOption;
    }

    public void setHasNaOption(boolean hasNaOption)
    {
        this.hasNaOption = hasNaOption;
    }

    public String getNaOptionText()
    {
        return naOptionText;
    }

    public void setNaOptionText(String naOptionText)
    {
        this.naOptionText = naOptionText;
    }

    @Override
    public int doStartTag() throws JspException
    {
        JspWriter out = pageContext.getOut();

        StringBuffer html = new StringBuffer("<select");

        //add attribute 'id'
        if (id != null && id.length() > 0)
        {
            html.append(" id=\"").append(id).append("\"");
        }

        html.append(" name=\"").append(this.name).append("\"");

        //add attribute 'size'
        if (this.size > 0)
        {
            html.append(" size=\"").append(size).append("\"");
        }

        //add attribute 'class'
        if (this.cssClass != null && this.cssClass.length() > 0)
        {
            html.append(" class=\"").append(this.cssClass).append("\"");
        }

        //add other attributs set by 'decorator'
        if (this.decorate != null && this.decorate.length() > 0)
        {
            html.append(" ").append(this.decorate);
        }

        html.append(">\n");

        //add blank option
        if (this.hasBlankOption)
        {
            html.append("<option value=\"\"></option>\n");
        }

        //add n/a option
        if (this.hasNaOption)
        {
            html.append("<option value=\"-1\">");
            html.append(this.naOptionText);
            html.append("</option>\n");
        }

        //add options
        try
        {
            if (this.items != null && this.items.size() > 0)
            {
                for (Object item : items)
                {
                    html.append("    ");
                    Object value = PropertyUtils.getProperty(item, itemValue);
                    Object label = PropertyUtils.getProperty(item, itemLabel);
                    html.append("<option value=\"").append(value).append("\"");
                    //check if this is a 'selected' option
                    if (this.selected != null && this.selected.size() > 0)
                    {
                        for (Object object : this.selected)
                        {
                            if (String.valueOf(object).equals(String.valueOf(value)))
                            {
                                html.append(" selected");
                            }
                        }
                    }
                    html.append(">");

                    html.append(label);
                    html.append("</option>\n");
                }

            }

            html.append("</select>\n");

            out.print(html);
        }
        catch (Exception e)
        {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}