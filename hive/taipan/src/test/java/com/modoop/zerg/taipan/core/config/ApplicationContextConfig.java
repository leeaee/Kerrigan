package com.modoop.zerg.taipan.core.config;

import com.modoop.zerg.taipan.core.memcached.SpyMemcachedClient;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.spring.MemcachedClientFactoryBean;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Genkyo Lee
 * @date: 1/6/13
 */
@Configuration
@Configurable(autowire = Autowire.BY_NAME)
public class ApplicationContextConfig
{
    private static final String SERVERS = "192.168.56.101:11211";

    @Bean
    public MemcachedClientFactoryBean memcachedClientFactoryBean()
    {
        MemcachedClientFactoryBean memcachedClientFactoryBean = new MemcachedClientFactoryBean();
        memcachedClientFactoryBean.setServers(SERVERS);
        memcachedClientFactoryBean.setOpTimeout(1000);
        memcachedClientFactoryBean.setLocatorType(ConnectionFactoryBuilder.Locator.CONSISTENT);
        memcachedClientFactoryBean.setProtocol(ConnectionFactoryBuilder.Protocol.BINARY);

        return memcachedClientFactoryBean;
    }

    @Bean
    public SpyMemcachedClient memcachedClient() throws Exception
    {
        SpyMemcachedClient memcachedClient = new SpyMemcachedClient();
        MemcachedClient client = (MemcachedClient) memcachedClientFactoryBean().getObject();
        memcachedClient.setMemcachedClient(client);

        return memcachedClient;
    }

} // end class
