package com.modoop.zerg.taipan.core.web;

import com.modoop.zerg.taipan.core.web.servlet.Servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 为Response设置客户端缓存控制Header的Filter.
 *
 * eg.在web.xml中设置
 * 	<filter>
 * 		<filter-name>cacheControlHeaderFilter</filter-name>
 * 		<filter-class>org.springside.modules.web.CacheControlHeaderFilter</filter-class>
 * 		<init-param>
 * 			<param-name>expiresSeconds</param-name>
 * 			<param-value>31536000</param-value>
 * 		</init-param>
 * 	</filter>
 * 	<filter-mapping>
 * 		<filter-name>cacheControlHeaderFilter</filter-name>
 * 		<url-pattern>/images/*</url-pattern>
 * 	</filter-mapping>
 *
 * @author Genkyo Lee
 */
public class CacheControlHeaderFilter implements Filter
{

    private static final String PARAM_EXPIRES_SECONDS = "expiresSeconds";
    private long expiresSeconds;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException
    {
        Servlets.setExpiresHeader((HttpServletResponse) res, expiresSeconds);
        chain.doFilter(req, res);
    }

    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig)
    {
        String expiresSecondsParam = filterConfig.getInitParameter(PARAM_EXPIRES_SECONDS);
        if (expiresSecondsParam != null)
        {
            expiresSeconds = Long.valueOf(expiresSecondsParam);
        }
        else
        {
            expiresSeconds = Servlets.ONE_YEAR_SECONDS;
        }
    }

    /**
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy()
    {
    }
}
