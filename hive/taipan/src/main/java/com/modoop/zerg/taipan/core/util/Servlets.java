package com.modoop.zerg.taipan.core.util;

import com.google.common.collect.Maps;
import com.google.common.net.HttpHeaders;
import com.modoop.zerg.taipan.core.constant.Constants;
import org.apache.commons.lang3.Validate;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Http与Servlet工具类.
 */
public class Servlets
{

    //-- 常用数值定义 --//
    public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

    /**
     * 设置客户端缓存过期时间 的Header.
     */
    public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds)
    {
        //Http 1.0 header, set a fix expires date.
        response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + expiresSeconds * 1000);
        //Http 1.1 header, set a time after now.
        response.setHeader(HttpHeaders.CACHE_CONTROL, "private, max-age=" + expiresSeconds);
    }

    /**
     * 设置禁止客户端缓存的Header.
     */
    public static void setNoCacheHeader(HttpServletResponse response)
    {
        //Http 1.0 header
        response.setDateHeader(HttpHeaders.EXPIRES, 1L);
        response.addHeader(HttpHeaders.PRAGMA, "no-cache");
        //Http 1.1 header
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0");
    }

    /**
     * 设置LastModified Header.
     */
    public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate)
    {
        response.setDateHeader(HttpHeaders.LAST_MODIFIED, lastModifiedDate);
    }

    /**
     * 设置Etag Header.
     */
    public static void setEtag(HttpServletResponse response, String etag)
    {
        response.setHeader(HttpHeaders.ETAG, etag);
    }

    /**
     * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
     * <p/>
     * 如果无修改, checkIfModify返回false ,设置304 not modify status.
     *
     * @param lastModified 内容的最后修改时间.
     */
    public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
                                               long lastModified)
    {
        long ifModifiedSince = request.getDateHeader(HttpHeaders.IF_MODIFIED_SINCE);
        if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000))
        {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            return false;
        }
        return true;
    }

    /**
     * 根据浏览器 If-None-Match Header, 计算Etag是否已无效.
     * <p/>
     * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
     *
     * @param etag 内容的ETag.
     */
    public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag)
    {
        String headerValue = request.getHeader(HttpHeaders.IF_NONE_MATCH);
        if (headerValue != null)
        {
            boolean conditionSatisfied = false;
            if (!"*".equals(headerValue))
            {
                StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

                while (!conditionSatisfied && commaTokenizer.hasMoreTokens())
                {
                    String currentToken = commaTokenizer.nextToken();
                    if (currentToken.trim().equals(etag))
                    {
                        conditionSatisfied = true;
                    }
                }
            }
            else
            {
                conditionSatisfied = true;
            }

            if (conditionSatisfied)
            {
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                response.setHeader(HttpHeaders.ETAG, etag);
                return false;
            }
        }
        return true;
    }

    /**
     * 设置让浏览器弹出下载对话框的Header.
     *
     * @param fileName 下载后的文件名.
     */
    public static void setFileDownloadHeader(HttpServletResponse response, String fileName)
    {
        try
        {
            //中文文件名支持
            String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedfileName + "\"");
        }
        catch (UnsupportedEncodingException ignored)
        {
        }
    }

    /**
     * 取得带相同前缀的Request Parameters, copy from spring.
     * <p/>
     * 返回的结果的Parameter名已去除前缀.
     * <p/>
     * 比如有m2
     */
    public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix)
    {
        Validate.notNull(request, "Request must not be null");
        Enumeration paramNames = request.getParameterNames();
        Map<String, Object> params = new TreeMap<String, Object>();
        if (prefix == null)
        {
            prefix = "";
        }
        while (paramNames != null && paramNames.hasMoreElements())
        {
            String paramName = (String) paramNames.nextElement();
            if ("".equals(prefix) || paramName.startsWith(prefix))
            {
                String unprefixed = paramName.substring(prefix.length());
                String[] values = request.getParameterValues(paramName);
                if (values == null || values.length == 0)
                {
                    // Do nothing, no values found at all.
                }
                else if (values.length > 1)
                {
                    params.put(unprefixed, values);
                }
                else
                {
                    params.put(unprefixed, values[0].trim());
                }
            }
        }
        return params;
    }

    /**
     * 客户端对Http Basic验证的 Header进行编码.
     */
    public static String encodeHttpBasic(String userName, String password)
    {
        String encode = userName + ":" + password;
        return "Basic " + Encodes.encodeBase64(encode.getBytes());
    }

    /**
     * 用默认的字符集编码（UTF-8）编码指定的字符串，以便能够正确的作为 GET 参数使用。
     *
     * @param str 要编码的字符串
     * @return 编码过的字符串
     */
    public static String urlEncode(String str)
    {
        try
        {
            return URLEncoder.encode(str, Constants.DEFAULT_CHARSET);
        }
        catch (UnsupportedEncodingException e)
        {
            return str;
        }
    }

    /**
     * 用默认的字符集编码（UTF-8）解码指定的字符串，以便能够得到正确的原始参数.
     *
     * @param str 要解码的字符串
     * @return 解码过的字符串
     */
    public static String urlDecode(String str)
    {
        try
        {
            if (str.indexOf("%") > -1)
            {
                // The url has not been decoded yet and it should be decoded with project default charset.
                return URLDecoder.decode(str, Constants.DEFAULT_CHARSET);
            }
            else
            {// if (str.length() == str.getBytes().length) {
                // Some web container, such as tomcat 5.0.x, will automatically decoded url with charset 'ISO-8859-1',
                // here we need to transform the string to 'UTF-8', the project default charset.
                return new String(str.getBytes("ISO-8859-1"), Constants.DEFAULT_CHARSET);
            }
        }
        catch (UnsupportedEncodingException e)
        {
            return str;
        }
    }
}
