package com.modoop.zerg.taipan.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author: Genkyo Lee
 * @date: 8/21/12
 */
public class PropertiesUtils
{
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();

    private PropertiesUtils()
    {
    }

    /**
     * 载入多个文件, 文件路径使用Spring Resource格式.
     */
    public static Properties loadProperties(String... resourcesPaths) throws IOException
    {
        Properties properties = new Properties();

        for (String location : resourcesPaths)
        {
            logger.debug("Loading properties file from: " + location);

            InputStream is = null;
            try
            {
                Resource resource = resourceLoader.getResource(location);
                is = resource.getInputStream();
                properties.load(is);
            }
            catch (IOException e)
            {
                logger.error("Could not load properties from path: " + location);
                throw new IOException(e.getMessage());
            }
            finally
            {
                if (is != null)
                {
                    is.close();
                }
            }
        }

        return properties;
    }
}

