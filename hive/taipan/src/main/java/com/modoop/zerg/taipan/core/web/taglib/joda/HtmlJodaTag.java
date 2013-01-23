package com.modoop.zerg.taipan.core.web.taglib.joda;

import com.modoop.zerg.taipan.core.web.taglib.HtmlTag;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.Locale;

/**
 * @author: Genkyo Lee
 */
public class HtmlJodaTag extends HtmlTag
{
    //Properties
    private static final long TIME_OF_NA = 111111111111L;

    protected String pattern;
    protected Long value;
    protected Locale locale;
    protected String localeRef;
    protected DateTimeZone dateTimeZone;
    protected String dataTimeZoneRef;

    //Constructor
    public HtmlJodaTag()
    {
    }

    public String getPattern()
    {
        return pattern;
    }

    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }

    public Long getValue()
    {
        return value;
    }

    public void setValue(Long value)
    {
        this.value = value;
    }

    public DateTimeZone getDateTimeZone()
    {
        return dateTimeZone;
    }

    public void setDateTimeZone(DateTimeZone dateTimeZone)
    {
        this.dateTimeZone = dateTimeZone;
    }

    public String getDataTimeZoneRef()
    {
        return dataTimeZoneRef;
    }

    public void setDataTimeZoneRef(String dataTimeZoneRef)
    {
        this.dataTimeZoneRef = dataTimeZoneRef;
    }

    @Override
    public int doStartTag() throws JspException
    {
        JspWriter out = pageContext.getOut();

        DateTimeFormatter formatter;
        if (pattern != null)
        {
            formatter = DateTimeFormat.forPattern(pattern);
        }
        else
        {
            // use a medium date (no time) style by default; same as jstl
            formatter = DateTimeFormat.mediumDate();
        }

        formatter = formatter.withLocale(this.getCurrentLocale()).withZone(this.getCurrentDateTimeZone());

        String formatted;
        if (value != null)
        {
            if (value == TIME_OF_NA)
            {
                formatted = "--";
            }
            else
            {
                formatted = formatter.print(value);
            }
        }
        else
        {
            throw new JspException("value of tag must be a Long of Time Millis.");
        }

        try
        {
            out.print(formatted);
        }
        catch (IOException ioe)
        {
            throw new JspTagException(ioe.getMessage(), ioe);
        }

        return SKIP_BODY;
    }

    protected DateTimeZone getCurrentDateTimeZone()
    {
        if (this.dateTimeZone == null)
        {
            if (dataTimeZoneRef != null && dataTimeZoneRef.length() > 0)
            {
                dateTimeZone = DateTimeZone.forID(dataTimeZoneRef);
            }
            else
            {
                dateTimeZone = DateTimeZone.UTC;
            }
        }

        return dateTimeZone;
    }
}
