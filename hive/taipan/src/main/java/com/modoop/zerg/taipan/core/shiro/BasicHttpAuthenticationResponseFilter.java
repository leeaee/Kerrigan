package com.modoop.zerg.taipan.core.shiro;

import com.modoop.zerg.taipan.core.entity.Error;
import com.modoop.zerg.taipan.core.mapper.JaxbMapper;
import com.modoop.zerg.taipan.core.mapper.JsonMapper;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @author: Genkyo Lee
 * @date: 9/14/12
 */
public class BasicHttpAuthenticationResponseFilter extends BasicHttpAuthenticationFilter
{
    private static final String HTTP_HEADER_ACCEPT = "Accept";
    private static final String AUTHENTICATION_RESPONSE = "Authentication failed";

    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response)
    {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String authcHeader = getAuthcScheme() + " realm=\"" + getApplicationName() + "\"";
        httpResponse.setHeader(AUTHENTICATE_HEADER, authcHeader);
        try
        {
            HttpServletRequest httpRequest = WebUtils.toHttp(request);
            com.modoop.zerg.taipan.core.entity.Error error = new Error(401, AUTHENTICATION_RESPONSE);
            String accept = httpRequest.getHeader(HTTP_HEADER_ACCEPT);
            if (accept.contains(MediaType.APPLICATION_XML) && accept.contains(MediaType.APPLICATION_JSON))
            {
                if (MediaType.APPLICATION_XML.equalsIgnoreCase(accept) || (accept.indexOf(MediaType.APPLICATION_XML) < accept.indexOf(MediaType.APPLICATION_JSON)))
                {
                    httpResponse.setContentType(MediaType.APPLICATION_XML);
                    httpResponse.getOutputStream().print(JaxbMapper.toXml(error));
                }
                else
                {
                    httpResponse.setContentType(MediaType.APPLICATION_JSON);
                    httpResponse.getOutputStream().print(JsonMapper.buildNormalMapper().toJson(error));
                }
            }
            else
            {
                if (accept.contains(MediaType.APPLICATION_XML))
                {
                    httpResponse.setContentType(MediaType.APPLICATION_XML);
                    httpResponse.getOutputStream().print(JaxbMapper.toXml(error));
                }
                else
                {
                    httpResponse.setContentType(MediaType.APPLICATION_JSON);
                    httpResponse.getOutputStream().print(JsonMapper.buildNormalMapper().toJson(error));
                }
            }

            httpResponse.getOutputStream().flush();
        }
        catch (IOException ignore)
        {
        }
        return false;
    }
}
