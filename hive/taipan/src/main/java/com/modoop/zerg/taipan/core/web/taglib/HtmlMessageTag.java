package com.modoop.zerg.taipan.core.web.taglib;

import com.modoop.zerg.taipan.core.constant.Constants;
import com.modoop.zerg.taipan.core.i18n.I18NDictionary;
import com.modoop.zerg.taipan.core.i18n.I18NMessage;
import com.modoop.zerg.taipan.core.web.view.Button;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * @author: Genkyo
 */
public class HtmlMessageTag extends HtmlTag
{
    private static final long serialVersionUID = 1L;

    //Properties
    private I18NMessage message;

    //Constructor
    public HtmlMessageTag()
    {
    }

    //Methods
    public I18NMessage getMessage()
    {
        return message;
    }

    public void setMessage(I18NMessage message)
    {
        this.message = message;
    }

    @SuppressWarnings("unchecked")
    public int doStartTag() throws JspException
    {
        Locale userLocale = pageContext.getRequest().getLocale();
        ServletRequest req = pageContext.getRequest();

        if (message != null)
        {
            StringBuilder html = new StringBuilder("<div class=\"alert alert-block alert-success fade in\">\n");

            //Close button
            html.append("   <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\n");

            // output message
            html.append("   <p>\n");
            html.append("       <strong>").append(I18NDictionary.getMessage("msg.success", userLocale)).append("</strong>&nbsp;");
            html.append(I18NDictionary.translate(message, userLocale)).append("\n");
            html.append("   </p>\n");

            if (req.getAttribute(Constants.MESSAGE_BUTTONS) != null)
            {
                //Output buttons
                html.append("   <p>");
                List<Button> buttons = (List<Button>) req.getAttribute(Constants.MESSAGE_BUTTONS);
                for (Button button : buttons)
                {
                    html.append(button.getHtml(userLocale));
                }
                html.append("</p>\n");
            }

            //Table end
            html.append("</div>\n");

            JspWriter out = pageContext.getOut();
            try
            {
                out.println(html.toString());
            }
            catch (IOException e)
            {
                throw new JspException(e);
            }
        }

        return SKIP_BODY;
    }
}
