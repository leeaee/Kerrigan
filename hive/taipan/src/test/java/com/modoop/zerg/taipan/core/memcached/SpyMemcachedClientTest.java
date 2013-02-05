package com.modoop.zerg.taipan.core.memcached;

import com.modoop.zerg.taipan.core.config.ApplicationContextConfig;
import com.modoop.zerg.taipan.core.mapper.JsonMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author: YiLi
 */
@DirtiesContext
@ContextConfiguration(classes = ApplicationContextConfig.class)
public class SpyMemcachedClientTest extends AbstractJUnit4SpringContextTests
{
    @Autowired
    private SpyMemcachedClient client;

    public void getObject() throws InterruptedException
    {
        String key = "foo";
        String value = "bar";

        client.set(key, value, 3);

        //get
        String result = client.get(key);
        assertEquals(result, value);

        //get empty key
        Object o = client.get("");
        assertNull(o);

        //get null key
        Object nullkey = client.get(null);
        assertNull(nullkey);

        //get null value
        Object object = client.get("null");
        assertNull(object);

        //get expired time value
        Thread.sleep(4000);
        String expiredValue = client.get(key);
        assertNull(expiredValue);
    }

    public void addAndReplaceObject() throws InterruptedException
    {
        String key = "foo";
        String value = "bar";

        //set null object
        client.set(key, null, 10);

        //replace null object
        client.set(key, value, 10);
        assertEquals(client.get(key), value);
        client.replace(key, null, 10);
        assertEquals(client.get(key), value);

        //replace object time
        String newValue = "off";
        client.replace(key, newValue, 3);
        assertEquals(client.get(key), newValue);
        Thread.sleep(4000);
        assertNull(client.get(key));
    }

    @Test
    public void testShiro()
    {
        Object a = client.get("genkyo");
        JsonMapper jsonMapper = JsonMapper.buildNonNullMapper();
        System.out.println("genkyo >>> " + jsonMapper.toJson(a));
    }
}
